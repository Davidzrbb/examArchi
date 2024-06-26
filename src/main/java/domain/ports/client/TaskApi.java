package domain.ports.client;

import domain.functional.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskApi {
    void createTask(String description) throws Exception;

    void removeTask(Number id) throws Exception;

    Optional<List<Task>> retrieveAllTasks() throws Exception;

    void markAsDone(Number id) throws Exception;
}
