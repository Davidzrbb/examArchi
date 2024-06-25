package domain.port;

import domain.functional.model.Task;

import java.util.List;

public interface PersistencePort {
    List<Task> retrieveAllTasks();
    Task save(Task task);
    void remove(Task task);
}