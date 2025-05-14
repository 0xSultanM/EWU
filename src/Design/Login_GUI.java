package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Logic.Login.CheckFromFiles;
import Logic.Login.Account_Create;

public class Login_GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Login_GUI() {
        // Initialize the main JFrame
        setTitle("Altas Fitness - Daily Fitness Partner");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set icon
        ImageIcon icon = new ImageIcon(Login_GUI.class.getResource("/Design/images/Calling_Card.png"));
        setIconImage(icon.getImage());

        // Set the background color and layout
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // Initialize CardLayout and cardPanel for switching views
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add panels to the cardPanel
        JPanel logInPanel = LogInFrame();
        JPanel signUpPanel = SignUpFrame();

        cardPanel.add(logInPanel, "LogIn");
        cardPanel.add(signUpPanel, "Sign Up");

        // Add cardPanel to the JFrame
        add(cardPanel, BorderLayout.CENTER);

        // Create login and create account buttons
        JButton logBtn = new JButton("Log In");
        JButton createBtn = new JButton("Sign Up");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(logBtn);
        buttonPanel.add(createBtn);

        // Add the button panel to the JFrame
        add(buttonPanel, BorderLayout.SOUTH);

        // Show the main frame
        setVisible(true);

        // Action listeners for buttons
        logBtn.addActionListener(e -> cardLayout.show(cardPanel, "LogIn"));
        createBtn.addActionListener(e -> cardLayout.show(cardPanel, "Sign Up"));
    }

    private JPanel LogInFrame() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(6, 7, 20));
        loginPanel.setLayout(null);

        JLabel Title = new JLabel("Atlas Fitness - LogIn ");
        Font titleFont = new Font("SansSerif", Font.BOLD, 32);
        Title.setFont(titleFont);
        Title.setBounds(175,25,500,50);
        Title.setForeground(new Color(255, 215, 0));

        // Create components for the Login panel

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Log In");

        userLabel.setBounds(200, 150, 80, 25);
        userLabel.setForeground(new Color(255, 215, 0));
        passLabel.setBounds(200, 180, 80, 25);
        passLabel.setForeground(new Color(255, 215, 0));
        usernameField.setBounds(280, 150, 150, 25);
        passwordField.setBounds(280, 180, 150, 25);
        loginBtn.setBounds(260, 220, 90, 25);

        loginPanel.add(Title);
        loginPanel.add(userLabel);
        loginPanel.add(passLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordField);
        loginPanel.add(loginBtn);

        // Action for Login Button
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            boolean success = CheckFromFiles.loginAccount(user, pass);
            new ResultFrame(success ? "Log in Successful. Welcome " + user : "Login Failed, Please try again.");

            if (success) {
                dispose(); // Close Login window
                SwingUtilities.invokeLater(() -> new Main_GUI()); // Open Main GUI
            }
        });

        return loginPanel;
    }

    private JPanel SignUpFrame() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(6, 7, 20));
        signUpPanel.setLayout(null);

        JLabel Title = new JLabel("Atlas Fitness - Sign Up ");
        Font titleFont = new Font("SansSerif", Font.BOLD, 32);
        Title.setFont(titleFont);
        Title.setBounds(135,25,500,50);
        Title.setForeground(new Color(255, 215, 0));

        // Create components for the SignUp panel

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton createBtn = new JButton("Create");

        userLabel.setBounds(200, 150, 80, 25);
        userLabel.setForeground(new Color(255, 215, 0));
        passLabel.setBounds(200, 180, 80, 25);
        passLabel.setForeground(new Color(255, 215, 0));
        usernameField.setBounds(280, 150, 150, 25);
        passwordField.setBounds(280, 180, 150, 25);
        createBtn.setBounds(260, 220, 90, 25);

        signUpPanel.add(Title);
        signUpPanel.add(userLabel);
        signUpPanel.add(passLabel);
        signUpPanel.add(usernameField);
        signUpPanel.add(passwordField);
        signUpPanel.add(createBtn);

        // Action for Create Button
        createBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            boolean success = Account_Create.createAccount(user, pass);
            new ResultFrame(success ? "Account created!" : "Account already exists or error.");
        });

        return signUpPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_GUI());
    }
}
