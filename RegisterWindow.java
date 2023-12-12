import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame {
    private final Controller controller;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField extraPasswordField;
    private JTextField errorLoginExists;
    private JTextField errorIncorrectLoginField;
    private JTextField errorPasswordField;
    private JTextField errorIncorrectPasswordField;

    public RegisterWindow(Controller controller) {
        super("Sign up");
        this.controller = controller;
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 435);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(22, 22, 22));

        JLabel name = new JLabel("Your name:");
        name.setBounds(25, 25, 100, 50);
        name.setForeground(Color.WHITE);

        panel.add(name);

        nameField = new JTextField();
        nameField.setBounds(140, 25, 200, 40);
        nameField.setForeground(new Color(212, 212, 212));
        nameField.setBackground(new Color(44, 44, 44));
        nameField.setFont(new Font("", Font.PLAIN, 18));
        panel.add(nameField);

        JLabel login = new JLabel("Your login:");
        login.setBounds(25, 75, 100, 50);
        login.setForeground(Color.WHITE);
        panel.add(login);

        errorLoginExists = new JTextField();
        errorLoginExists.setBounds(170, 115, 200, 30);
        errorLoginExists.setEditable(false);
        errorLoginExists.setText("That login already exists!");
        errorLoginExists.setForeground(new Color(255, 65, 51));
        errorLoginExists.setBorder(null);
        errorLoginExists.setBackground(new Color(22, 22, 22));
        errorLoginExists.setVisible(false);
        panel.add(errorLoginExists);

        errorIncorrectLoginField = new JTextField();
        errorIncorrectLoginField.setBounds(170, 115, 200, 30);
        errorIncorrectLoginField.setEditable(false);
        errorIncorrectLoginField.setText("Incorrect type of login!");
        errorIncorrectLoginField.setForeground(new Color(255, 65, 51));
        errorIncorrectLoginField.setBorder(null);
        errorIncorrectLoginField.setBackground(new Color(22, 22, 22));
        errorIncorrectLoginField.setVisible(false);
        panel.add(errorIncorrectLoginField);

        usernameField = new JTextField();
        usernameField.setBounds(140, 75, 200, 40);
        usernameField.setForeground(new Color(212, 212, 212));
        usernameField.setBackground(new Color(44, 44, 44));
        usernameField.setFont(new Font("", Font.PLAIN, 18));
        panel.add(usernameField);

        JLabel password = new JLabel("Your password:");
        password.setBounds(25, 145, 100, 50);
        password.setForeground(Color.WHITE);
        panel.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 145, 200, 40);
        passwordField.setForeground(new Color(212, 212, 212));
        passwordField.setBackground(new Color(44, 44, 44));
        passwordField.setFont(new Font("", Font.PLAIN, 18));
        panel.add(passwordField);

        JLabel secondPassword = new JLabel("Confirm password:");
        secondPassword.setBounds(25, 195, 150, 50);
        secondPassword.setForeground(Color.WHITE);
        panel.add(secondPassword);

        extraPasswordField = new JPasswordField();
        extraPasswordField.setBounds(140, 195, 200, 40);
        extraPasswordField.setForeground(new Color(212, 212, 212));
        extraPasswordField.setBackground(new Color(44, 44, 44));
        extraPasswordField.setFont(new Font("", Font.PLAIN, 18));
        panel.add(extraPasswordField);

        errorPasswordField = new JTextField();
        errorPasswordField.setBounds(170, 235, 200, 30);
        errorPasswordField.setEditable(false);
        errorPasswordField.setText("Passwords does not match!");
        errorPasswordField.setForeground(new Color(255, 65, 51));
        errorPasswordField.setBorder(null);
        errorPasswordField.setBackground(new Color(22, 22, 22));
        errorPasswordField.setVisible(false);
        panel.add(errorPasswordField);

        errorIncorrectPasswordField = new JTextField();
        errorIncorrectPasswordField.setBounds(170, 235, 200, 30);
        errorIncorrectPasswordField.setEditable(false);
        errorIncorrectPasswordField.setText("Incorrect type of password!");
        errorIncorrectPasswordField.setForeground(new Color(255, 65, 51));
        errorIncorrectPasswordField.setBorder(null);
        errorIncorrectPasswordField.setBackground(new Color(22, 22, 22));
        errorIncorrectPasswordField.setVisible(false);
        panel.add(errorIncorrectPasswordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 270, 200, 50);
        registerButton.setRequestFocusEnabled(false);
        registerButton.setFont(new Font("", Font.BOLD, 18));
        registerButton.setForeground(new Color(212, 212, 212));
        registerButton.setBackground(new Color(44, 44, 44));
        registerButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));
        registerButton.addActionListener(controller);
        registerButton.setActionCommand("Register");
        panel.add(registerButton);


        JButton cancelButton = new JButton("Back");
        cancelButton.setBounds(100, 330, 200, 50);
        cancelButton.setRequestFocusEnabled(false);
        cancelButton.setFont(new Font("", Font.BOLD, 18));
        cancelButton.setForeground(new Color(212, 212, 212));
        cancelButton.setBackground(new Color(44, 44, 44));
        cancelButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));
        cancelButton.addActionListener(controller);
        cancelButton.setActionCommand("BackToLogin");
        panel.add(cancelButton);

        add(panel);

        setVisible(true);
    }

    public String getName() {
        return nameField.getText();
    }
    public String getLogin() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public boolean comparePasswords() {
        String password = new String (passwordField.getPassword());
        String secondPassword = new String (extraPasswordField.getPassword());
        return (password.equals(secondPassword));
    }

    public void showFailedLoginError() {
        errorIncorrectLoginField.setVisible(false);
        errorLoginExists.setVisible(true);
        Timer timer = new Timer(10000, e -> errorLoginExists.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
    public void showFailedPasswordError() {
        errorIncorrectPasswordField.setVisible(false);
        errorPasswordField.setVisible(true);
        Timer timer = new Timer(10000, e -> errorPasswordField.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
    public void showIncorrectPasswordError() {
        errorPasswordField.setVisible(false);
        errorIncorrectPasswordField.setVisible(true);
        Timer timer = new Timer(10000, e -> errorIncorrectPasswordField.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }

    public void showIncorrectLoginError() {
        errorLoginExists.setVisible(false);
        errorIncorrectLoginField.setVisible(true);
        Timer timer = new Timer(10000, e -> errorIncorrectLoginField.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }

    public void resetValues() {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        extraPasswordField.setText("");
    }
}

