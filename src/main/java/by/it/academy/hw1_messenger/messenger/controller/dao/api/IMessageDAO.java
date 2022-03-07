package by.it.academy.hw1_messenger.messenger.controller.dao.api;

import by.it.academy.hw1_messenger.messenger.model.Message;

import java.util.List;

public interface IMessageDAO {

    int insertMessage(String loginTo, Message message);

    List<Message> getMessage(String toLogin);
}
