package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HibernateMain1 {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.Builder.createBuilder()
                .setLogin("Hibernate")
                .setPassword("123")
                .setFirstName("Alex")
                .setLastName("Gut")
                .setBirthday(LocalDate.of(2000,04,15))
                .setRegistration(LocalDateTime.now())
                .build();
        session.save(user);
        session.getTransaction().commit();
        session.close();

    }
}
