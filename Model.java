public class Model {
    private Viewer viewer;
    private String login;
    private String password;
    private LoginWindow loginWindow;
    private RegisterWindow registerWindow;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        this.loginWindow = viewer.getLoginWindow();
        this.registerWindow = viewer.getRegisterWindow();
    }

    public boolean signUp(String login, String password, String name) {
        return Repository.createUser(name, login, password);
    }

    private void signIn(String login, String password) {
        boolean successSignUp = Repository.logIn(login, password);
        if (successSignUp) {
            this.login = login;
            this.password = password;
            System.out.println("Model: Success login");
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

    public void doAction(String command) {
        switch (command) {
            case "SignIn" -> {
                System.out.println("Model: Sign in");
                loginWindow = viewer.getLoginWindow();
                signIn(loginWindow.getLogin(), loginWindow.getPassword());
            }
            case "SignUp" -> {
                System.out.println("Model: Sign up");
                viewer.getRegisterWindow().init();
                registerWindow = viewer.getRegisterWindow();
            }
            case "Register" -> {
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
            }
            case "BackToLogin" -> {
                System.out.println("Model: BackToLogin");
                registerWindow.setVisible(false);
                registerWindow = null;
            }
        }
    }

    private boolean checkText(String text) {
        return !text.contains(" ") && !text.equals("");
    }
}
