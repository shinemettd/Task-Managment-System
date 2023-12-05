public class Model {
    private Viewer viewer;
    private String login;
    private String password;

    private LoginWindow loginWindow;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        this.loginWindow = viewer.getLoginWindow();
    }

    public void signUp(String login, String password, String name) {
        boolean successSignIn = Repository.createUser(name, login, password);
        if (!successSignIn) {
            System.out.println("Model: Account wasn't created!");
        }
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
            case "SignIn":
                System.out.println("Model: Sign in");
                loginWindow = viewer.getLoginWindow();
                signIn(loginWindow.getLogin(), loginWindow.getPassword());
                break;
            case "SignUp":
                System.out.println("Model: Sign up");
                break;
        }
    }
}
