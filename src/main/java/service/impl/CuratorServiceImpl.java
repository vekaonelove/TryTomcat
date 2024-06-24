package service.impl;

import model.Curator;
import model.ExperienceLevel;
import model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pool.ConnectionPool;
import service.CuratorService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuratorServiceImpl implements CuratorService {
    private static final Logger logger = LogManager.getLogger(CuratorServiceImpl.class.getName());

    @Override
    public void addCurator(Curator curator) {
        String INSERT_CURATOR_SQL = "INSERT INTO curators (name, surname, subjects, experience, email, photo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CURATOR_SQL)) {

            preparedStatement.setString(1, curator.getName());
            preparedStatement.setString(2, curator.getSurname());
            preparedStatement.setString(3, curator.getSubjects().name());
            preparedStatement.setString(4, curator.getExperience().name());
            preparedStatement.setString(5, curator.getEmail());
            InputStream photo = curator.getPhoto();
            if (photo != null) {
                preparedStatement.setBlob(6, photo);
            } else {
                preparedStatement.setNull(6, java.sql.Types.BLOB);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error: cannot add the curator to the db", e);
        }
    }

    @Override
    public void updateCurator(Curator curator) {
        String UPDATE_CURATOR_SQL = "UPDATE curators SET name = ?, surname = ?, subjects = ?, experience = ?, email = ?, photo = ? WHERE id = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CURATOR_SQL)) {

            preparedStatement.setString(1, curator.getName());
            preparedStatement.setString(2, curator.getSurname());
            preparedStatement.setString(3, curator.getSubjects().toString());
            preparedStatement.setString(4, curator.getExperience().toString());
            preparedStatement.setString(5, curator.getEmail());
            preparedStatement.setBlob(6, curator.getPhoto());
            preparedStatement.setInt(7, curator.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error: cannot update the curator in the db", e);
        }
    }

    @Override
    public void deleteCurator(int id) {
        String DELETE_CURATOR_SQL = "DELETE FROM curators WHERE id = ?";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CURATOR_SQL)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error: cannot delete the curator from the db", e);
        }
    }

    @Override
    public List<Curator> getAllCurators() {
        List<Curator> curators = new ArrayList<>();
        String SELECT_ALL_CURATORS_SQL = "SELECT * FROM curators";

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CURATORS_SQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Subject subjects = Subject.valueOf(resultSet.getString("subjects"));
                ExperienceLevel experience = ExperienceLevel.valueOf(resultSet.getString("experience"));
                String email = resultSet.getString("email");
                InputStream photo = resultSet.getBlob("photo").getBinaryStream();

                Curator curator = new Curator(name, surname, subjects, experience, email, photo);
                curator.setId(id);
                curators.add(curator);
            }

        } catch (SQLException e) {
            logger.error("Error: cannot retrieve curators from the db", e);
        }

        return curators;
    }

    public Curator extractCuratorById(int id) {
        String SELECT_CURATOR_BY_ID_SQL = "SELECT * FROM curators WHERE id = ?";
        Curator curator = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURATOR_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    Subject subjects = Subject.valueOf(resultSet.getString("subjects"));
                    ExperienceLevel experience = ExperienceLevel.valueOf(resultSet.getString("experience"));
                    String email = resultSet.getString("email");
                    InputStream photo = resultSet.getBlob("photo").getBinaryStream();

                    curator = new Curator(name, surname, subjects, experience, email, photo);
                    curator.setId(id);
                }
            }

        } catch (SQLException e) {
            logger.error("Error: cannot retrieve curator from the db", e);
        }
        return curator;
    }
}