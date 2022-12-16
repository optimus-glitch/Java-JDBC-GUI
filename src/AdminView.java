import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AdminView extends JFrame{
    JFrame frame1;
    JTextField textbox;
    JLabel label;
    JButton button;
    static JTable table;
    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/students";

    String[] columnNames = { "first_name", "last_name", "email", "password", "phoneNum"};

    public AdminView() {
        frame1 = new JFrame("Database Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.setLocationRelativeTo(null);

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

    public static void main(String[] args) {
        new AdminView();
    }
}
