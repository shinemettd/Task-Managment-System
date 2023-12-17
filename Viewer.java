public class Viewer {
    private final LoginWindow loginWindow;
    private final RegisterWindow registerWindow;
    private final MainWindow mainWindow;

    public Viewer() {
        Controller controller = new Controller(this);
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