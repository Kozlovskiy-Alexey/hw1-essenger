package by.it.academy.hw1_messenger.messenger.storage.hibernate.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class HibernateDBInitializer implements AutoCloseable{
    private volatile static HibernateDBInitializer instance;
    private final EntityManagerFactory entityManagerFactory;

    private HibernateDBInitializer() throws IOException, SQLException, PropertyVetoException {
        entityManagerFactory = Persistence.createEntityManagerFactory("by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity");
    }

    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public static HibernateDBInitializer getInstance() {
        HibernateDBInitializer result = instance;
        if (result != null) {
            return result;
        }
        synchronized (HibernateDBInitializer.class) {
            if (instance == null) {
                try {
                    instance = new HibernateDBInitializer();
                } catch (IOException | SQLException | PropertyVetoException e) {
                    throw new RuntimeException("Ошибка подключения к базе данных", e);
                }
            }
            return instance;
        }
    }

    @Override
    public synchronized void close() throws Exception {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
