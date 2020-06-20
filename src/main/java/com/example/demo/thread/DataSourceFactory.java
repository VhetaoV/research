package com.example.demo.thread;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataSourceFactory {
    private Logger log = Logger.getLogger(DataSourceFactory.class);
    private ComboPooledDataSource bs = null;
    public String driver,url,userName,password;


    public DataSourceFactory(String driver, String url, String userName, String password){
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    /**
     * 创建数据源
     * @return
     */
    public ComboPooledDataSource getDataSource() throws Exception{
        if(bs==null){
            log.info("数据库连接信息：[driver:"+driver+",url:"+url+",userName:"+userName+",password:"+password+"]");
            bs = new ComboPooledDataSource();
            bs.setDriverClass(driver);
            bs.setJdbcUrl(url);
            bs.setUser(userName);
            bs.setPassword(password);
            bs.setInitialPoolSize(20);
            bs.setMaxPoolSize(20);
            bs.setMaxIdleTime(30);
        }
        return bs;
    }

    /**
     * 释放数据源
     */
    public  void shutDownDataSource() throws Exception{
        if(bs!=null){
            bs.close();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public  Connection getConnection(){
        Connection con=null;
        try {
            if(bs!=null){
                con=bs.getConnection();
            }else{
                con=getDataSource().getConnection();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return con;
    }

    /**
     * 关闭连接
     */
    public void closeCon(ResultSet rs,PreparedStatement ps,Connection con){
        if(rs!=null){
            try {
                rs.close();
            } catch (Exception e) {
                log.error("关闭结果集ResultSet异常！"+e.getMessage(), e);
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (Exception e) {
                log.error("预编译SQL语句对象PreparedStatement关闭异常！"+e.getMessage(), e);
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (Exception e) {
                log.error("关闭连接对象Connection异常！"+e.getMessage(), e);
            }
        }
    }
}



