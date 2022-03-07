package by.it.academy.hw1_messenger.messenger.controller.dao;

import by.it.academy.hw1_messenger.messenger.controller.dao.api.IUserDAO;
import by.it.academy.hw1_messenger.messenger.model.User;

import java.sql.*;
import java.time.LocalDate;

public class UserDAO implements IUserDAO {

    private final ConnectionDB connectionDB;
    private final String INSERT_USER_SQL = "INSERT INTO \"messenger\".user (login, password, firstName, lastName, birthday) " +
            "VALUES (?, ?, ?, ?, ?);";
    private final String GET_USER_SQL = "SELECT * FROM \"messenger\".user WHERE login = ? AND password = ?;";
    private final String CHECK_USER_SQL = "SELECT login FROM \"messenger\".user WHERE login = ?;";

    public UserDAO() {
        this.connectionDB = new ConnectionDB();
    }

    @Override
    public int createUser(User user) {
        int rows = 0;
        try (Connection connection = connectionDB.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthday()));
            rows = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public User getUser(String login, String password) {
        User user = null;
        try (Connection connection = connectionDB.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                LocalDate birthday = LocalDate.parse(String.valueOf(resultSet.getDate("birthday")));
                user = new User(login, password, firstName, lastName, birthday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean checkUser(String login) {
       boolean isExist = false;
        try (Connection connection = connectionDB.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_SQL)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }
}
