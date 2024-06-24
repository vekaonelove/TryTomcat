package service.impl;

import model.Order;
import pool.ConnectionPool;
import service.OrderService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder(Order order) {
        String sql = "INSERT INTO Orders (curator_id, client_id) VALUES ( ?, ?)";

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, order.getCurator().getId());
            preparedStatement.setInt(2, order.getClient().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}