package domain.functional.model;

import domain.functional.enums.Status;

import java.util.UUID;

public class Task {
    private final String taskId = UUID.randomUUID().toString();
    private String description;
    private String titre;
    private String taskList;
    private Status status;

    public Task(String description, String titre, String taskList, Status status) {
        this.description = description;
        this.titre = titre;
        this.taskList = taskList;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTaskList() {
        return taskList;
    }

    public void setTaskList(String taskList) {
        this.taskList = taskList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
