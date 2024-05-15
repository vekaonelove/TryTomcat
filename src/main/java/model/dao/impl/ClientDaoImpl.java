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

    public ClientDaoImpl() {
    }

    private Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database");

            String url = "jdbc:mysql://localhost:3307/repet";
            String user = "root";
            String password = "12345678Ab*";
            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static final String INSERT_CLIENT_SQL =
            "INSERT INTO clients (username, passwrd, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";

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
            logger.info("Added a client to the db: " + client.getUsername());
        } catch (SQLException e) {
            logger.error("Error: cannot add the client to the db", e);
        }
    }
    @Override
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

    @Override
    public void updateClientUsername(int clientId, String username) {
        String UPDATE_USERNAME_SQL = "UPDATE clients SET username = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERNAME_SQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateClientFirstName(int clientId, String firstName) {
        String UPDATE_FIRST_NAME_SQL = "UPDATE clients SET first_name = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FIRST_NAME_SQL)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateClientLastName(int clientId, String lastName) {
        String UPDATE_LAST_NAME_SQL = "UPDATE clients SET last_name = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LAST_NAME_SQL)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateClientPassword(int clientId, String password) {
        String UPDATE_PASSWORD_SQL = "UPDATE clients SET passwrd = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {

            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateClientEmail(int clientId, String email) {
        String UPDATE_EMAIL_SQL = "UPDATE clients SET email = ? WHERE id = ?"; //todo extract to constant

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMAIL_SQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, clientId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void deleteClient(int id) {
        String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }// todo one method to establish connection
    }

    private void printSQLException(SQLException ex) {
        logger.error("SQLException: " + ex.getMessage());
    }
}