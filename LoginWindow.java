import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField errorField;
    private JTextField loginMessage;
    private final Controller controller;

    public LoginWindow(Controller controller) {
        super("Task Manager");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(22, 22, 22));

        JLabel login = new JLabel("Login:");
        login.setBounds(50, 25, 100, 50);
        login.setForeground(Color.WHITE);

        panel.add(login);
        usernameField = new JTextField();
        usernameField.setBounds(125, 25, 200, 40);
        usernameField.setForeground(new Color(212, 212, 212));
        usernameField.setBackground(new Color(44, 44, 44));
        usernameField.setFont(new Font("", Font.PLAIN, 18));
        panel.add(usernameField);

        JLabel password = new JLabel("Password:");
        password.setBounds(50, 75, 100, 50);
        password.setForeground(Color.WHITE);
        panel.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(125, 75, 200, 40);
        passwordField.setForeground(new Color(212, 212, 212));
        passwordField.setBackground(new Color(44, 44, 44));
        passwordField.setFont(new Font("", Font.PLAIN, 18));
        panel.add(passwordField);

        JButton loginButton = new JButton("Sign in");
        loginButton.setBounds(125, 150, 200, 40);
        loginButton.setRequestFocusEnabled(false);
        loginButton.setFont(new Font("", Font.BOLD, 18));
        loginButton.setForeground(new Color(212, 212, 212));
        loginButton.setBackground(new Color(44, 44, 44));
        loginButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));
        this.controller = controller;
        loginButton.addActionListener(controller);
        loginButton.setActionCommand("SignIn");
        panel.add(loginButton);

        JButton registerButton = new JButton("Sign up");
        registerButton.setBounds(125, 200, 200, 40);
        registerButton.setRequestFocusEnabled(false);
        registerButton.setFont(new Font("", Font.BOLD, 18));
        registerButton.setForeground(new Color(212, 212, 212));
        registerButton.setBackground(new Color(44, 44, 44));
        registerButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));
        registerButton.addActionListener(controller);
        registerButton.setActionCommand("SignUp");
        panel.add(registerButton);

        errorField = new JTextField();
        errorField.setBounds(150, 115, 200, 30);
        errorField.setEditable(false);
        errorField.setText("Invalid login or password!");
        errorField.setForeground(new Color(255, 65, 51));
        errorField.setBorder(null);
        errorField.setBackground(new Color(22, 22, 22));
        errorField.setVisible(false);
        panel.add(errorField);

        loginMessage = new JTextField();
        loginMessage.setBounds(75, 115, 400, 30);
        loginMessage.setEditable(false);
        loginMessage.setText("Registration completed! Enter your login and password");
        loginMessage.setForeground(new Color(41, 255, 48));
        loginMessage.setBorder(null);
        loginMessage.setBackground(new Color(22, 22, 22));
        loginMessage.setVisible(false);
        panel.add(loginMessage);

        add(panel);

        setVisible(true);
    }

    public String getLogin() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void showFailedSignInMessage() {
        loginMessage.setVisible(false);
        errorField.setVisible(true);
        new Timer(10000, e -> errorField.setVisible(false));
    }
    public void showUserLoginMessage() {
        errorField.setVisible(false);
        loginMessage.setVisible(true);
        new Timer(10000, e -> loginMessage.setVisible(false));
    }

    public void hideLoginWindow() {
        setVisible(false);
        usernameField.setText("");
        passwordField.setText("");
    }

    public void showLoginWindow() {
        setVisible(true);
        usernameField.setText("");
        passwordField.setText("");
    }

    public void hideLoginMessage() {
        loginMessage.setVisible(false);
    }
}
