package com.example.demo.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DataSourceFactory_dbcp {
    private Logger log = Logger.getLogger(DataSourceFactory.class);
    private BasicDataSource bs = null;
    public String driver,url,userName,password;


    public DataSourceFactory_dbcp(String driver,String url,String userName,String password){
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    /**
     * 创建数据源
     * @return
     */
    public BasicDataSource getDataSource() throws Exception{
        if(bs==null){
            log.info("数据库连接信息：[driver:"+driver+",url:"+url+",userName:"+userName+",password:"+password+"]");
            bs = new BasicDataSource();
            bs.setDriverClassName(driver);
            bs.setUrl(url);
            bs.setUsername(userName);
            bs.setPassword(password);
            bs.setMaxActive(300);//设置最大并发数
            bs.setInitialSize(100);//数据库初始化时，创建的连接个数
            bs.setMinIdle(100);//最小空暇连接数
            bs.setMaxIdle(200);//数据库最大连接数
            bs.setMaxWait(1000);
            bs.setMinEvictableIdleTimeMillis(60*1000);//空暇连接60秒中后释放
            bs.setTimeBetweenEvictionRunsMillis(4*60*1000);//4分钟检測一次是否有死掉的线程
            bs.setTestOnBorrow(false);
            bs.setValidationQuery("select 1");
            bs.setNumTestsPerEvictionRun(200);
            // mysql dba在mysql服务端启用了连接在空闲一定时间 (10分钟) 后，
            // 就自动关闭连接(连接失效)的功能，导致java端连接池在空闲一段时间后，
            // 连接被自动关闭(自动失效)。为了避免这种情况出现，
            // 可以在dbcp上配置空闲的时候检测连接池线程功能。建议设置为MaxIdle
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



