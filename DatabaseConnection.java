import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/task_manager";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres";

    private DatabaseConnection() {
        throw new UnsupportedOperationException(
                "Это утилитный класс!"
        );
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
