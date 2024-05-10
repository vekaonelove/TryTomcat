package model.dao.impl;

import model.dao.ClientDao;
import model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlet.LoginServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());
    private static final String INSERT_CLIENT_SQL =
            "INSERT INTO clients (username, password) VALUES (?, ?)";

    public ClientDaoImpl() {
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/repet";
        String user = "root";
        String password = "1234";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addClient(Client client) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getUsername());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getFirstName());
            preparedStatement.setString(4, client.getLastName());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.executeUpdate();
            logger.info("Client added to the database: " + client.getUsername());
        } catch (SQLException e) {
            logger.error("Error while adding client to the database", e);
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("passwrd");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                clients.add(new Client(username, password, firstName, lastName, email));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return clients;
    }

    public Client getClientByUsernameAndPassword(String username, String password) {
        Client client = null;
        String SELECT_CLIENT_SQL = "SELECT * FROM clients WHERE username = ? AND passwrd = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_SQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                client = new Client(username, password, firstName, lastName, email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return client;
    }

    private void printSQLException(SQLException ex) {
        logger.error("SQLException: " + ex.getMessage());
    }
}