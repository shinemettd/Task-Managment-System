import java.sql.*;

public class Repository {
    public static boolean createUser(String name, String login, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

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
                    System.out.println("Success account creation");
                    return true;
                } else {
                    System.out.println("Failed account creation");
                    return false;
                }
            }
        } catch (SQLException sqle) {
            System.out.println("th");
            sqle.printStackTrace();
        }
        return false;
    }

    public static void addTask(String user_login, String task_name, String task_description, String deadline) {
        if (task_name.length() >= 100) {
            task_name = task_name.substring(0, 96) + "...";
        }
        if (task_description.length() >= 2000) {
            task_description = task_description.substring(0, 1996) + "...";
        }
        if (deadline.length() > 10) {
            deadline = "1900-01-01";
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

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
                    System.out.println("Success task creation");
                } else {
                    System.out.println("Failed task creation");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeTask(String login, int task_id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

            String insertQuery = "delete from task " +
                    "where user_login = ? and task_id = ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, login);
                preparedStatement.setInt(2, task_id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Success task deletion");
                } else {
                    System.out.println("Failed task deletion");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskName(String login, int task_id, String newName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

            String updateQuery = "update task set name = ? where task_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, task_id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Success name rewrite");
                } else {
                    System.out.println("Failed name rewrite");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskDescription(String login, int task_id, String newDescription) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

            String updateQuery = "update task set description = ? where task_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newDescription);
                preparedStatement.setInt(2, task_id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Success description rewrite");
                } else {
                    System.out.println("Failed description rewrite");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskDeadline(String login, int task_id, String newDeadline) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

            String updateQuery = "update task set deadline = ? where task_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                Date sqlDeadline = Date.valueOf(newDeadline);
                preparedStatement.setDate(1, sqlDeadline);
                preparedStatement.setInt(2, task_id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Success deadline rewrite");
                } else {
                    System.out.println("Failed deadline rewrite");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeTaskStatus(String login, int task_id, String newStatus) {
        if (!(newStatus.equals("Planned") || newStatus.equals("Doing") || newStatus.equals("Done"))) {
            System.out.println("Incorrect type of status");
            return;
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

            String updateQuery = "update task set status = ? where task_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newStatus);
                preparedStatement.setInt(2, task_id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Success status rewrite");
                } else {
                    System.out.println("Failed status rewrite");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean logIn(String login, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

            String findUser = "select * from \"user\" where login = ? and password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(findUser)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Success login");
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Failed login");
        return false;
    }
}
