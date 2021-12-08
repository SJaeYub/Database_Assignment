package com.example.practice.db_connect;

import java.sql.*;

public class Test1 {
    private String username = "projectuser";
    private String password = "1234";
    private static Connection dbTest;
    private String forDecktop;
    private String forlaptop = "@localhost:1521:XE";

    Test1() {
        connectDB();
    }

    private void connectDB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + forDecktop, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public void execute_query() throws SQLException {
        String sqlStr = "SELECT maker, type FROM product" + " WHERE model=2004";
        PreparedStatement pstmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            System.out.println("make: " + rs.getString("MAKER"));
            System.out.println("type: " + rs.getString("TYPE"));
        }
        rs.close();
        pstmt.close();
    }

    public static void main(String[] args) {
        Test1 t1 = new Test1();
        try {
            t1.execute_query();
            dbTest.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }

}
