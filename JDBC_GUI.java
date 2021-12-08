package com.example.practice.db_connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class JDBC_GUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    static Connection conn = null;
    static PreparedStatement stmt = null;

    JPanel MainPanel;
    JTabbedPane TabbedInfoPanel;

    JLabel TabProductLabel;
    JComboBox<String> TabProductmodelNumberCombobox;
    static JTextArea TapProductTextArea;

    public JDBC_GUI() {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        setTitle("JDBC와 자바 GUI 실습");
        setBounds(100, 20, 540, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainPanel = new JPanel();
        MainPanel.setLayout(null);

        makeComponent();

        getContentPane().add(MainPanel, BorderLayout.CENTER);
    }

    public void makeComponent() {
        TabbedInfoPanel = new JTabbedPane();
        JPanel TabbedInfoPanel_Product = new JPanel();
        TabbedInfoPanel_Product.setLayout(null);

        TabbedInfoPanel.addTab("Product", TabbedInfoPanel_Product);

        TabbedInfoPanel.setBounds(10, 20, 500, 300);
        MainPanel.add(TabbedInfoPanel);

        TabProductLabel = new JLabel();
        TabProductLabel.setText("model");
        TabProductLabel.setIcon(new ImageIcon(""));
        TabProductLabel.setBounds(20, 0, 80, 80);
        TabbedInfoPanel_Product.add(TabProductLabel);

        TabProductmodelNumberCombobox = new JComboBox<String>();

        for (int i = 1; i < 10; i++) {
            TabProductmodelNumberCombobox.addItem("100" + i);
        }
        TabProductmodelNumberCombobox.addItem("1010");

        for (int i = 1; i < 9; i++) {
            TabProductmodelNumberCombobox.addItem("200" + i);
        }
        for (int h = 1; h < 7; h++) {
            TabProductmodelNumberCombobox.addItem("300" + h);
        }

        TabProductmodelNumberCombobox.setBounds(80, 20, 130, 40);
        TabbedInfoPanel_Product.add(TabProductmodelNumberCombobox);
        TabProductmodelNumberCombobox.addActionListener(this);

        TapProductTextArea = new JTextArea();
        TapProductTextArea.setFont(new Font("굴림", 0, 12));
        TapProductTextArea.setForeground(Color.black);
        TapProductTextArea.setOpaque(true);
        TapProductTextArea.setBackground(Color.white);
        TapProductTextArea.setBounds(20, 80, 450, 180);
        TapProductTextArea.setBorder(null);
        TapProductTextArea.setLineWrap(true);
        TapProductTextArea.setEditable(false);
        TabbedInfoPanel_Product.add(TapProductTextArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object buttonAction = e.getSource();

        if (buttonAction == TabProductmodelNumberCombobox) {
            String modelnumber = (String) TabProductmodelNumberCombobox.getSelectedItem();

            JDBC_Connect_Product JCP = new JDBC_Connect_Product();
            JCP.productSearch(modelnumber);
            System.out.println(modelnumber);
        }
    }
}
