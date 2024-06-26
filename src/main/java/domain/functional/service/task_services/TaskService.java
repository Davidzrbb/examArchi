package domain.functional.service.task_services;

import domain.functional.enums.Status;
import domain.functional.model.Task;
import domain.functional.service.validation.TaskValidation;
import domain.ports.client.TaskApi;
import domain.ports.data.PersistencePort;

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
    public void createTask(String description) {
        if (!TaskValidation.validateDescription(description)) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_MESSAGE);
        }
        Number nextTaskId = persistencePort.getLastTaskId()
                .filter(TaskValidation::validateId)
                .map(id -> id.intValue() + 1)
                .orElse(INITIAL_TASK_ID);
        Task task = new Task(nextTaskId, description, Status.TODO);
        persistencePort.save(task);
    }

    @Override
    public void removeTask(Number taskId) {
        Task task = persistencePort.findTaskById(taskId)
                .filter(TaskValidation::validateTask)
                .orElseThrow(() -> new IllegalArgumentException(TASK_DOES_NOT_EXIST_MESSAGE));
        persistencePort.remove(task);
        Number lastTaskId = persistencePort.getLastTaskId().orElse(INITIAL_TASK_ID - 1);
        updateTaskIdsAfterRemoval(taskId, lastTaskId);
    }

    private void updateTaskIdsAfterRemoval(Number taskId, Number lastTaskId) {
        int taskIdValue = taskId.intValue();
        int lastTaskIdValue = lastTaskId.intValue();
        if (lastTaskIdValue > taskIdValue) {
            for (int i = taskIdValue + 1; i <= lastTaskIdValue; i++) {
                int index = i;
                persistencePort.findTaskById(i).ifPresent(taskToUpdate -> {
                    taskToUpdate.setTaskId(index - 1);
                    persistencePort.save(taskToUpdate);
                });
            }
        }
    }

    @Override
    public Optional<List<Task>> retrieveAllTasks() {
        return persistencePort.retrieveAllTasks();
    }

    @Override
    public void markAsDone(Number id) {
        Task task = persistencePort.findTaskById(id)
                .filter(TaskValidation::validateTask)
                .orElseThrow(() -> new IllegalArgumentException(TASK_DOES_NOT_EXIST_MESSAGE));
        task.setStatus(Status.DONE);
        persistencePort.save(task);
    }
}
