package adapter.server;

import domain.functional.enums.Status;
import domain.functional.model.Task;
import domain.ports.data.PersistencePort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePersistenceAdapter implements PersistencePort {

    private final Connection connection;

    public SqlitePersistenceAdapter(String connectionString) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionString);
        this.connection = connection;
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table if not exists tasks (id integer, description string, status string, createdAt date)");
    }

    @Override
    public Optional<List<Task>> retrieveAllTasks() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from tasks");
            List<Task> tasks = new ArrayList<>();
            while(rs.next())
            {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("description"),
                        Status.valueOf(rs.getString("status")),
                        new java.util.Date(rs.getDate("createdAt").getTime())
                );
                tasks.add(task);
            }
            return Optional.of(tasks);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void save(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into tasks values (?, ?, ?, ?);");
            statement.setInt(1, task.getTaskId().intValue());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus().name());
            statement.setDate(4, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: manage here}
        }
    }

    @Override
    public void remove(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from tasks where id is ?");
            statement.setInt(1, task.getTaskId().intValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: manage here
        }
    }

    @Override
        public Optional<Number> getLastTaskId() {
        try {
            PreparedStatement statement = connection.prepareStatement("select max(id) from tasks");
            ResultSet rs = statement.executeQuery();
            return Optional.of(rs.getInt(0));
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: manage here
        }
        return Optional.empty();
    }

    @Override
    public Optional<Task> findTaskById(Number taskId) {
        try {
            PreparedStatement statement = connection.prepareStatement("select from tasks where id is ?");
            statement.setInt(1, taskId.intValue());
            ResultSet rs = statement.executeQuery();

            return Optional.of(new Task(
                    rs.getInt("id"),
                    rs.getString("description"),
                    Status.valueOf(rs.getString("status")),
                    new java.util.Date(rs.getDate("createdAt").getTime())
            ));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
