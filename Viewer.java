import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Viewer {
    private Controller controller;

    private LoginWindow loginWindow;
    private RegisterWindow registerWindow;

    public Viewer() {
        controller = new Controller(this);
        loginWindow = new LoginWindow(controller);
        registerWindow = new RegisterWindow(controller);
    }

    public LoginWindow getLoginWindow() {
        return loginWindow;
    }

    public RegisterWindow getRegisterWindow() {
        return registerWindow;
    }
}
