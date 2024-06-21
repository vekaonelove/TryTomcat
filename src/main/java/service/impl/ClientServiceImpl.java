package service.impl;

import model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;
import service.ClientService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class.getName());

    @Override
        public boolean checkAdmin(String username, String password) {
            // Implement your logic here
            return false;
        }

        @Override
        public String extractRole(String username) {
            // Implement your logic here to get the role of the user from the database
            return null;
        }


    private static final String INSERT_CLIENT_SQL =
            "INSERT INTO clients (username, passwrd, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addClient(Client client) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getUsername());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getFirstName());
            preparedStatement.setString(4, client.getLastName());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.executeUpdate();
            logger.info("Added a client to the db: " + client.getUsername());
        } catch (SQLException e) {
            logger.error("Error: cannot add the client to the db", e);
        }
    }
    @Override
    public List<Client> extractAllClients() {
        List<Client> clients = new ArrayList<>();
        String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients";
        try (Connection connection = ConnectionPool.getConnection();
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

    public Client extractClientByUsernameAndPassword(String username, String password) {
        Client client = null;
        String SELECT_CLIENT_SQL =
                "SELECT * FROM clients WHERE username = ? AND passwrd = ?";

        try (Connection connection = ConnectionPool.getConnection();
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


    public void updateClient(Client client) {
        String UPDATE_CLIENT_SQL =
                "UPDATE clients SET username = ?, passwrd = ?, first_name = ?," +
                        " last_name = ?, email = ? WHERE id = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {

            preparedStatement.setString(1, client.getUsername());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getFirstName());
            preparedStatement.setString(4, client.getLastName());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.setInt(6, client.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }



    @Override
    public void deleteClient(int id) {
        String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE id = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        logger.error("SQLException: " + ex.getMessage());
    }
}
