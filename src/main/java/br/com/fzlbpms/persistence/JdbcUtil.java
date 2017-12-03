package br.com.fzlbpms.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil{

    private static String db_sgbd = "mysql";
    private static String db_server_host = "localhost";
    private static String db_port = "3306";
    private static String classNameForSgbdDriver = "com.mysql.cj.jdbc.Driver";
    //private static String classNameForSgbdDriver = "com.mysql.jdbc.Driver";
    private static String db_name = "rfidserver";
    private static String db_user = "root";
    private static String db_password = "";

    private static String jdbc_str_connection;
    private static Connection conn;
    
    
    static public Connection getConnection(){
        if(JdbcUtil.conn != null){
            return JdbcUtil.conn;
        }else{
            try{
                Class.forName(classNameForSgbdDriver);
                JdbcUtil.jdbc_str_connection = "jdbc:"+db_sgbd+"//"+db_server_host+":"+db_port+"/"+db_name;
                JdbcUtil.conn = DriverManager.getConnection(jdbc_str_connection, db_user, db_password);         
            }catch(Exception e){
                e.printStackTrace();
            }                
        }
        return JdbcUtil.conn; 
    }

}
