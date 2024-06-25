package domain.functional.model;

import java.util.List;
import java.util.UUID;

public class TaskList {
    private final String taskListId = UUID.randomUUID().toString();
    private String title;
    private String userId;
    private List<Task> tasks;

    public TaskList(String title, String userId, List<Task> tasks) {
        this.title = title;
        this.userId = userId;
        this.tasks = tasks;
    }

    public String getTaskListId() {
        return taskListId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public void printTaskList() {
        System.out.println("TaskList{" +
                "taskListId='" + taskListId + '\'' +
                ", title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                ", tasks=" + tasks +
                '}');
    }
}

