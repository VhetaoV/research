package com.example.demo.thread;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MultiThreadTest {

    private static final String url = "jdbc:mysql://10.37.47.28:3306/ami_lora";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "lora_reader";
    private static final String password = "JYhLe[Lb";

    private static final String url2 = "jdbc:mysql://10.37.149.69:3306/ami_lora10";
    private static final String name2 = "com.mysql.jdbc.Driver";
    private static final String username2 = "web_amilora";
    private static final String password2 = "web_amilora";

    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getLogger(MultiThreadFinal.class);

        Class.forName(name);
        //线上库
        DataSourceFactory dataSourceFactory = new DataSourceFactory(name,url,username,password);
        //测试库
        DataSourceFactory dataSourceFactory2 = new DataSourceFactory(name2,url2,username2,password2);

        String startDateStr = "2019-09-16 00:00:00";
        boolean flag = true;
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql="";
        List<Map<String,Object>> notExistsList = new ArrayList<>();

        while(flag){
            String endDateStr = getEndDate(startDateStr);
            try{
                conn = dataSourceFactory.getConnection();
                //抄表记录的id  AND gmeter.company_id in (61,142)
                flag = false;

                StringBuilder readingSql = new StringBuilder();
                readingSql.append("SELECT res.rid,res.meter_code,res.total_used,res.post_date,res.company_id,res.unit_price FROM " +
                        "(SELECT reading.rid,reading.meter_code,reading.total_used,reading.post_date,gmeter.company_id,reading.unit_price  " +
                        "FROM ami_gprs_reading_backups reading FORCE INDEX (index_post_date) " +
                        "INNER JOIN ami_gprs_meter gmeter ON reading.meter_code = gmeter.meter_code " +
                        "WHERE setatus=1  AND reading.post_date >= '"+startDateStr+"' " +
                        "AND reading.post_date < '"+endDateStr+"') res WHERE " +
                        "NOT EXISTS (SELECT reading_id FROM ami_billing WHERE ami_billing.reading_id = res.rid AND post_date<'2019-12-01 00:00:00')");
                preparedStatement = conn.prepareStatement(sql=readingSql.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Map<String,Object> map = new HashMap<>();
                    long rid = resultSet.getLong("rid");
                    BigDecimal total_used  = resultSet.getBigDecimal("total_used");
                    String meter_code = resultSet.getString("meter_code");
                    Date post_date = resultSet.getTimestamp("post_date");
                    String company_id = resultSet.getString("company_id");
                    BigDecimal unit_price = resultSet.getBigDecimal("unit_price");
                    map.put("rid",rid);
                    map.put("total_used",total_used);
                    map.put("meter_code",meter_code);
                    map.put("post_date",post_date);
                    map.put("company_id",company_id);
                    map.put("unit_price",unit_price);
                    notExistsList.add(map);
                }
                if(dataSourceFactory!=null){
                    dataSourceFactory.closeCon(resultSet,preparedStatement,conn);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(sql);
            }finally {
                if(dataSourceFactory!=null){
                    dataSourceFactory.closeCon(resultSet,preparedStatement,conn);
                }
            }
            logger.info("获取到"+startDateStr+"--"+endDateStr+"数据:"+notExistsList.size());
            int threadNum = 20;
            ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            int perSize = notExistsList.size() / threadNum;
            int remainderSize = notExistsList.size() % threadNum;
            for (int i = 0; i <threadNum; i++) {
                DealThreadBackup thread = new DealThreadBackup();
                int startIndex = i*perSize;
                int endIndex = (i + 1) * perSize;
                if(remainderSize != 0 && i==threadNum-1){
                    endIndex = notExistsList.size();
                }
                thread.setNoeExistsList(notExistsList.subList(startIndex,endIndex));
                thread.setCountDownLatch(countDownLatch);
                thread.setDataSourceFactory(dataSourceFactory);
                thread.setDataSourceFactory2(dataSourceFactory2);
                executorService.submit(thread);
            }
            countDownLatch.await();
            executorService.shutdown();
            notExistsList.clear();
            logger.info("=====endDateStr=========="+endDateStr);
            startDateStr = endDateStr;
            if(!"2019-11-16 00:00:00".equals(endDateStr)){
                flag = true;
            }
        }
    }

    public static String getEndDate(String dateStr){
        String endDateStr="";
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(dateStr);
            Long time = date.getTime()+1000*3600*24;
            endDateStr =  simpleDateFormat.format(time);
        }catch (Exception e){

        }
        return  endDateStr;
    }

    public static List<Map<String,String>>  getDateList(String startDateStr,String endDateStr){
        List<Map<String,String>> dateList = new ArrayList<>();
        while(!startDateStr.equals(endDateStr)){
            Map<String,String> dateMap = new HashMap<String,String>();
            dateMap.put("startDateStr",startDateStr);
            dateMap.put("endDateStr",startDateStr = getEndDate(startDateStr));
            dateList.add(dateMap);
        }
        return  dateList;
    }
}

class DealThreadBackup extends Thread {

    Logger logger = Logger.getLogger(DealThread.class);

    private List<Map<String,Object>> noeExistsList;

    private CountDownLatch countDownLatch;

    private DataSourceFactory dataSourceFactory;

    private DataSourceFactory dataSourceFactory2;

    public void setNoeExistsList(List<Map<String,Object>> noeExistsList) {
        this.noeExistsList = noeExistsList;
    }
    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public void setDataSourceFactory2(DataSourceFactory dataSourceFactory2) {
        this.dataSourceFactory2 = dataSourceFactory2;
    }

    @Override
    public void run() {
        logger.info("开始处理数据");

        String sql = "";


        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        Connection connection2 = null;
        ResultSet resultSet2 = null;
        PreparedStatement preparedStatement2 = null;

        for(Map<String,Object> map : noeExistsList) {
            boolean flag = false;
            long lastReadingId = 0;
            BigDecimal lastTotalUsed = new BigDecimal(0);
            try {
                connection = dataSourceFactory.getConnection();


                Long rid = Long.parseLong(map.get("rid").toString());
                String meterCode = map.get("meter_code").toString();
                BigDecimal totalUsed = (BigDecimal) map.get("total_used");
                String postDate = map.get("post_date").toString();
                String companyId = map.get("company_id").toString();
                BigDecimal unitPrice = (BigDecimal) map.get("unit_price");

                int num = 0;
                StringBuilder countsSql = new StringBuilder();
                countsSql.append("SELECT count(*) num FROM ami_gprs_reading WHERE meter_code='"+meterCode+"' AND post_date>'"+postDate
                        +"' AND it_enabled=1 AND electricity is NULL");
                preparedStatement = connection.prepareStatement(sql = countsSql.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    num = resultSet.getInt("num");
                }
                if(num > 0){
                    logger.info("metercode====" + meterCode + "有重新建档");
                    if (dataSourceFactory != null) {
                        dataSourceFactory.closeCon(resultSet, preparedStatement, connection);
                    }
                    continue;
                }

                //查询非建档
                String sql1 = "SELECT rid,total_used from ami_gprs_reading WHERE it_enabled=1 AND setatus=1 AND electricity is not null AND " +
                        "meter_code='" + meterCode + "' AND post_date <'" + postDate + "' ORDER BY post_date DESC,total_used DESC  limit 1";
                preparedStatement = connection.prepareStatement(sql = sql1);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    lastReadingId = resultSet.getLong("rid");
                    lastTotalUsed = resultSet.getBigDecimal("total_used");
                    flag = true;
                }

                if (!flag) {
                    String sql2 = "SELECT rid,total_used from ami_gprs_reading_backups WHERE it_enabled=1 AND setatus=1 AND meter_code='"
                            + meterCode + "' AND post_date <'" + postDate + "' ORDER BY post_date DESC,total_used DESC  limit 1";
                    preparedStatement = connection.prepareStatement(sql = sql2);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        lastReadingId = resultSet.getLong("rid");
                        lastTotalUsed = resultSet.getBigDecimal("total_used");
                        flag = true;
                    }
                }

                if (!flag) {
                    String sql3 = "SELECT rid,total_used from ami_gprs_reading WHERE it_enabled=1 AND setatus=1 AND " +
                            "meter_code='" + meterCode + "' AND post_date <'" + postDate + "' ORDER BY post_date DESC,total_used  DESC limit 1";
                    preparedStatement = connection.prepareStatement(sql = sql3);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        lastReadingId = resultSet.getLong("rid");
                        lastTotalUsed = resultSet.getBigDecimal("total_used");
                        flag = false;
                    }
                }
                if (lastReadingId == 0L) {
                    logger.error("metercode====" + meterCode + "没查到上一条抄表记录");
                    if (dataSourceFactory != null) {
                        dataSourceFactory.closeCon(resultSet, preparedStatement, connection);
                    }
                    continue;
                }

                if (totalUsed.subtract(lastTotalUsed).compareTo(BigDecimal.ZERO) > 0) {
                    StringBuilder insertSql = new StringBuilder();
                    insertSql.append("INSERT INTO ami_billing_temporary(meter_code,billing_gas,before_reading_id,reading_id,post_date,create_time,company_id,unit_price) " +
                            "VALUES ('" + meterCode + "'," + totalUsed.subtract(lastTotalUsed) + "," + lastReadingId + "," + rid + ",'" + postDate + "',now(),"+companyId+","+unitPrice+")");
                    connection2 = dataSourceFactory2.getConnection();
                    preparedStatement2 = connection2.prepareStatement(insertSql.toString());
                    preparedStatement2.execute();

//                    StringBuilder difSql = new StringBuilder();
//                    difSql.append("SELECT COUNT(*) num FROM ami_billing_temporary WHERE meter_code='"+meterCode+"' " +
//                            "AND reading_id="+rid+" AND before_reading_id = "+lastReadingId);
//                    preparedStatement = connection.prepareStatement(difSql.toString());
//                    resultSet = preparedStatement.executeQuery();
//                    while (resultSet.next()){
//                        int  difNum = resultSet.getInt("num");
//                        if(difNum==0){
//                            StringBuilder insertSql2 = new StringBuilder();
//                            insertSql2.append("INSERT INTO ami_billing_temporary_copy(meter_code,billing_gas,before_reading_id,reading_id,post_date,create_time,company_id,unit_price) " +
//                                    "VALUES ('" + meterCode + "'," + totalUsed.subtract(lastTotalUsed) + "," + lastReadingId + "," + rid + ",'" + postDate + "',now(),"+companyId+","+unitPrice+")");
//                            connection2 = dataSourceFactory2.getConnection();
//                            preparedStatement2 = connection2.prepareStatement(insertSql2.toString());
//                            preparedStatement2.execute();
//                        }
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(sql);
            } finally {
                if (dataSourceFactory != null) {
                    dataSourceFactory.closeCon(resultSet, preparedStatement, connection);
                }
                if (dataSourceFactory2 != null) {
                    dataSourceFactory2.closeCon(resultSet2, preparedStatement2, connection2);
                }
            }
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}