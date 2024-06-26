package fr.esgi.domain.ports.client;

import fr.esgi.domain.functional.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskApi {
    boolean createTask(String description) throws Exception;

    boolean removeTask(Number id) throws Exception;

    Optional<List<Task>> retrieveAllTasks() throws Exception;

    boolean markAsDone(Number id) throws Exception;
}
