import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private final Controller controller;
    private final JPanel actionPanel;
    private final CardLayout cardLayout;
    private final Model model;
    private JTextField nameField;
    private JTextField loginField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField newConfirmationPasswordField;
    private JTextField newTaskName;
    private JTextArea newTaskDescription;
    private JRadioButton rButtonDoing;
    private JRadioButton rButtonPlanned;
    private JRadioButton rButtonDone;


    public MainWindow(Controller controller) {
        super("Task Management System");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        this.controller = controller;

        model = controller.getModel();

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(44, 44, 44));
        navigationPanel.setPreferredSize(new Dimension(getWidth(), 50));

        JButton menu = new JButton("All tasks");
        menu.addActionListener(controller);
        menu.setActionCommand("MenuMainWindow");
        menu.setRequestFocusEnabled(false);
        menu.setPreferredSize(new Dimension(200, 40));
        menu.setFont(new Font("", Font.BOLD, 18));
        menu.setForeground(new Color(212, 212, 212));
        menu.setBackground(new Color(44, 44, 44));

        JButton addTask = new JButton("Add Task");
        addTask.addActionListener(controller);
        addTask.setActionCommand("AddTaskMainWindow");
        addTask.setRequestFocusEnabled(false);
        addTask.setPreferredSize(new Dimension(200, 40));
        addTask.setFont(new Font("", Font.BOLD, 18));
        addTask.setForeground(new Color(212, 212, 212));
        addTask.setBackground(new Color(44, 44, 44));

        JButton profile = new JButton("Settings");
        profile.addActionListener(controller);
        profile.setActionCommand("ProfileMainWindow");
        profile.setRequestFocusEnabled(false);
        profile.setPreferredSize(new Dimension(200, 40));
        profile.setFont(new Font("", Font.BOLD, 18));
        profile.setForeground(new Color(212, 212, 212));
        profile.setBackground(new Color(44, 44, 44));

        JButton logOut = new JButton("Log out");
        logOut.addActionListener(controller);
        logOut.setActionCommand("LogoutMainWindow");
        logOut.setRequestFocusEnabled(false);
        logOut.setPreferredSize(new Dimension(200, 40));
        logOut.setFont(new Font("", Font.BOLD, 18));
        logOut.setForeground(new Color(212, 212, 212));
        logOut.setBackground(new Color(44, 44, 44));

        navigationPanel.add(menu);
        navigationPanel.add(addTask);
        navigationPanel.add(profile);
        navigationPanel.add(logOut);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(navigationPanel, BorderLayout.NORTH);

        actionPanel = new JPanel();
        cardLayout = new CardLayout();
        actionPanel.setLayout(cardLayout);

        JScrollPane tasksPanel = drawMainMenu();

        actionPanel.add(tasksPanel, "Tasks");

        JPanel addTaskPanel = drawAddTask();

        actionPanel.add(addTaskPanel, "AddTask");

        JPanel settingsPanel = drawSettings();

        actionPanel.add(settingsPanel, "Settings");

        JPanel currentTaskPanel = new JPanel();

        actionPanel.add(currentTaskPanel, "CurrentTask");

        mainPanel.add(actionPanel);

        add(mainPanel);
    }

    public JScrollPane drawMainMenu() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 25, 25));
        buttonPanel.setBackground(new Color(22, 22, 22));

        ArrayList<Task> tasksList = Repository.getTasksList(model.getLogin());
        model.setTasksList(tasksList);

        for (int i = 0; i < tasksList.size(); i++) {
            String taskName = tasksList.get(i).getName();
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
            button.setActionCommand("TaskButtonAction" + i);
            button.addActionListener(controller);
            button.setBounds(550, 500, 150, 50);
            button.setRequestFocusEnabled(false);
            button.setFont(new Font("", Font.BOLD, 36));
            if (tasksList.get(i).getStatus().equals("Done")) {
                button.setForeground(new Color(212, 212, 212));
                button.setBackground(new Color(79, 255, 79));
                button.setText(String.format("<html>%s<br> [Done] </html>", taskName));
            } else if (tasksList.get(i).getStatus().equals("Doing")) {
                button.setForeground(new Color(212, 212, 212));
                button.setBackground(new Color(255, 145, 57));
                button.setText(String.format("<html>%s<br> [Doing] </html>", taskName));
            } else {
                button.setForeground(new Color(212, 212, 212));
                button.setBackground(new Color(255, 60, 60));
                button.setText(String.format("<html>%s<br> [Planned] </html>", taskName));
            }
            button.setBorder(new LineBorder(new Color(212, 212, 212), 1));
            buttonPanel.add(button);
        }
        JButton lastButton = new JButton("New Task");
        lastButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
        lastButton.addActionListener(controller);
        lastButton.setActionCommand("AddTaskMainWindow");
        lastButton.setRequestFocusEnabled(false);
        lastButton.setFont(new Font("", Font.BOLD, 36));
        lastButton.setForeground(new Color(212, 212, 212));
        lastButton.setBackground(new Color(44, 44, 44));
        lastButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));
        buttonPanel.add(lastButton);
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    public void updateMainMenu() {
        JScrollPane updatedTasksPanel = drawMainMenu();

        actionPanel.remove(0);
        actionPanel.add(updatedTasksPanel, "Tasks", 0);

        revalidate();
        repaint();
    }

    public void drawCurrentTask(int taskIndex) {
        JPanel currentTaskPanel = new JPanel();
        currentTaskPanel.setForeground(new Color(212, 212, 212));
        currentTaskPanel.setBackground(new Color(44, 44, 44));;
        currentTaskPanel.setLayout(null);

        Task currentTask = model.getTaskList().get(taskIndex);

        JLabel taskNameLabel = new JLabel(currentTask.getName());
        taskNameLabel.setForeground(Color.WHITE);
        taskNameLabel.setBounds(0, 0, 100, 100);

        JLabel taskDescriptionLabel = new JLabel(currentTask.getDescription());
        taskDescriptionLabel.setForeground(Color.WHITE);
        taskDescriptionLabel.setBounds(0, 100, 100, 100);

        JLabel taskStatusLabel = new JLabel(currentTask.getStatus());
        taskStatusLabel.setForeground(Color.WHITE);
        taskStatusLabel.setBounds(0, 200, 100, 100);

        currentTaskPanel.add(taskNameLabel);
        currentTaskPanel.add(taskDescriptionLabel);
        currentTaskPanel.add(taskStatusLabel);

        actionPanel.remove(3);
        actionPanel.add(currentTaskPanel, "CurrentTask");
    }

    public JPanel drawSettings() {
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBackground(new Color(22, 22, 22));

        JLabel nameLabel = new JLabel("Your name");
        nameLabel.setBounds(385, 100, 100, 50);
        nameLabel.setForeground(Color.WHITE);

        nameField = new JTextField(model.getName() == null ? "-" : model.getName());
        nameField.setBounds(500, 100, 300, 50);
        nameField.setForeground(new Color(212, 212, 212));
        nameField.setBackground(new Color(44, 44, 44));
        nameField.setFont(new Font("", Font.PLAIN, 18));

        JLabel loginLabel = new JLabel("Your login");
        loginLabel.setBounds(385, 175, 100, 50);
        loginLabel.setForeground(Color.WHITE);

        loginField = new JTextField(model.getLogin());
        loginField.setBounds(500, 175, 300, 50);
        loginField.setEditable(false);
        loginField.setForeground(new Color(212, 212, 212));
        loginField.setBackground(new Color(44, 44, 44));
        loginField.setFont(new Font("", Font.PLAIN, 18));

        JLabel oldPasswordLabel = new JLabel("Current password");
        oldPasswordLabel.setBounds(385, 250, 115, 50);
        oldPasswordLabel.setForeground(Color.WHITE);

        oldPasswordField = new JPasswordField("");
        oldPasswordField.setBounds(500, 250, 300, 50);
        oldPasswordField.setForeground(new Color(212, 212, 212));
        oldPasswordField.setBackground(new Color(44, 44, 44));
        oldPasswordField.setFont(new Font("", Font.PLAIN, 18));

        JLabel newPasswordLabel = new JLabel("New password");
        newPasswordLabel.setBounds(385, 325, 100, 50);
        newPasswordLabel.setForeground(Color.WHITE);

        newPasswordField = new JPasswordField("");
        newPasswordField.setBounds(500, 325, 300, 50);
        newPasswordField.setForeground(new Color(212, 212, 212));
        newPasswordField.setBackground(new Color(44, 44, 44));
        newPasswordField.setFont(new Font("", Font.PLAIN, 18));

        JLabel confirmationPasswordLabel = new JLabel("Confirmation");
        confirmationPasswordLabel.setBounds(385, 400, 100, 50);
        confirmationPasswordLabel.setForeground(Color.WHITE);

        newConfirmationPasswordField = new JPasswordField("");
        newConfirmationPasswordField.setBounds(500, 400, 300, 50);
        newConfirmationPasswordField.setForeground(new Color(212, 212, 212));
        newConfirmationPasswordField.setBackground(new Color(44, 44, 44));
        newConfirmationPasswordField.setFont(new Font("", Font.PLAIN, 18));

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("SaveSettings");
        saveButton.addActionListener(controller);
        saveButton.setBounds(550, 500, 150, 50);
        saveButton.setRequestFocusEnabled(false);
        saveButton.setFont(new Font("", Font.BOLD, 18));
        saveButton.setForeground(new Color(212, 212, 212));
        saveButton.setBackground(new Color(44, 44, 44));
        saveButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));

        settingsPanel.add(nameField);
        settingsPanel.add(nameLabel);
        settingsPanel.add(loginField);
        settingsPanel.add(loginLabel);
        settingsPanel.add(oldPasswordField);
        settingsPanel.add(oldPasswordLabel);
        settingsPanel.add(newPasswordField);
        settingsPanel.add(newPasswordLabel);
        settingsPanel.add(newConfirmationPasswordField);
        settingsPanel.add(confirmationPasswordLabel);
        settingsPanel.add(saveButton);

        return settingsPanel;
    }

    public JPanel drawAddTask() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 44, 44));
        panel.setLayout(null);

        JLabel label = new JLabel("Creating new task");
        label.setBounds(540, 0, 200, 200);
        label.setForeground(new Color(212, 212, 212));
        label.setFont(new Font("", Font.PLAIN, 24));

        newTaskName = new JTextField("");
        newTaskName.setBounds(475, 175, 300, 50);
        newTaskName.setForeground(new Color(212, 212, 212));
        newTaskName.setBackground(new Color(44, 44, 44));
        newTaskName.setFont(new Font("", Font.PLAIN, 22));

        JLabel newTaskNameLabel = new JLabel("Title");
        newTaskNameLabel.setBounds(605, 100, 200, 100);
        newTaskNameLabel.setForeground(new Color(212, 212, 212));
        newTaskNameLabel.setFont(new Font("", Font.PLAIN, 24));

        newTaskDescription = new JTextArea("");
        newTaskDescription.setBounds(425, 300, 400, 100);
        newTaskDescription.setForeground(new Color(212, 212, 212));
        newTaskDescription.setBackground(new Color(44, 44, 44));
        newTaskDescription.setFont(new Font("", Font.PLAIN, 18));
        newTaskDescription.setLineWrap(true);
        newTaskDescription.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        JLabel newTaskDescriptionLabel = new JLabel("Description");
        newTaskDescriptionLabel.setBounds(570, 200, 200, 150);
        newTaskDescriptionLabel.setForeground(new Color(212, 212, 212));
        newTaskDescriptionLabel.setFont(new Font("", Font.PLAIN, 24));

        rButtonDoing = new JRadioButton("Doing");
        rButtonDoing.setActionCommand("ChangeActionToDoing");
        rButtonDoing.addActionListener(controller);
        rButtonDoing.setRequestFocusEnabled(false);
        rButtonDoing.setBounds(500, 400, 100, 100);
        rButtonDoing.setForeground(new Color(212, 212, 212));
        rButtonDoing.setBackground(new Color(44, 44, 44));

        rButtonPlanned = new JRadioButton("Planned");
        rButtonPlanned.setActionCommand("ChangeActionToPlanned");
        rButtonPlanned.addActionListener(controller);
        rButtonPlanned.setRequestFocusEnabled(false);
        rButtonPlanned.setBounds(600, 400, 100, 100);
        rButtonPlanned.setForeground(new Color(212, 212, 212));
        rButtonPlanned.setBackground(new Color(44, 44, 44));

        rButtonDone = new JRadioButton("Done");
        rButtonDone.setActionCommand("ChangeActionToPlanned");
        rButtonDone.addActionListener(controller);
        rButtonDone.setRequestFocusEnabled(false);
        rButtonDone.setBounds(700, 400, 100, 100);
        rButtonDone.setForeground(new Color(212, 212, 212));
        rButtonDone.setBackground(new Color(44, 44, 44));

        ButtonGroup rButtonGroup = new ButtonGroup();
        rButtonGroup.add(rButtonDoing);
        rButtonGroup.add(rButtonPlanned);
        rButtonGroup.add(rButtonDone);

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("SaveNewTask");
        saveButton.addActionListener(controller);
        saveButton.setBounds(550, 500, 150, 50);
        saveButton.setRequestFocusEnabled(false);
        saveButton.setFont(new Font("", Font.BOLD, 18));
        saveButton.setForeground(new Color(212, 212, 212));
        saveButton.setBackground(new Color(44, 44, 44));
        saveButton.setBorder(new LineBorder(new Color(212, 212, 212), 1));

        panel.add(label);
        panel.add(newTaskName);
        panel.add(newTaskNameLabel);
        panel.add(newTaskDescription);
        panel.add(newTaskDescriptionLabel);
        panel.add(rButtonDoing);
        panel.add(rButtonPlanned);
        panel.add(rButtonDone);
        panel.add(saveButton);

        return panel;
    }

    public String getNewTaskName() {
        return newTaskName.getText();
    }

    public String getNewTaskDescription() {
        return newTaskDescription.getText();
    }

    public void clearAddTask() {
        newTaskName.setText("");
        newTaskDescription.setText("");
        rButtonPlanned.setSelected(false);
        rButtonDoing.setSelected(false);
        rButtonDone.setSelected(false);
    }

    public boolean isAnyButtonSelected() {
        return rButtonDoing.isSelected() || rButtonPlanned.isSelected() || rButtonDone.isSelected();
    }

    public void updateSettings() {
        nameField.setText(model.getName());
        loginField.setText(model.getLogin());
        oldPasswordField.setText("");
        newPasswordField.setText("");
        newConfirmationPasswordField.setText("");
    }

    public void showMainMenu() {
        cardLayout.show(actionPanel, "Tasks");
    }

    public void showAddTask() {
        cardLayout.show(actionPanel, "AddTask");
    }
    public void showCurrentTask() {
        cardLayout.show(actionPanel, "CurrentTask");
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

    public String getNewPasswordField() {
        return new String(newPasswordField.getPassword());
    }

    public String getNewConfirmationPasswordField() {
        return new String(newConfirmationPasswordField.getPassword());
    }

    public String getOldPasswordFieldText() {
        return new String(oldPasswordField.getPassword());
    }
}