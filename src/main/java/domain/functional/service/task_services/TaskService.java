package domain.functional.service.task_services;

import domain.functional.enums.Status;
import domain.functional.model.Task;
import domain.ports.client.TaskApi;
import domain.ports.data.PersistencePort;

import java.util.List;

public class TaskService implements TaskApi {
    private final PersistencePort persistencePort;

    public TaskService(PersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Task createTask(String description) throws Exception {
        return persistencePort.save(new Task(null, description, Status.TODO));
    }

    @Override
    public void removeTask(Number id) throws Exception {

    }

    @Override
    public List<Task> getAllTasks() throws Exception {
        return List.of();
    }

    @Override
    public Task markAsDone(Number id) throws Exception {
        return null;
    }
}
