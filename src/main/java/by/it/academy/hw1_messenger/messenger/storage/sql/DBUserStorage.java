package by.it.academy.hw1_messenger.messenger.storage.sql;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.sql.api.SQLInitializer;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class DBUserStorage implements IUserStorage {
    private static volatile DBUserStorage instance;
    private final SQLInitializer initializer = SQLInitializer.getInstance();

    private DBUserStorage() {

    }

    public static DBUserStorage getInstance() {
        DBUserStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DBUserStorage.class) {
            if (instance == null) {
                instance = new DBUserStorage();
            }
        }
        return instance;
    }

    @Override
    public User get(String login) {
        User user = null;
        String sql = "SELECT\n" +
                "    login,\n" +
                "    password,\n" +
                "    firstname,\n" +
                "    lastname,\n" +
                "    birthday,\n" +
                "    registration\n" +
                "FROM\n" +
                "    messenger.user\n" +
                "WHERE\n" +
                "    login = ?;";
        try (Connection connection = initializer.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birthday = rs.getObject("birthday", LocalDate.class);
                LocalDateTime registration = rs.getObject("registration", LocalDateTime.class);
                user = new User(login, password, firstname, lastname, birthday, registration);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("оибка выполнения SQL запроса при получении user", e);
        }
        return user;
    }

    @Override
    public void add(User user) {
        User result = this.get(user.getLogin());
        if (result != null) {
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже существует");
        }

        DateTimeFormatter bDayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthday = bDayFormatter.format(user.getBirthday());
        DateTimeFormatter regFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String registration = regFormatter.format(user.getRegistration());

        String sql = "INSERT INTO messenger.user (login, password, firstname, lastname, birthday, registration)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = initializer.getDataSource().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setDate(5, Date.valueOf(birthday));
            ps.setTimestamp(6, Timestamp.valueOf(registration));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка выполенния SQL запроса при добавлении user", e);
        }
    }

    @Override
    public Collection<User> getAll() {
        User user = null;
        Collection<User> users = new ArrayList<>();
        String sql = "SELECT\n" +
                "    login,\n" +
                "    password,\n" +
                "    firstname,\n" +
                "    lastname,\n" +
                "    birthday,\n" +
                "    registration\n" +
                "FROM\n" +
                "    messenger.user;";
        try (Connection connection = initializer.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                LocalDate birthday = rs.getObject("birthday", LocalDate.class);
                LocalDateTime registration = rs.getObject("registration", LocalDateTime.class);
                user = new User(login, password, firstname, lastname, birthday, registration);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("оибка выполнения SQL запроса при получении писка users", e);
        }
        return users;
    }

    @Override
    public long getCount() {
        long count = 0;
        String sql = "SELECT\n" +
                "    COUNT(login)\n" +
                "FROM\n" +
                "    messenger.user;";
        try (Connection connection = initializer.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getLong("count");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка выполенния SQL", e);
        }
        return count;
    }
}
