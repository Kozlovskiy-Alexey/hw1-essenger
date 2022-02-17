package by.it.academy.hw1_messenger.messenger.controllers.mains;

import by.it.academy.hw1_messenger.messenger.dto.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MessengerMain1 {
    public static void main(String[] args) {

        Person person1 = new Person("BadBoy", "123", "Vasia", "Belenko",
               LocalDate.of(2000,10,25));

     LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        System.out.println(localDateTime);

    }
}
