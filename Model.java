import javax.swing.*;
import java.util.ArrayList;

public class Model {
    private Viewer viewer;
    private String name;

    private String login;
    private String password;
    private LoginWindow loginWindow;
    private RegisterWindow registerWindow;
    private MainWindow mainWindow;
    private ArrayList<Task> tasksList;
    private String currentButtonActionNumber;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        this.loginWindow = viewer.getLoginWindow();
        this.registerWindow = viewer.getRegisterWindow();
        tasksList = new ArrayList<>();
    }

    public boolean signUp(String login, String password, String name) {
        return Repository.createUser(name, login, password);
    }

    private void signIn(String login, String password) {
        boolean successSignUp = Repository.logIn(login, password);
        if (successSignUp) {
            this.name = Repository.getUserName(login);
            this.login = login;
            this.password = password;
            System.out.println("Model: Success login");
            loginWindow.hideLoginWindow();
            loginWindow = null;
            mainWindow = viewer.getMainWindow();
            mainWindow.updateMainMenu();
            mainWindow.updateSettings();
            doAction("MainWindow");
        } else {
            loginWindow.showFailedSignInMessage();
        }
    }

    private void signOut() {
        login = null;
    }

    private void createTask() {

    }

    public void modifyTask() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doAction(String command) {
        if (command.contains("TaskButtonAction") && command.length() > 16) {
            currentButtonActionNumber = command.substring(16, command.length());
            doAction("TaskButtonAction");
        }
        switch (command) {
            case "SignIn" -> {
                System.out.println("Model: Sign in");
                loginWindow = viewer.getLoginWindow();
                signIn(loginWindow.getLogin(), loginWindow.getPassword());
            } case "SignUp" -> {
                System.out.println("Model: Sign up");
                viewer.getRegisterWindow().init();
                registerWindow = viewer.getRegisterWindow();
            } case "Register" -> {
                System.out.println("Model: Register");
                if (!(checkText(registerWindow.getLogin()))) {
                    System.out.println("Model: Incorrect type of login");
                    registerWindow.showIncorrectLoginError();
                    return;
                }
                if (!(checkText(registerWindow.getPassword()))) {
                    System.out.println("Model: Incorrect type of password");
                    registerWindow.showIncorrectPasswordError();
                    return;
                }
                if (!(registerWindow.comparePasswords())) {
                    System.out.println("Model: Passwords does not matches");
                    registerWindow.showFailedPasswordError();
                    return;
                }
                if (signUp(registerWindow.getLogin(), registerWindow.getPassword(), registerWindow.getName())) {
                    System.out.println("Model: Account registered");
                } else {
                    registerWindow.showFailedLoginError();
                    System.out.println("Model: Account not registered (Same login exists)");
                }
                doAction("BackToLogin");
                loginWindow = viewer.getLoginWindow();
                loginWindow.showUserLoginMessage();
            } case "BackToLogin" -> {
                System.out.println("Model: BackToLogin");
                registerWindow.setVisible(false);
                registerWindow.resetValues();
                registerWindow = null;
            } case "MainWindow" -> {
                System.out.println("Model: MainWindow");
                mainWindow = viewer.getMainWindow();
                mainWindow.updateMainMenu();
                mainWindow.showMainWindow();
            } case "MenuMainWindow" -> {
                System.out.println("Model: MenuMainWindow");
                mainWindow = viewer.getMainWindow();
                mainWindow.updateMainMenu();
                mainWindow.showMainMenu();
            } case "ProfileMainWindow" -> {
                System.out.println("Model: ProfileMainWindow");
                mainWindow = viewer.getMainWindow();
                mainWindow.updateSettings();
                mainWindow.showSettings();
            } case "LogoutMainWindow" -> {
                System.out.println("Model: LogoutWindowMenu");
                this.login = null;
                mainWindow.hideMainWindow();
                loginWindow = viewer.getLoginWindow();
                loginWindow.showLoginWindow();
            } case "AddTaskMainWindow" -> {
                System.out.println("Model: AddTaskMainWindow");
                mainWindow.showAddTask();
            } case "SaveSettings" -> {
                System.out.println("Model: SaveSettings");
                changePassword();
                setName(mainWindow.getNameFieldText());
                mainWindow.updateSettings();
            } case "TaskButtonAction" -> {
                System.out.println("Model: TaskButtonAction, task number = " + currentButtonActionNumber);
                System.out.println(login + "\nModel: Total tasks = " + Repository.getTasksCount(login));
                mainWindow.showMainMenu();
            }
        }
    }
    public void changePassword() {
        if (mainWindow.getOldPasswordFieldText().equals("")) {
            JOptionPane.showMessageDialog(null, "You must enter your current password to save changes!");
            return;
        }
        if (!(mainWindow.getOldPasswordFieldText().equals(password))) {
            JOptionPane.showMessageDialog(null, "Incorrect current password!");
            return;
        }
        if (!(mainWindow.getNewPasswordField().equals(mainWindow.getNewConfirmationPasswordField()))) {
            JOptionPane.showMessageDialog(null, "Incorrect new passwords!");
            return;
        }
        String newName = mainWindow.getNameFieldText();
        Repository.changeUserName(login, newName);
    }

    private boolean checkText(String text) {
        return !text.contains(" ") && !text.equals("");
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
