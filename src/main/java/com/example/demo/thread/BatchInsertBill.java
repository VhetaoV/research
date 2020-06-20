package com.example.demo.thread;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * @description:
 * @author: hetao
 * @create: 2019-12-19 15:29
 **/
public class BatchInsertBill {

    private static final String url = "jdbc:mysql://10.37.47.28:3306/ami_lora";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "lora_reader";
    private static final String password = "JYhLe[Lb";

    private static final String url2 = "jdbc:mysql://10.37.149.69:3306/ami_lora10";
    private static final String name2 = "com.mysql.jdbc.Driver";
    private static final String username2 = "web_amilora";
    private static final String password2 = "web_amilora";

    public static void main(String[] args){

        //线上库
        DataSourceFactory dataSourceFactory = new DataSourceFactory(name,url,username,password);
        //测试库
        DataSourceFactory dataSourceFactory2 = new DataSourceFactory(name2,url2,username2,password2);

        Connection connection2 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet2 = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sql = "SELECT meter_code,billing_gas,meter_value,before_reading_id,reading_id FROM ami_billing_temporary";
            connection2 = dataSourceFactory2.getConnection();
            preparedStatement2 = connection2.prepareStatement(sql);
            while (resultSet2.next()){
                long rid = resultSet2.getLong("rid");
                BigDecimal total_used  = resultSet2.getBigDecimal("total_used");
                String meter_code = resultSet2.getString("meter_code");
                long before_reading_id = resultSet2.getLong("before_reading_id");
                long reading_id = resultSet2.getLong("reading_id");

                StringBuilder querySql = new StringBuilder();
                querySql.append("SELECT now(),gprs.company_id,gprs.customer_id,customer_name,1,1.5,'SYS',now(),pricing.pricing_code," +
                                "pricing.pid,'remark',pricing.base_price*1.5,now(),137266517,customer.customer_code,160725548,gprs.mid" +
                                "FROM ami_gprs_meter gprs" +
                                "INNER JOIN ami_pricing pricing ON gprs.pricing_id = pricing.pid" +
                                "INNER JOIN ami_customer customer ON customer.cid = gprs.customer_id" +
                                "WHERE gprs.meter_code = 'A01981805061453' AND pricing.pricing_tiered=1 ");
                connection = dataSourceFactory.getConnection();
                preparedStatement = connection.prepareStatement(querySql.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){

                }

                StringBuilder insertSql = new StringBuilder();
                insertSql.append("");
            }

            if(dataSourceFactory2!=null){
                dataSourceFactory2.closeCon(resultSet2,preparedStatement2,connection2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(dataSourceFactory2!=null){
                dataSourceFactory2.closeCon(resultSet2,preparedStatement2,connection2);
            }
        }
    }
}
