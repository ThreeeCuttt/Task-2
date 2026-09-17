import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    public User save(User user) {
        String sql = """
                INSERT INTO app_user (name, email)
                VALUES (?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }

            return user;

        } catch (SQLException exception) {
            throw new RuntimeException(
                    "Не удалось сохранить пользователя в БД",
                    exception
            );
        }
    }
}
