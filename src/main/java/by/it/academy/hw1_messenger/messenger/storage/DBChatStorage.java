package by.it.academy.hw1_messenger.messenger.storage;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DBChatStorage implements IChatStorage {

    private static volatile DBChatStorage instance;
    private final IUserStorage userStorage;
    private final DBInitializer initializer;

    private DBChatStorage() {
         this.initializer = DBInitializer.getInstance();
         this.userStorage = DBUserStorage.getInstance();
    }

    public static DBChatStorage getInstance() {
        DBChatStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DBChatStorage.class) {
            if (instance == null) {
                instance = new DBChatStorage();
            }
        }
        return instance;
    }

    @Override
    public List<Message> get(String login) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT\n" +
                "    fromlogin,\n" +
                "    datetime,\n" +
                "    message\n" +
                "FROM\n" +
                "    messenger.message\n" +
                "WHERE toLogin = ?;";
        try (Connection connection = initializer.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fromLogin = rs.getString("fromlogin");
                LocalDateTime datetime = rs.getObject("datetime", LocalDateTime.class);
                String text = rs.getString("message");
                Message message = new Message(datetime, fromLogin, text);
                messages.add(message);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка выполенния SQL", e);
        }
        return messages;
    }

    @Override
    public void addMessage(String login, Message message) {
        User user = userStorage.get(login);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь с таким логином не найден!");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String date = formatter.format(message.getDateTime());

        String sql = "INSERT INTO messenger.message (tologin, fromlogin, datetime, message)\n" +
                "    VALUES (?, ?, ?, ?);";
        try (Connection connection = initializer.getDataSource().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, login);
        ps.setString(2, message.getFromLogin());
        ps.setTimestamp(3, Timestamp.valueOf(date));
        ps.setString(4, message.getText());
        ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Ошибка выполенния SQL", e);
        }
    }

    @Override
    public long getCount() {
        long count = 0;
        String sql = "SELECT\n" +
                "    COUNT(toLogin)\n" +
                "FROM\n" +
                "    messenger.message;";
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
