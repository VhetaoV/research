package com.example.demo.queue;

import com.example.demo.thread.DataSourceFactory;
import com.example.demo.thread.MultiThreadFinal;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @description:
 * @author: hetao
 * @create: 2019-12-24 16:49
 **/
public class DataOperator {

    private static final String url2 = "jdbc:mysql://10.37.149.69:3306/ami_lora10";
    private static final String name2 = "com.mysql.jdbc.Driver";
    private static final String username2 = "web_amilora";
    private static final String password2 = "web_amilora";

    private static Logger logger = Logger.getLogger(MultiThreadFinal.class);

    String startDateStr = "2019-09-16 00:00:00";
    String endDateStr = "2019-11-16 00:00:00";


    public  List<Map<String,Object>> findDataPage(int startPage,int pageSize){
        DataSourceFactory dataSourceFactory2 = new DataSourceFactory(name2,url2,username2,password2);
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<Map<String,Object>> notExistsList = new ArrayList<>();

        try{
            StringBuilder sqlBuilder = new StringBuilder(0);
            int startIndex = (startPage - 1)* pageSize;
            int endIndex = startIndex + pageSize;
            sqlBuilder.append("SELECT reading.rid,reading.meter_code,reading.total_used,reading.post_date FROM ami_gprs_reading reading " +
                    "FORCE INDEX(FKgnhmtpfkvts9t472ey33dwmoa5) WHERE setatus=1 AND reading.post_date >= '"+startDateStr+"' AND " +
                    "reading.post_date <= '"+endDateStr+"' AND reading.electricity is not null AND NOT EXISTS " +
                    "(SELECT reading_id FROM ami_billing WHERE ami_billing.reading_id = reading.rid ) limit "+startIndex+","+endIndex);
            connection = dataSourceFactory2.getConnection();
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                long rid = resultSet.getLong("rid");
                BigDecimal total_used  = resultSet.getBigDecimal("total_used");
                String meter_code = resultSet.getString("meter_code");
                Date post_date = resultSet.getTimestamp("post_date");
                map.put("rid",rid);
                map.put("total_used",total_used);
                map.put("meter_code",meter_code);
                map.put("post_date",post_date);
                notExistsList.add(map);
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
          return notExistsList;
    }
}
