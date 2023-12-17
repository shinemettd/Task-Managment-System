import java.util.Date;

public class Task {
    private String userLogin;
    private int taskId;
    private String name;
    private String description;
    private Date creationDate;
    private String status;

    public Task(String userLogin, int taskId, String name, String description, Date creationDate, String status) {
        this.userLogin = userLogin;
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

}
