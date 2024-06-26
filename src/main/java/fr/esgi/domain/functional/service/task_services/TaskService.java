package fr.esgi.domain.functional.service.task_services;

import fr.esgi.domain.functional.enums.Status;
import fr.esgi.domain.functional.model.Task;
import fr.esgi.domain.functional.service.validation.TaskValidation;
import fr.esgi.domain.ports.client.TaskApi;
import fr.esgi.domain.ports.data.PersistencePort;

import java.util.List;
import java.util.Optional;

public class TaskService implements TaskApi {
    private final PersistencePort persistencePort;

    private static final int INITIAL_TASK_ID = 1;
    private static final String INVALID_DESCRIPTION_MESSAGE = "Invalid description";
    private static final String TASK_DOES_NOT_EXIST_MESSAGE = "Task does not exist.";

    public TaskService(PersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public boolean createTask(String description) {
        if (!TaskValidation.validateDescription(description)) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_MESSAGE);
        }
        Number nextTaskId = persistencePort.getLastTaskId()
                .filter(TaskValidation::validateId)
                .map(id -> id.intValue() + 1)
                .orElse(INITIAL_TASK_ID);
        Task task = new Task(nextTaskId, description, Status.TODO);
        return persistencePort.save(task);
    }

    @Override
    public boolean removeTask(Number taskId) {
        Task task = persistencePort.findTaskById(taskId)
                .filter(TaskValidation::validateTask)
                .orElseThrow(() -> new IllegalArgumentException(TASK_DOES_NOT_EXIST_MESSAGE));
        return persistencePort.remove(task);
    }

    @Override
    public Optional<List<Task>> retrieveAllTasks() {
        return persistencePort.retrieveAllTasks();
    }

    @Override
    public boolean markAsDone(Number id) {
        Task task = persistencePort.findTaskById(id)
                .filter(TaskValidation::validateTask)
                .orElseThrow(() -> new IllegalArgumentException(TASK_DOES_NOT_EXIST_MESSAGE));
        return persistencePort.updateStatus(task.getTaskId(), Status.DONE);
    }
}
