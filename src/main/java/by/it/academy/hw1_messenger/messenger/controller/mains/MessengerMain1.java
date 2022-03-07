package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MessengerMain1 {
    public static void main(String[] args) {

        User user1 = new User("BadBoy", "123", "Vasia", "Belenko",
               LocalDate.of(2000,10,25));

     LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        System.out.println(localDateTime);

    }
}
