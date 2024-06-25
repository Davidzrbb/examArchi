package domain.functional.model;

import domain.functional.enums.Status;

import java.util.Date;

public class Task {
    private Number taskId;
    private String description;
    private Status status;
    private final Date createdAt = new Date();

    public Task(Number taskId, String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setTaskId(Number taskId) {
        this.taskId = taskId;
    }

    public Number getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
