package domain.ports.client;

import domain.functional.model.Task;

import java.util.List;

public interface TaskApi {
    Task createTask(String description) throws Exception;

    void removeTask(Number id) throws Exception;

    List<Task> retrieveAllTasks() throws Exception;

    Task markAsDone(Number id) throws Exception;
}
