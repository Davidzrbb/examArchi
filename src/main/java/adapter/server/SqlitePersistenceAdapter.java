package adapter.server;

import domain.functional.model.Task;
import domain.ports.data.PersistencePort;

import java.sql.*;
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
            return Optional.of(SqlitePersistenceMapper.fromResultSetList(rs));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean save(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into tasks values (?, ?, ?, ?);");
            statement.setInt(1, task.getTaskId().intValue());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus().name());
            statement.setDate(4, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.executeUpdate();
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }

    @Override
    public boolean remove(Task task) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from tasks where id is ?");
            statement.setInt(1, task.getTaskId().intValue());
            statement.executeUpdate();
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }

    @Override
    public Optional<Number> getLastTaskId() {
        try {
            PreparedStatement statement = connection.prepareStatement("select max(id) from tasks");
            ResultSet rs = statement.executeQuery();
            int id = rs.getInt(1);
            return Optional.of(id);
        } catch (SQLException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Task> findTaskById(Number taskId) {
        try {
            PreparedStatement statement = connection.prepareStatement("select from tasks where id is ?");
            statement.setInt(1, taskId.intValue());
            ResultSet rs = statement.executeQuery();

            return Optional.of(SqlitePersistenceMapper.fromResultSet(rs));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
