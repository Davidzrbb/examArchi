package domain.functional.service.task_services;

import domain.functional.enums.Status;
import domain.functional.model.Task;
import domain.functional.service.validation.TaskValidation;
import domain.ports.client.TaskApi;
import domain.ports.data.PersistencePort;

import java.util.List;

public class TaskService implements TaskApi {
    private final PersistencePort persistencePort;

    public TaskService(PersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Task createTask(String title) throws Exception {
        if (!TaskValidation.validateTitle(title)) {
            throw new Exception("Invalid title");
        }
        Number taskId = persistencePort.getLastTaskId();
        Number nextTaskId = 1;
        if (TaskValidation.validateId(taskId)) {
            nextTaskId = taskId.intValue() + 1;
        }
        return persistencePort.save(new Task(nextTaskId, title, Status.TODO));
    }

    @Override
    public void removeTask(Number id) throws Exception {

    }

    @Override
    public List<Task> retrieveAllTasks() throws Exception {
        return persistencePort.retrieveAllTasks();
    }

    @Override
    public Task markAsDone(Number id) throws Exception {
        return persistencePort.markAsDone(id);
    }
}
