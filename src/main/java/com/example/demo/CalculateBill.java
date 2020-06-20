package com.example.demo;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: hetao
 * @create: 2019-12-13 14:17
 **/
public class CalculateBill {

    private static final String url = "jdbc:mysql://10.37.47.28:3306/ami_lora";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "lora_reader";
    private static final String password = "JYhLe[Lb";

    private static final String url2 = "jdbc:mysql://10.37.149.69:3306/ami_lora10";
    private static final String name2 = "com.mysql.jdbc.Driver";
    private static final String username2 = "web_amilora";
    private static final String password2 = "web_amilora";


    private Connection connection = null;
    private PreparedStatement preparedStatement = null;


//    private Connection DBManager(String sql){
//        try{
//            Class.forName(name);
//            connection = DriverManager.getConnection(url, username, password);
//            preparedStatement = connection.prepareStatement(sql);
//            return connection;
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }

   public static void main(String[] args) throws Exception {
       Class.forName(name);
       //线上库
       Connection conn = DriverManager.getConnection(url, username, password);
       Statement statement  = conn.createStatement();
       statement.setQueryTimeout(1500);
       //测试库
       Connection conn2 = DriverManager.getConnection(url2, username2, password2);
       Statement statement2  = conn2.createStatement();


       String startDateStr = "2019-09-16 00:00:00";
       String endDateStr = getEndDate(startDateStr);

       boolean flag = true;

       while(flag){
           //抄表记录的id
           flag = false;
           StringBuilder readingSql = new StringBuilder();
           readingSql.append("SELECT rid FROM ami_gprs_reading WHERE post_date >= '"+startDateStr+
                   "' AND post_date <= '"+endDateStr+"' AND setatus=1");
           ResultSet resultSet = statement.executeQuery(readingSql.toString());
           List<Long> readingList = new ArrayList<>();
           while (resultSet.next()){
               long rid = resultSet.getLong("rid");
               readingList.add(rid);
           }

           //账单中readingid
           StringBuilder billingSql = new StringBuilder();
           billingSql.append("SELECT reading_id FROM ami_billing WHERE post_date >= '"+startDateStr+"' AND post_date <= '"+endDateStr+"'");
           List<Long> billingList = new ArrayList<>();
           resultSet = statement.executeQuery(billingSql.toString());
           while (resultSet.next()){
               long reading_id = resultSet.getLong("reading_id");
               billingList.add(reading_id);
           }

           List<Long> notExistsList = new ArrayList<>();

           for(Long rid : readingList) {
               if (billingList.contains(rid)) {
                   continue;
               }
               notExistsList.add(rid);
           }
               StringBuilder findReadingByRidSql = new StringBuilder();
               findReadingByRidSql.append("select rid,total_used,meter_code,post_date from ami_gprs_reading where rid=");
               resultSet =  statement.executeQuery(findReadingByRidSql.toString());
               while (resultSet.next()){
                   long reading_id = resultSet.getLong("rid");
                   BigDecimal total_used  = resultSet.getBigDecimal("total_used");
                   String meter_code = resultSet.getString("meter_code");
                   Date post_date = resultSet.getTimestamp("post_date");
                   StringBuilder findLastReadingByMeterCodeSql = new StringBuilder();
                   if(meter_code.contains("'")){
                       System.out.println("meter_code====="+meter_code);
                       continue;
                   }
                   findLastReadingByMeterCodeSql.append("select reading.rid,reading.total_used,reading.meter_code,reading.post_date from ami_gprs_reading reading " +
                           " where reading.setatus =1 and reading.meter_code='"+meter_code+"' and reading.post_date < '"+post_date+"' order by reading.post_date desc limit 1");
                   resultSet =  statement.executeQuery(findLastReadingByMeterCodeSql.toString());
                   while (resultSet.next()){
                       long last_reading_id = resultSet.getLong("rid");
                       BigDecimal last_total_used  = resultSet.getBigDecimal("total_used");
                       if(!total_used.subtract(last_total_used).equals(0)){
                           StringBuilder insertSql = new StringBuilder();
                           insertSql.append("INSERT INTO ami_billing_temporary_copy(meter_code,billing_gas,before_reading_id,reading_id,post_date) " +
                                   "VALUES ('"+meter_code+"',"+total_used.subtract(last_total_used)+","+reading_id+","+last_reading_id+",'"+post_date+"')");
                           statement2.execute(insertSql.toString());
                       }
                   }
               }

           System.err.println("startDateStr=========="+startDateStr);
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
}


