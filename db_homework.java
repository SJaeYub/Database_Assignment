package com.example.practice.db_connect;

import java.sql.*;

public class db_homework {
    private static Connection dbHomework;

    private String username = "projectuser";
    private String password = "1234";
    private static Connection dbTest;
    private String forlaptop = "@localhost:1521:XE";

    db_homework() {
        connect_DB();
    }

    private void connect_DB() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbHomework = DriverManager.getConnection("jdbc:oracle:thin:" + forDecktop, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException : " + e);
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }

    private void execute_query() throws SQLException {
        String sql = "SELECT MAKER, AVG(PRICE)\n" +
                "FROM PC NATURAL JOIN PRODUCT\n" +
                "WHERE PC.RAM = 16\n" +
                "group by MAKER\n" +
                "ORDER BY AVG(PRICE) ASC";
        PreparedStatement pstmt = dbHomework.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            System.out.println("MAKER: " + rs.getString("MAKER"));
            System.out.println("AVG(PRICE): " + rs.getString("AVG(PRICE)"));
        }

        rs.close();
        pstmt.close();
    }

    public static void main(String[] args) {
        db_homework dbh = new db_homework();
        try {
            dbh.execute_query();
            dbHomework.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException:" + e);
        }
    }
}
