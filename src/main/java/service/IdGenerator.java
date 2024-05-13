package service;

import java.sql.*;

public class IdGenerator {
    private static int currentId;

    public static void initialize() {
        String SELECT_MAX_ID_SQL = "SELECT MAX(id) AS max_id FROM clients";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/repet", "root", "12345678Ab*");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                currentId = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int increment(){
        return ++currentId;
    }
}