package adapter;

import domain.functional.model.Task;
import domain.port.PersistencePort;

import java.util.List;

public class SqlitePersistenceAdapter implements PersistencePort {
    @Override
    public List<Task> retrieveAllTasks() {
        return List.of();
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public void remove(Task task) {

    }
}
