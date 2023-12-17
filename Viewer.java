public class Viewer {
    private Controller controller;

    private LoginWindow loginWindow;
    private RegisterWindow registerWindow;
    private MainWindow mainWindow;

    public Viewer() {
        controller = new Controller(this);
        loginWindow = new LoginWindow(controller);
        registerWindow = new RegisterWindow(controller);
        mainWindow = new MainWindow(controller);
    }

    public LoginWindow getLoginWindow() {
        return loginWindow;
    }

    public RegisterWindow getRegisterWindow() {
        return registerWindow;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
