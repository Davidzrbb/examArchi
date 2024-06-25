package domain.ports.data;

import domain.functional.model.Task;

import java.util.List;

public interface PersistencePort {
    List<Task> retrieveAllTasks();

    Task save(Task task);

    void remove(Task task);

    Number getLastTaskId();

    Task markAsDone(Number taskId);

    Task findTaskById(Number taskId);
}
