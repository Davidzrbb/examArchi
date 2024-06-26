package fr.esgi.adapter.server;

import fr.esgi.domain.functional.enums.Status;
import fr.esgi.domain.functional.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlitePersistenceMapper {
    public static Task fromResultSet(ResultSet resultSet) throws SQLException {
        return new Task(resultSet.getInt("id"),
                resultSet.getString("description"),
                Status.valueOf(resultSet.getString("status")),
                resultSet.getTimestamp("createdAt").toLocalDateTime());
    }

    public static List<Task> fromResultSetList(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            tasks.add(fromResultSet(resultSet));
        }
        return tasks;
    }
}
