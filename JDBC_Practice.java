package com.example.practice.lasthomework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class JDBC_Practice extends JFrame implements ActionListener {

    static Connection connection;
    static PreparedStatement statement = null;

    JTextField modelInput1 = new JTextField();
    JTextField modelInput2 = new JTextField();
    JTextField modelInput3 = new JTextField();
    JButton buyButton = new JButton("구매");
    JButton closeButton = new JButton("닫기");

    private JComboBox<Integer> num1 = new JComboBox<Integer>();
    private JComboBox<Integer> num2 = new JComboBox<Integer>();
    private JComboBox<Integer> num3 = new JComboBox<Integer>();

    private JPanel contentPane;

    String model1 = "";
    String model2 = "";
    String model3 = "";
    int price1 = 0;
    int price2 = 0;
    int price3 = 0;
    int quantity1 = 0;
    int quantity2 = 0;
    int quantity3 = 0;
    int totalprice = 0;

    JComboBox<String> search = new JComboBox<String>();
    JTextArea textArea = new JTextArea();
    JTextArea finalBuy = new JTextArea();
    JPanel TabbedInfoPanel_Buy = new JPanel();
    JLabel modelLabel3_1 = new JLabel();

    JButton resetButton = new JButton("리셋");
    JButton finalButton = new JButton("최종 구매");
    JButton showlist = new JButton("조회");
    JTextArea textArea_2 = new JTextArea();

    public JDBC_Practice(String user_id, String pw) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 805, 507);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLayeredPane layeredPane = new JLayeredPane();
        contentPane.add(layeredPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setFont(new Font("Dialog", Font.BOLD, 12));
        panel.setBorder(new TitledBorder("구매"));
        panel.setBounds(12, 86, 362, 351);
        layeredPane.add(panel);

        modelInput1.setBounds(112, 71, 70, 30);
        panel.add(modelInput1);

        modelInput2.setBounds(112, 121, 70, 30);
        panel.add(modelInput2);

        modelInput3.setBounds(112, 171, 70, 30);
        panel.add(modelInput3);

        JLabel modelLabel1 = new JLabel();
        modelLabel1.setText("PC");
        modelLabel1.setBounds(32, 72, 54, 30);
        panel.add(modelLabel1);

        JLabel modelLabel2 = new JLabel();
        modelLabel2.setText("Laptop");
        modelLabel2.setBounds(32, 115, 70, 41);
        panel.add(modelLabel2);

        JLabel modelLabel3 = new JLabel();
        modelLabel3.setText("Printer");
        modelLabel3.setBounds(32, 165, 70, 41);
        panel.add(modelLabel3);

        buyButton.setBounds(82, 237, 100, 35);
        buyButton.addActionListener(this);
        panel.add(buyButton);

        closeButton.setBounds(202, 237, 100, 35);
        closeButton.addActionListener(this);
        panel.add(closeButton);

        num1.setBounds(232, 71, 70, 30);
        for (int i = 1; i < 11; i++) {
            num1.addItem(i);
        }
        panel.add(num1);

        num2.setBounds(232, 121, 70, 30);
        for (int i = 1; i < 11; i++) {
            num2.addItem(i);
        }
        panel.add(num2);

        num3.setBounds(232, 171, 70, 30);
        for (int i = 1; i < 11; i++) {
            num3.addItem(i);
        }
        panel.add(num3);

        JLabel modelLabel = new JLabel();
        modelLabel.setText("모델 번호");
        modelLabel.setBounds(122, 41, 54, 30);
        panel.add(modelLabel);

        JLabel countLabel = new JLabel();
        countLabel.setText("개수");
        countLabel.setBounds(252, 41, 29, 30);
        panel.add(countLabel);

        JTabbedPane TabbedInfoPanel = new JTabbedPane();
        TabbedInfoPanel.setBounds(398, 80, 362, 357);
        layeredPane.add(TabbedInfoPanel);

        JPanel TabbedInfoPanel_Search = new JPanel();
        TabbedInfoPanel_Search.setLayout(null);
        TabbedInfoPanel.addTab("조회", (Icon) null, TabbedInfoPanel_Search, null);
        TabbedInfoPanel.setDisabledIconAt(0, null);

        JLabel modelLabel1_1 = new JLabel();
        modelLabel1_1.setText("조회할 물품 선택");
        modelLabel1_1.setBounds(31, 10, 100, 35);
        TabbedInfoPanel_Search.add(modelLabel1_1);

        search.setBounds(153, 12, 70, 30);
        search.addItem("PC");
        search.addItem("Laptop");
        search.addItem("Printer");
        search.addActionListener(this);
        TabbedInfoPanel_Search.add(search);

        textArea.setBounds(12, 55, 333, 218);
        textArea.setBorder(new LineBorder(Color.GRAY, 2));
        textArea.setEditable(false);
        TabbedInfoPanel_Search.add(textArea);

        TabbedInfoPanel_Buy.setLayout(null);
        TabbedInfoPanel.addTab("최종구매", null, TabbedInfoPanel_Buy, null);

        finalBuy.setBounds(12, 10, 333, 190);
        finalBuy.setBorder(new LineBorder(Color.GRAY, 2));
        TabbedInfoPanel_Buy.add(finalBuy);

        finalButton.setBounds(139, 232, 97, 30);
        TabbedInfoPanel_Buy.add(finalButton);
        finalButton.addActionListener(this);

        resetButton.setBounds(248, 232, 97, 30);
        TabbedInfoPanel_Buy.add(resetButton);
        resetButton.addActionListener(this);

        JPanel TabbedInfoPanel_List = new JPanel();
        TabbedInfoPanel_List.setLayout(null);
        TabbedInfoPanel.addTab("구매내역", null, TabbedInfoPanel_List, null);

        textArea_2.setBorder(new LineBorder(Color.GRAY, 2));
        textArea_2.setBounds(12, 10, 333, 188);
        TabbedInfoPanel_List.add(textArea_2);

        showlist.setBounds(248, 230, 97, 30);
        TabbedInfoPanel_List.add(showlist);
        showlist.addActionListener(this);

        JLabel revenue = new JLabel();
        revenue.setFont(new Font("굴림", Font.BOLD, 11));
        revenue.setText("컴퓨터 가게 총수입:");
        revenue.setBounds(12, 230, 147, 29);
        TabbedInfoPanel_List.add(revenue);

        modelLabel3_1.setFont(new Font("굴림", Font.BOLD, 12));
        modelLabel3_1.setBounds(160, 230, 76, 30);
        TabbedInfoPanel_List.add(modelLabel3_1);

        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
        flowLayout_1.setVgap(7);
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
        panel_1.setBounds(12, 26, 362, 43);
        layeredPane.add(panel_1);

        JLabel store = new JLabel();
        store.setFont(new Font("굴림", Font.BOLD, 20));
        store.setText("컴퓨터가게");
        panel_1.add(store);

        JPanel panel_1_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1_1.getLayout();
        flowLayout.setVgap(10);
        flowLayout.setHgap(20);
        panel_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, null));
        panel_1_1.setBounds(398, 26, 362, 43);
        layeredPane.add(panel_1_1);

        JLabel jLabel = new JLabel();
        jLabel.setText("좋은 시간 되세요. " + LocalDate.now());
        jLabel.setFont(new Font("굴림", Font.BOLD, 16));
        panel_1_1.add(jLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton) {
            model1 = modelInput1.getText();
            model2 = modelInput2.getText();
            model3 = modelInput3.getText();
            quantity1 = num1.getSelectedIndex() + 1;
            quantity2 = num2.getSelectedIndex() + 1;
            quantity3 = num3.getSelectedIndex() + 1;

            if (model1.isEmpty() && model2.isEmpty() && model3.isEmpty()) {
                JOptionPane.showMessageDialog(null, "세개 중에 한개는 입력해주세요.");
            } else {
                try {
                    if (!model1.isEmpty()) {
                        insertPC(model1, quantity1);
                    }
                    if (!model2.isEmpty()) {
                        insertLaptop(model2, quantity2);
                    }
                    if (!model3.isEmpty()) {
                        insertPrinter(model3, quantity3);
                    }

                    JOptionPane.showMessageDialog(null, "정상적으로 추가되었습니다.");
                    showAddList();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "입력하신 모델이 존재하지 않습니다.");
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == closeButton) {
            JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (e.getSource() == search) {
            try {
                showProductList();
            } catch (SQLException se) {
                se.printStackTrace();
            }

        } else if (e.getSource() == finalButton) {
            try {
                buy();
                JOptionPane.showMessageDialog(null, "최종 구매하여 [총 금액 : $" + totalprice + "]가 결제되었습니다.");
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(null, "먼저 구매를 해주세요.");
                f.printStackTrace();
            }
            int result = JOptionPane.showConfirmDialog(null, "계속 구매를 하시겠습니까?", "제목", JOptionPane.OK_CANCEL_OPTION);
            if (result == 2) {
                JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
                System.exit(0);
            } else {
                try {
                    reset();
                    finalBuy.setText(null);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        } else if (e.getSource() == resetButton) {
            try {
                reset();
                finalBuy.setText(null);
            } catch (SQLException r) {
                r.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "리셋되었습니다.");
        } else if (e.getSource() == showlist) {
            try {
                showAll();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void insertPC(String model, int quantity) throws SQLException {
        String sqlStr = "SELECT price FROM PC WHERE model=" + model;
        System.out.println("model: " + model);
        System.out.println("quantity: " + quantity);
        System.out.println("sql: " + sqlStr);
        statement = connection.prepareStatement(sqlStr);
        System.out.println("stmt: " + statement);
        ResultSet rs = statement.executeQuery();
        System.out.println("rs: " + rs);
        rs.next();
        price1 = rs.getInt("price");
        int price = price1 * quantity;
        sqlStr = "INSERT into transaction (type, tmodel, tcount, tprice)" + "VALUES('PC'," + model + "," + quantity + "," + price + ")";
        statement = connection.prepareStatement(sqlStr);
        statement.executeUpdate();
        rs.close();
        statement.close();
    }

    private void insertLaptop(String model, int quantity) throws SQLException {
        System.out.println("model : " + model);
        String sqlStr = "SELECT price FROM Laptop WHERE model=" + model;
        statement = connection.prepareStatement(sqlStr);
        ResultSet rs = statement.executeQuery();
        rs.next();
        price2 = rs.getInt("price");
        int price = price2 * quantity;
        System.out.println("price2 : " + price2);
        sqlStr = "INSERT into transaction (type, tmodel, tcount, tprice)" + "VALUES ('Laptop'," + model + "," + quantity + "," + price + ")";
        statement = connection.prepareStatement(sqlStr);
        statement.executeUpdate();
        rs.close();
        statement.close();
    }

    private void insertPrinter(String model, int quantity) throws SQLException {
        String sqlStr = "SELECT price FROM Printer WHERE model=" + model;
        statement = connection.prepareStatement(sqlStr);
        ResultSet rs = statement.executeQuery();
        rs.next();
        price3 = rs.getInt("price");
        int price = price3 * quantity;
        sqlStr = "INSERT into transaction (type, tmodel, tcount, tprice)" + "VALUES ('Printer'," + model + "," + quantity + "," + price + ")";
        statement = connection.prepareStatement(sqlStr);
        statement.executeUpdate();
        rs.close();
        statement.close();
    }

    private void showProductList() throws SQLException {
        String specification = "";

        String sqlStr = "SELECT count(column_name) num from cols where table_name = '" + ((String) search.getSelectedItem()).toUpperCase() + "'";
        System.out.println("sqlStr" + sqlStr);
        PreparedStatement stmt = connection.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        rs.next();
        int number = rs.getInt("num");
        String[] tables = new String[number];

        sqlStr = "SELECT column_name from cols where table_name = '" + ((String) search.getSelectedItem()).toUpperCase() + "'";
        System.out.println("sqlStr" + sqlStr);
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        for (number = 0; rs.next(); number++) {
            tables[number] = rs.getString("column_name");
            specification += tables[number] + "\t";
        }

        for (specification += '\n'; number > 0; number--) {
            specification += "------------------------";
        }

        specification += "\n";

        sqlStr = "SELECT * FROM " + search.getSelectedItem();
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        for (number = 0; rs.next(); number++) {
            for (int i = 0; i < tables.length; i++) {
                specification += rs.getString(tables[i]) + "\t";
            }
            specification += '\n';
        }

        textArea.setText(specification);
        System.out.println(specification);
        rs.close();
        stmt.close();
    }

    private void showAddList() throws SQLException {
        String specification = "";
        String sqlStr = "select * from transaction where type='PC'";
        PreparedStatement stmt = connection.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();
        int num, price1 = 0, price2 = 0, price3 = 0;
        Integer model;
        if(rs.next()) {
            model = rs.getInt("tmodel");
            num = rs.getInt("tcount");
            price1 = rs.getInt("tprice");
            specification += "-PC-" + '\n' + "PCmodel : " + model + "           " + "개수 : " + num + "\t" + "가격 : $" + price1 + '\n' + '\n';
            System.out.println(specification);
        }

        sqlStr = "select * from transaction where type='Laptop'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            model = rs.getInt("tmodel");
            num = rs.getInt("tcount");
            price2 = rs.getInt("tprice");
            specification += "-Laptop-" + '\n' + "Laptopmodel : " + model + "    " + "개수 : " + num + "\t" + "가격 : $" + price2 + '\n' + '\n';
            System.out.println(specification);
        }

        sqlStr = "select * from transaction where type='Printer'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            model = rs.getInt("tmodel");
            num = rs.getInt("tcount");
            price3 = rs.getInt("tprice");
            specification += "-Printer-" + '\n' + "Printermodel : " + model + "     " + "개수 : " + num + "\t" + "가격 : $" + price3 + '\n' + '\n';
            System.out.println(specification);
        }

        totalprice = price1 + price2 + price3;
        specification += "총가격 : $" + totalprice;
        finalBuy.setText(specification);
    }

    private void showAll() throws SQLException {
        String specification = "";

        String sqlStr = "SELECT count(column_name) num from cols where table_name = 'shopping_cart'";
        System.out.println("sqlStr" + sqlStr);
        PreparedStatement stmt = connection.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        rs.next();
        int number = rs.getInt("num");
        String[] tables = new String[number];

        sqlStr = "SELECT sum(tprice) from shopping_cart";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        rs.next();
        int total = rs.getInt("SUM(TPRICE)");
        modelLabel3_1.setText("$ " + total);

        sqlStr = "SELECT column_name from cols where table_name = 'shopping_cart'";
        System.out.println("sqlStr " + sqlStr);
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        for(number = 0; rs.next(); number++) {
            tables[number] = rs.getString("column_name");
            specification += tables[number] + "\t";
        }

        for(specification += '\n'; number > 0; number--) {
            specification += "------------------------";
        }

        specification += "\n";

        sqlStr = "SELECT * FROM shopping_cart";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        for(number = 0; rs.next(); number++) {
            for(int i = 0; i < tables.length; i++) {
                specification += rs.getString(tables[i]) + "\t";
            }
            specification += '\n';
        }
        textArea_2.setText(specification);
        rs.close();
        stmt.close();
    }

    private void buy() throws SQLException{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();

        String today = format.format(time);
        System.out.println("today : " + today);

        String sqlStr = "SELECT tmodel from transaction where TYPE='PC'";
        PreparedStatement stmt = connection.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();
        int model = 0, count = 0, price = 0;
        if(rs.next()) {
            model = rs.getInt("tmodel");
            System.out.println("model : " + model);
        }


        sqlStr = "SELECT tcount from transaction where TYPE='PC'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            count = rs.getInt("tcount");
            System.out.println("count : " + count);
        }

        sqlStr = "SELECT tprice from transaction where TYPE='PC'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            price = rs.getInt("tprice");
            System.out.println("price : " + price);
        }

        sqlStr = "insert into shopping_cart values (tnum_seq.NEXTVAL," + model + "," + count + "," + price + ")";
        System.out.println("sql : " + sqlStr);
        stmt = connection.prepareStatement(sqlStr);
        stmt.executeUpdate();

        sqlStr = "SELECT tmodel from transaction where TYPE='Laptop'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            model = rs.getInt("tmodel");
        }

        sqlStr = "SELECT tcount from transaction where TYPE='Laptop'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            count = rs.getInt("tcount");
        }

        sqlStr = "SELECT tprice from transaction where TYPE='Laptop'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            price = rs.getInt("tprice");
        }

        sqlStr = "insert into shopping_cart values (tnum_seq.NEXTVAL," + model + "," + count + "," + price + ")";
        stmt = connection.prepareStatement(sqlStr);
        stmt.executeUpdate();

        sqlStr = "SELECT tmodel from transaction where TYPE='Printer'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            model = rs.getInt("tmodel");
        }

        sqlStr = "SELECT tcount from transaction where TYPE='Printer'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            count = rs.getInt("tcount");
        }

        sqlStr = "SELECT tprice from transaction where TYPE='Printer'";
        stmt = connection.prepareStatement(sqlStr);
        rs = stmt.executeQuery();
        if(rs.next()) {
            price = rs.getInt("tprice");
        }

        sqlStr = "insert into shopping_cart values (tnum_seq.NEXTVAL," + model + "," + count + "," + price + ")";
        stmt = connection.prepareStatement(sqlStr);
        stmt.executeUpdate();

        rs.close();
        stmt.close();

    }

    private void reset() throws SQLException{
        String sqlStr = "truncate table transaction";
        statement = connection.prepareStatement(sqlStr);
        statement.executeUpdate();
        statement.close();
    }
}
