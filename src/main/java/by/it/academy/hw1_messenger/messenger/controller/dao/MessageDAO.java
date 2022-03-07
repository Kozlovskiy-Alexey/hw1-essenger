package by.it.academy.hw1_messenger.messenger.controller.dao;

import by.it.academy.hw1_messenger.messenger.controller.dao.api.IMessageDAO;
import by.it.academy.hw1_messenger.messenger.model.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO implements IMessageDAO {

    private final ConnectionDB connectionDB;
    private final String INSERT_MESSAGE_SQL = "INSERT INTO \"messenger\".message (toLogin, fromLogin, dateTime, \"message\")" +
            "VALUES (?, ?, ?, ?);";
    private final String GET_MESSAGE_SQL = "SELECT fromLogin, dateTime, \"message\" FROM \"messenger\".message WHERE tologin = ?;";

    public MessageDAO() {
        this.connectionDB = new ConnectionDB();
    }

    @Override
    public int insertMessage(String loginTo, Message message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String date = dateTimeFormatter.format(message.getDateTime());


        int rows = 0;
        try (Connection connection = connectionDB.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL);
            preparedStatement.setString(1, loginTo);
            preparedStatement.setString(2, message.getFromLogin());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(date));
            preparedStatement.setString(4, message.getMessage());
            rows = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Message> getMessage(String toLogin) {
        List<Message> messageList = new ArrayList<>();
        try (Connection connection = connectionDB.connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE_SQL);
            preparedStatement.setString(1, toLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String loginFrom = resultSet.getString("fromLogin");
                String messageText = resultSet.getString("message");
                String dateTime = resultSet.getString("dateTime");
                Timestamp timestamp = Timestamp.valueOf(dateTime);
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                messageList.add(new Message(localDateTime.truncatedTo(ChronoUnit.SECONDS), loginFrom, messageText));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messageList;
    }
}
