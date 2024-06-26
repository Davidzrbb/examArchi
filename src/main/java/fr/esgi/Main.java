package fr.esgi;

import fr.esgi.adapter.server.SqlitePersistenceAdapter;
import fr.esgi.controller.resource.TaskResource;
import fr.esgi.domain.functional.service.task_services.TaskService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SqlitePersistenceAdapter adapter = new SqlitePersistenceAdapter("jdbc:sqlite:test.db");
        TaskService taskService = new TaskService(adapter);
        TaskResource taskResource = new TaskResource(taskService);
        taskResource.dispatch(args);
    }
}