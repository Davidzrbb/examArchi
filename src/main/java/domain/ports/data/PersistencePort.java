package domain.ports.data;

import domain.functional.model.Task;

import java.util.List;
import java.util.Optional;

public interface PersistencePort {
    Optional<List<Task>> retrieveAllTasks();

    void save(Task task);

    void remove(Task task);

    Optional<Number> getLastTaskId();
    
    Optional<Task> findTaskById(Number taskId);
}
