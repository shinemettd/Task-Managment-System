public class Task {
    private int id;
    private String user_login;
    private String name;
    private String description;
    private String status;
    private String creationDate;
    private String deadlineDate;

    public Task(int id, String user_login, String name, String description, String status, String creationDate, String deadlineDate) {
        this.id = id;
        this.user_login = user_login;
        this.name = name;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.deadlineDate = deadlineDate;
    }
}
