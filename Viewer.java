import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Viewer {
    private Controller controller;

    private LoginWindow loginWindow;

    public Viewer() {
        controller = new Controller(this);
        loginWindow = new LoginWindow(controller);
    }

    public LoginWindow getLoginWindow() {
        return loginWindow;
    }
}
