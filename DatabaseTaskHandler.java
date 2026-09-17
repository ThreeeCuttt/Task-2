import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTaskHandler implements ITaskHandler {

    @Override
    public boolean addTask(Task task) {
        String sql = """
                INSERT INTO task (
                    name,
                    description,
                    deadline,
                    priority,
                    status,
                    user_id
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());

            if (task.getDeadline() == null) {
                statement.setNull(3, Types.DATE);
            } else {
                statement.setDate(3, Date.valueOf(task.getDeadline()));
            }

            statement.setString(4, task.getPriority().name());
            statement.setString(5, task.getStatus().name());
            statement.setInt(6, task.getUserId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getInt(1));
                }
            }

            return true;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Не удалось сохранить задачу в БД",
                    exception
            );
        }
    }

    @Override
    public boolean removeTaskById(int taskId) {
        String sql = "DELETE FROM task WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, taskId);

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Не удалось удалить задачу из БД",
                    exception
            );
        }
    }

    @Override
    public boolean completeTask(int taskId) {
        String sql = "UPDATE task SET status = ? WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, Status.DONE.name());
            statement.setInt(2, taskId);

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Не удалось выполнить задачу",
                    exception
            );
        }
    }

    @Override
    public List<Task> getAllTasks() {
        String sql = """
                SELECT id, name, description, deadline, priority, status, user_id
                FROM task
                ORDER BY id
                """;

        List<Task> tasks = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Date deadlineDate = resultSet.getDate("deadline");

                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        deadlineDate == null ? null : deadlineDate.toLocalDate(),
                        Priority.valueOf(resultSet.getString("priority"))
                );

                task.setStatus(
                        Status.valueOf(resultSet.getString("status"))
                );

                task.setUserId(resultSet.getInt("user_id"));

                tasks.add(task);
            }

            return tasks;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Не удалось получить задачи из БД",
                    exception
            );
        }
    }
}