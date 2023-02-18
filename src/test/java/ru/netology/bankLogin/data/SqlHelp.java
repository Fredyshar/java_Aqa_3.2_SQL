package ru.netology.bankLogin.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelp {
    private static QueryRunner runner = new QueryRunner();

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelp.VerificationCode getVerCode() {
        var codeSql = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var connection = getConnect()) {
            var code = runner.query(connection, codeSql, new ScalarHandler<String>());
            return new DataHelp.VerificationCode(code);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnect();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }
}
