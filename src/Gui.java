import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

class Gui extends JFrame implements ActionListener {

    JLabel winTitle, fnameLabel, lnameLabel, emaiLabel, passwordLabel, phoneLabel;
    JTextField fnameField, lnameField, emailField, phoneField;
    JButton registerButton, cancelButton;
    JPasswordField passwordField;
    final static String url = "jdbc:mysql://localhost:3306/students";

    Font myFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 28);
    Font lableFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 17);
    Font buttonFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 14);

    public Gui() {
        setVisible(true);
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Window Title
        winTitle = new JLabel("EVENT REGISTRATION");
        winTitle.setBounds(50, 20, 320, 30);
        winTitle.setFont(myFont);
        winTitle.setForeground(new Color(0, 255, 0));

        // Labels
        fnameLabel = new JLabel("First-Name");
        fnameLabel.setBounds(45, 80, 100, 20);
        fnameLabel.setForeground(new Color(0, 255, 0));
        fnameLabel.setFont(lableFont);
        fnameLabel.setFocusable(true);

        lnameLabel = new JLabel("Last-Name");
        lnameLabel.setBounds(45, 140, 100, 20);
        lnameLabel.setForeground(new Color(0, 255, 0));
        lnameLabel.setFont(lableFont);

        emaiLabel = new JLabel("Email");
        emaiLabel.setBounds(45, 200, 100, 20);
        emaiLabel.setForeground(new Color(0, 255, 0));
        emaiLabel.setFont(lableFont);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(45, 260, 100, 20);
        passwordLabel.setForeground(new Color(0, 255, 0));
        passwordLabel.setFont(lableFont);

        phoneLabel = new JLabel("Telephone");
        phoneLabel.setBounds(45, 320, 100, 20);
        phoneLabel.setForeground(new Color(0, 255, 0));
        phoneLabel.setFont(lableFont);

        // Input Fields
        fnameField = new JTextField();
        fnameField.setBounds(170, 78, 200, 25);

        lnameField = new JTextField();
        lnameField.setBounds(170, 138, 200, 25);

        emailField = new JTextField();
        emailField.setBounds(170, 198, 200, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 258, 200, 25);

        phoneField = new JTextField();
        phoneField.setBounds(170, 318, 200, 25);


        // JButtons
        registerButton = new JButton("Register");
        registerButton.setFont(buttonFont);
        registerButton.setBorder(null);
        registerButton.setFocusable(false);
        registerButton.setBounds(75, 370, 280, 30);
        registerButton.setHorizontalTextPosition(JButton.CENTER);
        registerButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(buttonFont);
        cancelButton.setBorder(null);
        cancelButton.setHorizontalTextPosition(JButton.CENTER);
        cancelButton.setFocusable(false);
        cancelButton.setBounds(75, 420, 280, 30);
        cancelButton.addActionListener(this);

        add(winTitle);
        add(fnameLabel);
        add(fnameField);
        add(lnameLabel);
        add(lnameField);
        add(emaiLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(phoneLabel);
        add(phoneField);
        add(registerButton);
        add(cancelButton);

        getContentPane().setBackground(new Color(0x123456));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, "root", "carl");
                Statement stmt = conn.createStatement();
                
                String fname = fnameField.getText().toLowerCase();
                String lname = lnameField.getText().toLowerCase();
                String email = emailField.getText().toLowerCase();
                char[] passuwad = passwordField.getPassword();
                String pwd = "";
                for (int i = 0; i < passuwad.length; i++) {
                    pwd+=passuwad[i];
                }
                String phoneNum = phoneField.getText();
                
                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pwd.isEmpty() || phoneNum.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Fill All Fields", "Failed",JOptionPane.ERROR_MESSAGE);
                    // this.dispose();
                } else {
                    String insert = "INSERT INTO Registration(first_name, last_name, email, password, phoneNum)";
                    String values = "VALUES(\""+fname+"\",\""+lname+"\",\""+email+"\",\""+pwd+"\",\""+phoneNum+"\")";
                    String sql = insert+values ;
                    
                    //execute statements
                    stmt.executeUpdate(sql);
                    registerButton.setEnabled(false);
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Registered", "Success",JOptionPane.INFORMATION_MESSAGE);
                }  
    
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new Gui();
    }

}
