package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.HibernateUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public class HibernateUserStorageMain2 {
    public static void main(String[] args) {
        HibernateUserStorage userStorage = HibernateUserStorage.getInstance();

        userStorage.add(User.Builder.createBuilder()
                        .setLogin("Nic")
                        .setPassword("123")
                        .setFirstName("Nic")
                        .setLastName("MarMike")
                        .setBirthday(LocalDate.of(1945, Month.FEBRUARY, 06))
                        .setRegistration(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build());
        User user = userStorage.get("Bob");
        System.out.println(user);
//        Collection<User> all = userStorage.getAll();
//        all.forEach(System.out::println);
//        System.out.println(userStorage.getCount());

    }
}
