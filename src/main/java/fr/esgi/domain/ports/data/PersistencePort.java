package fr.esgi.domain.ports.data;

import fr.esgi.domain.functional.model.Task;

import java.util.List;
import java.util.Optional;

public interface PersistencePort {
    Optional<List<Task>> retrieveAllTasks();

    boolean save(Task task);

    boolean remove(Task task);

    Optional<Number> getLastTaskId();
    
    Optional<Task> findTaskById(Number taskId);
}
