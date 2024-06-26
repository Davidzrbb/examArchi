package adapter.server;

import domain.functional.enums.Status;
import domain.functional.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlitePersistenceMapper {
    public static Task fromResultSet(ResultSet resultSet) throws SQLException {
        return new Task(resultSet.getInt("id"),
                resultSet.getString("description"),
                Status.valueOf(resultSet.getString("status")),
                new java.util.Date(resultSet.getDate("createdAt").getTime()));
    }

    public static List<Task> fromResultSetList(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            tasks.add(fromResultSet(resultSet));
        }
        return tasks;
    }
}
