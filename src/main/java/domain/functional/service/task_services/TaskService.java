package domain.functional.service.task_services;

import domain.functional.enums.Status;
import domain.functional.model.Task;
import domain.functional.service.validation.TaskValidation;
import domain.ports.client.TaskApi;
import domain.ports.data.PersistencePort;

import java.util.List;

public class TaskService implements TaskApi {
    private final PersistencePort persistencePort;

    private static final int INITIAL_TASK_ID = 1;
    private static final String INVALID_DESCRIPTION_MESSAGE = "Invalid description";
    private static final String TASK_DOES_NOT_EXIST_MESSAGE = "Task does not exist.";

    public TaskService(PersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Task createTask(String description) {
        if (!TaskValidation.validateDescription(description)) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION_MESSAGE);
        }
        Number taskId = persistencePort.getLastTaskId();
        Number nextTaskId = (TaskValidation.validateId(taskId)) ? taskId.intValue() + 1 : INITIAL_TASK_ID;
        Task task = new Task(nextTaskId, description, Status.TODO);
        return persistencePort.save(task);
    }

    @Override
    public void removeTask(Number taskId) {
        Task task = persistencePort.findTaskById(taskId);
        if (!TaskValidation.validateTask(task)) {
            throw new IllegalArgumentException(TASK_DOES_NOT_EXIST_MESSAGE);
        }
        persistencePort.remove(task);
        Number lastTaskId = persistencePort.getLastTaskId();
        updateTaskIdsAfterRemoval(taskId, lastTaskId);
    }

    private void updateTaskIdsAfterRemoval(Number taskId, Number lastTaskId) {
        int taskIdValue = taskId.intValue();
        int lastTaskIdValue = lastTaskId.intValue();
        if (lastTaskIdValue > taskIdValue) {
            for (int i = taskIdValue + 1; i <= lastTaskIdValue; i++) {
                Task taskToUpdate = persistencePort.findTaskById(i);
                if (taskToUpdate != null) {
                    taskToUpdate.setTaskId(i - 1);
                    persistencePort.save(taskToUpdate);
                }
            }
        }
    }

    @Override
    public List<Task> retrieveAllTasks() {
        return persistencePort.retrieveAllTasks();
    }

    @Override
    public Task markAsDone(Number id) {
        Task task = persistencePort.findTaskById(id);
        if (!TaskValidation.validateTask(task)) {
            throw new IllegalArgumentException(TASK_DOES_NOT_EXIST_MESSAGE);
        }
        task.setStatus(Status.DONE);
        return persistencePort.save(task);
    }
}
