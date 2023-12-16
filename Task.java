import java.util.Date;

public class Task {
    private String userLogin;
    private int taskId;
    private String name;
    private String description;
    private Date creationDate;
    private Date deadline;
    private String status;

    public Task(String userLogin, int taskId, String name, String description, Date creationDate, String status) {
        this.userLogin = userLogin;
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
