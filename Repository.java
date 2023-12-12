import java.sql.*;

public class Repository {

    private static String databaseAddress = "jdbc:postgresql://localhost:5432/postgres";
    private static String databaseUser = "postgres";
    private static String databasePassword = "admin";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(databaseAddress, databaseUser, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDatabaseParameters(String dbAddress, String dbUser, String dbPassword) {
        databasePassword = dbPassword;
        databaseUser = dbUser;
        databaseAddress = dbPassword;
        try {
            connection = DriverManager.getConnection(databaseAddress, databaseUser, databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean createUser(String name, String login, String password) {
        try {
            String insertQuery = "INSERT INTO \"user\" " +
                    "(name, login, password) " +
                    "VALUES (?, ?, ?) " +
                    "ON CONFLICT DO NOTHING";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, login);
                preparedStatement.setString(3, password);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Repository: Success account creation");
                    return true;
                } else {
                    System.out.println("Repository: Failed account creation");
                    return false;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    public static void addTask(String user_login, String task_name, String task_description, String deadline) throws SQLException {
        if (task_name.length() >= 100) {
            task_name = task_name.substring(0, 96) + "...";
        }
        if (task_description.length() >= 2000) {
            task_description = task_description.substring(0, 1996) + "...";
        }
        if (deadline.length() > 10) {
            deadline = "1900-01-01";
        }
        String insertQuery = "INSERT INTO task (name, description, deadline, user_login)" +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, task_name);
            preparedStatement.setString(2, task_description);
            Date sqlDeadline = Date.valueOf(deadline);
            preparedStatement.setDate(3, sqlDeadline);
            preparedStatement.setString(4, user_login);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Repository: Success task creation");
            } else {
                System.out.println("Repository: Failed task creation");
            }
        }
    }

    public static void removeTask(String login, int task_id) {
        String insertQuery = "DELTE FROM task " +
                "WHERE user_login = ? AND task_id = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, task_id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Repository: Success task deletion");
            } else {
                System.out.println("Repository: Failed task deletion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskName(String login, int task_id, String newName) {
        String updateQuery = "UPDATE task SET name = ? WHERE task_id = ? AND user_login = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, task_id);
            preparedStatement.setString(3, login);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Repository: Success name rewrite");
            } else {
                System.out.println("Repository: Failed name rewrite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskDescription(String login, int task_id, String newDescription) {
        String updateQuery = "UPDATE task SET description = ? WHERE task_id = ? AND user_login  = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, task_id);
            preparedStatement.setString(3, login);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Repository: Success description rewrite");
            } else {
                System.out.println("Repository: Failed description rewrite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskDeadline(String login, int task_id, String newDeadline) {
        String updateQuery = "UPDATE task SET deadline = ? WHERE task_id = ? AND user_login  = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            Date sqlDeadline = Date.valueOf(newDeadline);
            preparedStatement.setDate(1, sqlDeadline);
            preparedStatement.setInt(2, task_id);
            preparedStatement.setString(3, login);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Repository: Success deadline rewrite");
            } else {
                System.out.println("Repository: Failed deadline rewrite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskStatus(String login, int task_id, String newStatus) {
        if (!(newStatus.equals("Planned") || newStatus.equals("Doing") || newStatus.equals("Done"))) {
            System.out.println("Repository: Incorrect type of status");
            return;
        }
        String updateQuery = "UPDATE task SET status = ? WHERE task_id = ? AND user_login = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, task_id);
            preparedStatement.setString(3, login);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Repository: Success status rewrite");
            } else {
                System.out.println("Repository: Failed status rewrite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean logIn(String login, String password) {
        String findUser = "SELECT * FROM \"user\" WHERE login = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(findUser)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Repository: Success login");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Repository: Failed login");
        return false;
    }

    public static String getUserName(String login) {
        String selectName = "SELECT name FROM \"user\" WHERE login = ?";
        String name = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectName)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    name = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}