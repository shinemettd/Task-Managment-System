import javax.swing.*;
import java.util.ArrayList;

public class Model {
    private final Viewer viewer;
    private String name;

    private String login;
    private String password;
    private LoginWindow loginWindow;
    private RegisterWindow registerWindow;
    private MainWindow mainWindow;
    private ArrayList<Task> tasksList;
    private String currentButtonActionNumber;
    private String newTaskStatus;
    private int currentTaskId;

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
            System.out.println(Observer.getActionCounter() + ") Model: Success login");
            loginWindow.hideLoginWindow();
            loginWindow.hideLoginMessage();
            loginWindow = null;
            mainWindow = viewer.getMainWindow();
            mainWindow.updateMainMenu();
            mainWindow.updateSettings();
            doAction("MainWindow");

        } else {
            loginWindow.showFailedSignInMessage();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doAction(String command) {
        if (command.contains("TaskButtonAction") && command.length() > 16) {
            currentButtonActionNumber = command.substring(16);
            doAction("TaskButtonAction");
        }
        switch (command) {
            case "SignIn" -> {
                System.out.println(Observer.getActionCounter() + ") Model: Sign in");
                loginWindow = viewer.getLoginWindow();
                signIn(loginWindow.getLogin(), loginWindow.getPassword());
            } case "SignUp" -> {
                System.out.println(Observer.getActionCounter() +  ") Model: Sign up");
                viewer.getRegisterWindow().init();
                registerWindow = viewer.getRegisterWindow();
            } case "Register" -> {
                System.out.println(Observer.getActionCounter() + ") Model: Register");
                if (!(checkText(registerWindow.getLogin()))) {
                    System.out.println(Observer.getActionCounter() + ") Model: Incorrect type of login");
                    registerWindow.showIncorrectLoginError();
                    return;
                }
                if (!(checkText(registerWindow.getPassword()))) {
                    System.out.println(Observer.getActionCounter() + ") Model: Incorrect type of password");
                    registerWindow.showIncorrectPasswordError();
                    return;
                }
                if (!(registerWindow.comparePasswords())) {
                    System.out.println(Observer.getActionCounter() + ") Model: Passwords does not matches");
                    registerWindow.showFailedPasswordError();
                    return;
                }
                if (signUp(registerWindow.getLogin(), registerWindow.getPassword(), registerWindow.getName())) {
                    System.out.println(Observer.getActionCounter() + ") Model: Account registered");
                } else {
                    registerWindow.showFailedLoginError();
                    System.out.println(Observer.getActionCounter() + ") Model: Account not registered (Same login exists)");
                }
                doAction("BackToLogin");
                loginWindow = viewer.getLoginWindow();
                loginWindow.showUserLoginMessage();
            } case "BackToLogin" -> {
                System.out.println(Observer.getActionCounter() + ") Model: BackToLogin");
                registerWindow.setVisible(false);
                registerWindow.resetValues();
                registerWindow = null;
            } case "MainWindow" -> {
                System.out.println(Observer.getActionCounter() + ") Model: MainWindow");
                mainWindow = viewer.getMainWindow();
                mainWindow.updateMainMenu();
                mainWindow.showMainWindow();
            } case "MenuMainWindow" -> {
                System.out.println(Observer.getActionCounter() + ") Model: MenuMainWindow");
                mainWindow = viewer.getMainWindow();
                mainWindow.updateMainMenu();
                mainWindow.showMainMenu();
            } case "ProfileMainWindow" -> {
                System.out.println(Observer.getActionCounter() + ") Model: ProfileMainWindow");
                mainWindow = viewer.getMainWindow();
                mainWindow.updateSettings();
                mainWindow.showSettings();
            } case "LogoutMainWindow" -> {
                System.out.println(Observer.getActionCounter() + ") Model: LogoutWindowMenu");
                this.login = null;
                mainWindow.hideMainWindow();
                loginWindow = viewer.getLoginWindow();
                loginWindow.showLoginWindow();
            } case "AddTaskMainWindow" -> {
                System.out.println(Observer.getActionCounter() + ") Model: AddTaskMainWindow");
                mainWindow.showAddTask();
            } case "SaveSettings" -> {
                System.out.println(Observer.getActionCounter() + ") Model: SaveSettings");
                changePassword();
                setName(mainWindow.getNameFieldText());
                mainWindow.updateSettings();
            } case "TaskButtonAction" -> {
                System.out.println(Observer.getActionCounter() + ") Model: TaskButtonAction");
                mainWindow.drawCurrentTask(Integer.parseInt(currentButtonActionNumber));
                mainWindow.showCurrentTask();
            } case "SaveNewTask" -> {
                System.out.println(Observer.getActionCounter() + ") Model: SaveNewTask");
                checkValidnessAddTaskAndSave();
            } case "ChangeActionToDoing" -> {
                System.out.println(Observer.getActionCounter() + ") Model: ChangeActionToDoing");
                newTaskStatus = "Doing";
            } case "ChangeActionToPlanned" -> {
                System.out.println(Observer.getActionCounter() + ") Model: ChangeActionToPlanned");
                newTaskStatus = "Planned";
            } case "ChangeActionToDone" -> {
                System.out.println(Observer.getActionCounter() + ") Model: ChangeActionToDone");
                newTaskStatus = "Done";
            } case "EditCurrentTask" -> {
                System.out.println(Observer.getActionCounter() + ") Model: EditCurrentTask");
                mainWindow.makeEditableCurrentTask(true);
            } case "SaveCurrentTask" -> {
                System.out.println(Observer.getActionCounter() + ") Model: SaveCurrentTask");
                String newTaskName = mainWindow.getCurrentTaskNameField();
                if (newTaskName.equals("")) {
                    JOptionPane.showMessageDialog(null, "You must enter new task title!");
                    System.out.println(Observer.getActionCounter() + ") Model: EditCurrentTask (newTaskName have no value)");
                    return;
                }
                Repository.changeTaskName(login, currentTaskId, newTaskName);
                String newTaskDescription = mainWindow.getCurrentTaskDescriptionField();
                Repository.changeTaskDescription(login, currentTaskId, newTaskDescription);
                String newTaskStatus = mainWindow.getCurrentChosenTaskStatus();
                Repository.changeTaskStatus(login, currentTaskId, newTaskStatus);
                mainWindow.makeEditableCurrentTask(false);
            } case "DeleteCurrentTask" -> {
                System.out.println(Observer.getActionCounter() + ") Model: DeleteCurrentTask (awaiting confirmation)");
                int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete task?", "Deleting task confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.out.println(Observer.getActionCounter() + ") Model: DeleteCurrentTask (user confirmed deletion)");
                    Repository.removeTask(login, currentTaskId);
                    doAction("MenuMainWindow");
                } else {
                    System.out.println(Observer.getActionCounter() + ") Model: DeleteCurrentTask (user canceled deletion)");
                    return;
                }
            }
        }
    }

    public void checkValidnessAddTaskAndSave() {
        if (mainWindow.getNewTaskName().equals("")) {
            System.out.println(Observer.getActionCounter() + ") Model: checkValidnessAddTaskAndSave (newTaskName have no value)");
            JOptionPane.showMessageDialog(null, "You must enter task title!");
            return;
        }
        if (!(mainWindow.isAnyButtonSelected())) {
            System.out.println(Observer.getActionCounter() + ") Model: checkValidnessAddTaskAndSave (no button was selected)");
            JOptionPane.showMessageDialog(null, "You must select one of the statuses!");
            return;
        }
        String taskName = mainWindow.getNewTaskName();
        String taskDescription = mainWindow.getNewTaskDescription();
        Repository.addTask(login, taskName, taskDescription, newTaskStatus);
        mainWindow.updateMainMenu();
        mainWindow.showMainMenu();
        mainWindow.clearAddTask();
    }
    public void changePassword() {
        if (mainWindow.getOldPasswordFieldText().equals("")) {
            System.out.println(Observer.getActionCounter() + ") Model: changePassword (password wasn't entered)");
            JOptionPane.showMessageDialog(null, "You must enter your current password to save changes!");
            return;
        }
        if (!(mainWindow.getOldPasswordFieldText().equals(password))) {
            System.out.println(Observer.getActionCounter() + ") Model: changePassword (incorrect current password)");
            JOptionPane.showMessageDialog(null, "Incorrect current password!");
            return;
        }
        if (!(mainWindow.getNewPasswordField().equals(mainWindow.getNewConfirmationPasswordField()))) {
            System.out.println(Observer.getActionCounter() + ") Model: changePassword (new passwords are not the same)");
            JOptionPane.showMessageDialog(null, "Incorrect new passwords!");
            return;
        }
        if (!(mainWindow.getOldPasswordFieldText().equals(""))) {
            System.out.println(Observer.getActionCounter() + ") Model: changePassword");
            JOptionPane.showMessageDialog(null, "Your changes was saved!");
            String newPassword = mainWindow.getOldPasswordFieldText();
            Repository.changeUserPassword(login, newPassword);
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

    public void setTasksList(ArrayList<Task> tasksList) {
        this.tasksList = new ArrayList<>(tasksList);
    }

    public ArrayList<Task> getTaskList() {
        return tasksList;
    }

    public void setCurrentId(int taskId) {
        currentTaskId = taskId;
    }
}
