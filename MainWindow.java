import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Controller controller;
    private JPanel mainPanel;
    private JPanel navigationPanel;

    private JPanel actionPanel;

    private CardLayout cardLayout;

    private Model model;

    private JTextField nameField;

    private JTextField loginField;
    private JPasswordField passwordField;

    public MainWindow(Controller controller) {
        super("Task Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        this.controller = controller;

        model = controller.getModel();

        navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.LIGHT_GRAY);
        navigationPanel.setPreferredSize(new Dimension(getWidth(), 50)); // height navbag

        JButton menu = new JButton("Menu");
        menu.addActionListener(controller);
        menu.setActionCommand("MenuMainWindow");

        JButton addTask = new JButton("Add Task");
        addTask.addActionListener(controller);
        addTask.setActionCommand("AddTaskMainWindow");

        JButton profile = new JButton("Profile");
        profile.addActionListener(controller);
        profile.setActionCommand("ProfileMainWindow");

        JButton logOut = new JButton("Log out");
        logOut.addActionListener(controller);
        logOut.setActionCommand("LogoutMainWindow");

        navigationPanel.add(menu);
        navigationPanel.add(addTask);
        navigationPanel.add(profile);
        navigationPanel.add(logOut);

        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(navigationPanel, BorderLayout.NORTH);

        actionPanel = new JPanel();
        cardLayout = new CardLayout();
        actionPanel.setLayout(cardLayout);

        JScrollPane tasksPanel = drawMainMenu();

        actionPanel.add(tasksPanel, "Tasks");

        JPanel addTaskPanel = drawTask();

        actionPanel.add(addTaskPanel, "AddTask");

        JPanel settingsPanel = drawSettings();

        actionPanel.add(settingsPanel, "Settings");

        mainPanel.add(actionPanel);

        add(mainPanel);
    }

    public JScrollPane drawMainMenu() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 25, 25));

        for (int i = 0; i < 14; i++) {
            JButton button = new JButton("Button " + (i + 1));
            button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
            buttonPanel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    public JPanel drawTask() {
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Task!");

        panel.add(label);

        return panel;
    }

    public JPanel drawSettings() {
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(null);

        nameField = new JTextField(model.getName() == null ? "-" : model.getName());
        nameField.setBounds(50, 100, 300, 50);

        loginField = new JTextField(model.getLogin());
        loginField.setBounds(50, 175, 300, 50);
        loginField.setEditable(false);

        passwordField = new JPasswordField(model.getPassword());
        passwordField.setBounds(50, 250, 300, 50);

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("SaveSettings");
        saveButton.addActionListener(controller);
        saveButton.setBounds(200, 350, 150, 50);

        settingsPanel.add(nameField);
        settingsPanel.add(loginField);
        settingsPanel.add(passwordField);
        settingsPanel.add(saveButton);

        return settingsPanel;
    }

    public void updateSettings() {
        nameField.setText(model.getName());
        loginField.setText(model.getLogin());
        passwordField.setText(model.getPassword());
    }

    public void showMainMenu() {
        cardLayout.show(actionPanel, "Tasks");
    }

    public void showAddTask() {
        cardLayout.show(actionPanel, "AddTask");
    }

    public void showSettings() {
        cardLayout.show(actionPanel, "Settings");
    }

    public void showMainWindow() {
        setVisible(true);
    }

    public void hideMainWindow() {
        setVisible(false);
    }

    public String getNameFieldText() {
        return nameField.getText();
    }

    public String getPasswordFieldText() {
        return new String(passwordField.getPassword());
    }
}