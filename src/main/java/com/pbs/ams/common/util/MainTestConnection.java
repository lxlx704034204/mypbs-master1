package com.pbs.ams.common.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;

/**
 * Created by lx on 2017/8/10.
 */
public class MainTestConnection {

    public static void main(String[] args) {

//        testConnectDB();
        testConnectRedis();
    }

    public static void testConnectRedis(){
        RedisUtil.getJedis();
    }


    public static void testConnectDB(){
        System.out.println( "-test-0-: ");
        Connection cc=null;
        PreparedStatement statement =null;
        ResultSet rs =null;
        try{
            cc= getConnection();
//            cc= getConnection2();
            System.out.println( "-test-0.1-: "+ cc);
            if(!cc.isClosed()) System.out.println( "-test-1-: Succeeded connecting to the Database!");
            statement = cc.prepareStatement("select * from upms_user  where user_id=?");
            statement.setString(1, "1");//"select * from users where name=?"
            rs = statement.executeQuery();
            while(rs.next()) {
                System.out.println("-test-2-'upms_user'表id=1的name为: "+ rs.getString("username")+"");
            }
        }catch(SQLException e){
            System.out.println("-test-3-: "+ e.toString());
        }finally {
            System.out.println("-test-4-: ");
            try{
                if(rs!=null) rs.close();
                if(statement!=null) statement.close();
                if(cc!=null) cc.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/seckill",
                    "root",
                    "liangxin");//获取连接对象
            return conn;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection2(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("zheng");
        dataSource.setUrl("jdbc:mysql://dbserver:3306/zheng");//127.0.0.1
        dataSource.setInitialSize(1); dataSource.setMinIdle(1);
        dataSource.setMaxActive(20); // 启用监控统计功能  dataSource.setFilters("stat");// for mysql  dataSource.setPoolPreparedStatements(false);

        try{
//            Class.forName(driver);
//            Connection conn= DriverManager.getConnection(url,name,pwd);//获取连接对象

            Connection conn = dataSource.getConnection();
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
