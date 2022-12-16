import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BannerPage extends JFrame implements ActionListener {

    JButton adminButton, userButton;
    JLabel winTitle, logonLabel;
    Font buttonFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 14);
    Font myFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 28);

    public BannerPage() {
        setVisible(true);
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Window Title
        winTitle = new JLabel("MASENO EVENT VIEWER");
        winTitle.setBounds(35, 20, 350, 30);
        winTitle.setFont(myFont);
        winTitle.setForeground(new Color(0, 255, 0));

        // Login-as
        logonLabel = new JLabel("Login As");
        logonLabel.setBounds(175, 70, 70, 30);
        logonLabel.setForeground(new Color(0, 255, 0));
        logonLabel.setFont(buttonFont);

        // Buttons
        userButton = new JButton("Student");
        userButton.setFont(buttonFont);
        userButton.setBorder(null);
        userButton.setFocusable(false);
        userButton.setBounds(110, 120, 200, 30);
        userButton.setHorizontalTextPosition(JButton.CENTER);
        userButton.addActionListener(this);

        adminButton = new JButton("Administrator");
        adminButton.setFont(buttonFont);
        adminButton.setBorder(null);
        adminButton.setHorizontalTextPosition(JButton.CENTER);
        adminButton.setFocusable(false);
        adminButton.setBounds(110, 170, 200, 30);
        adminButton.addActionListener(this);


        add(winTitle);
        add(logonLabel);
        add(userButton);
        add(adminButton);
        getContentPane().setBackground(new Color(0x123456));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton) {
            this.dispose();
            new Gui();
        }
        if (e.getSource() == adminButton) {
            this.dispose();
            new SearchResult();
        }

    }

    public static void main(String[] args) {
        new BannerPage();
    }

}
