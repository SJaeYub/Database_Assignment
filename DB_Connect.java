package com.example.practice.db_connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connect {
    private static Connection dbTest;
    private static String username, password;

    DB_Connect() {
//        connectDB(username, password, dbTest);
    }

    public void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "sys as sysdba", "1234");    노트북용

            System.out.println("데이터 베이스에 연결 되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 베이스 연결에 실패하였습니다.");
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static void main(String[] argv) {
        new DB_Connect();
        try {
            dbTest.close();
        } catch (SQLException e) {
            System.out.println("Exception:" + e);
        }
    }
}

