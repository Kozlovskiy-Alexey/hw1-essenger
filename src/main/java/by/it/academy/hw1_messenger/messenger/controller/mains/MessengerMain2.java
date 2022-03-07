package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.controller.dao.MessageDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.UserDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.api.IMessageDAO;
import by.it.academy.hw1_messenger.messenger.controller.dao.api.IUserDAO;
import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.view.MessengerServiceDAO;
import by.it.academy.hw1_messenger.messenger.view.api.IMessengerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MessengerMain2 {
    public static void main(String[] args) {

        User user = new User("Vov1", "123", "Vladimir", "Tarasov", LocalDate.of(1984,01,21));

        IUserDAO userDAO = new UserDAO();
        IMessageDAO messageDAO = new MessageDAO();
        IMessengerService service = new MessengerServiceDAO();
//        int rows = userDAO.createUser(user);
//        System.out.println(rows);
        User vov1 = userDAO.getUser("Vo", "123");
        System.out.println(vov1);
        Message message = new Message(LocalDateTime.now(), "Alex", "Hello !!!");
        Message message1 = new Message(LocalDateTime.now(), "Vic", "Hello !!!");
        messageDAO.insertMessage("Alex", message1);
        List<Message> messageList = service.get("Alex");
        messageList.forEach(System.out::println);


    }
}
