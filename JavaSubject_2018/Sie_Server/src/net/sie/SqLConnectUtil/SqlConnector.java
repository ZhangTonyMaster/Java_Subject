package net.sie.SqLConnectUtil;

import net.sie.Impl.SqlMethodImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {

    //private String url;

//    public SqlConnector(String url){
//        this.url = url;
//    }

    public Connection getConnector() throws ClassNotFoundException, SQLException {

        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://" + "localhost:3306" + "/java_subject" + "?serverTimezone=GMT%2B8";
        //加上?serverTimezone=GMT%2B8" 即可  GMT%2B8代表： 东八区
        final String username = "root";
        final String password = "123456";
        Connection conn = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
