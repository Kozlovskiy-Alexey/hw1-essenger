package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.sql.DBUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

public class MessengerMain1 {
    public static void main(String[] args) {

        User user1 = new User("BadBaby", "123", "Vasia", "Belenko",
                LocalDate.of(2000, 10, 25), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        IUserStorage userStorage = DBUserStorage.getInstance();

        userStorage.add(user1);
        Collection<User> all = userStorage.getAll();
        all.forEach(System.out::println);

        long count = userStorage.getCount();
        System.out.println("Всего пользователей: " + count);

    }
}
