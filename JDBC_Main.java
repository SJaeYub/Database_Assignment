package com.example.practice.lasthomework;

import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class JDBC_Main {
    public static void main(String[] args) {

        String userid = JOptionPane.showInputDialog("Oracle 아이디를 입력해주세요.");
        String userpw = JOptionPane.showInputDialog("Oracle 비밀번호를 입력해주세요.");

        try {

            Class.forName("oracle.jdbc.OracleDriver");
            JDBC_Practice.connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", userid, userpw);

            JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
            System.out.println("데이터베이스 연결");

            JDBC_Practice gui = new JDBC_Practice(userid, userpw);
            gui.setVisible(true);
        } catch (Exception x) {
            x.printStackTrace();
            JOptionPane.showMessageDialog(null, "로그인 할 수가 없습니다. 아이디와 비밀번호를 다시 입력해주세요.");
        }
    }
}