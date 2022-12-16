import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class SearchResult extends JFrame implements ActionListener {
    JFrame frame, frame1;
    JTextField textbox;
    JLabel label, winTitle, logonLabel;
    JButton searchButton, searchButton2, viewDbButton;
    static JTable table;
    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/students";
    Font myFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 28);
    Font lableFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 17);
    Font buttonFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 14);

    String[] columnNames = { "first_name", "last_name", "email", "password", "phoneNum" };

    public SearchResult() {
        setVisible(true);
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Window Title
        winTitle = new JLabel("DB VIEWER");
        winTitle.setBounds(120, 20,180, 30);
        winTitle.setFont(myFont);
        winTitle.setForeground(new Color(0, 255, 0));

        // Action Label
        logonLabel = new JLabel("Choose Action");
        logonLabel.setBounds(150, 70, 120, 30);
        logonLabel.setForeground(new Color(0, 255, 0));
        logonLabel.setFont(buttonFont);

        // Buttons
        searchButton = new JButton("Search Database");
        searchButton.setFont(buttonFont);
        searchButton.setBorder(null);
        searchButton.setFocusable(false);
        searchButton.setBounds(110, 120, 200, 30);
        searchButton.setHorizontalTextPosition(JButton.CENTER);
        searchButton.addActionListener(this);

        viewDbButton = new JButton("View Full Database");
        viewDbButton.setFont(buttonFont);
        viewDbButton.setBorder(null);
        viewDbButton.setHorizontalTextPosition(JButton.CENTER);
        viewDbButton.setFocusable(false);
        viewDbButton.setBounds(110, 170, 200, 30);
        viewDbButton.addActionListener(this);


        add(winTitle);
        add(logonLabel);
        add(searchButton);
        add(viewDbButton);
        getContentPane().setBackground(new Color(0x123456));
    }

    public void createUI() {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        label = new JLabel("Enter Search String");
        label.setBounds(100, 50, 200, 20);
        label.setForeground(new Color(0, 255, 0));
        label.setFont(lableFont);

        textbox = new JTextField(100);
        textbox.setBounds(100, 80, 200, 20);

        searchButton2 = new JButton("Search Database");
        searchButton2.setFont(buttonFont);
        searchButton2.setBorder(null);
        searchButton2.setFocusable(false);
        searchButton2.setBounds(100, 140, 200, 20);
        searchButton2.setHorizontalTextPosition(JButton.CENTER);
        searchButton2.addActionListener(this);

        frame.add(textbox);
        frame.add(label);
        frame.add(searchButton2);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0x123456));
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == searchButton) {
            this.dispose();
            createUI();
        }

        if (ae.getSource() == searchButton2) {
            frame.dispose();
            showTableData();
        }

        if (ae.getSource() == viewDbButton) {
            this.dispose();
            adminView();
        }

    }

    public void showTableData() {
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.setVisible(true);
        frame1.setSize(600, 500);
        frame1.setLocation(200, 200);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String textvalue = textbox.getText();

        try {
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(url, "root", "carl");
            String sql = "select * from registration where first_name or last_name like '%" + textvalue + "%'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            String first_name = "";
            String last_name = "";
            String email = "";
            String passuad = "";
            String phoneNum = "";

            while (rs.next()) {
                first_name = rs.getString("first_name");
                last_name = rs.getString("last_name");
                email = rs.getString("email");
                passuad = rs.getString("password");
                phoneNum = rs.getString("phoneNum");

                model.addRow(new Object[] { first_name, last_name, email, passuad, phoneNum });
                i++;
            }

            if (i < 1) {
                frame1.dispose();
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
    }


    public void adminView() {
        frame1 = new JFrame("Database Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.setLocation(200, 200);

        // JTable
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        // Scroll Pane
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(url, "root", "carl");
            String sql = "select * from registration";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String passuad = rs.getString("password");
                String phoneNum = rs.getString("phoneNum");

                model.addRow(new Object[] { first_name, last_name, email, passuad, phoneNum });
                i++;
            }
            if (i < 1) {
                this.dispose();
                JOptionPane.showMessageDialog(null, "No Record Found", "Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            this.dispose();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }


        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(600, 500);
    }

}