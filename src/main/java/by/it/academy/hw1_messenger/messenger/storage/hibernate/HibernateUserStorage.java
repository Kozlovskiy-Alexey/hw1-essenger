package by.it.academy.hw1_messenger.messenger.storage.hibernate;

import by.it.academy.hw1_messenger.messenger.model.User;
import by.it.academy.hw1_messenger.messenger.storage.api.IUserStorage;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HibernateUserStorage implements IUserStorage, AutoCloseable {
    private static volatile HibernateUserStorage instance;
    private final HibernateDBInitializer dbInitializer;

    private HibernateUserStorage() {
        dbInitializer = HibernateDBInitializer.getInstance();
    }

    public static HibernateUserStorage getInstance() {
        HibernateUserStorage result = instance;
        if (result != null) {
            return result;
        }
        synchronized (HibernateUserStorage.class) {
            if (instance == null) {
                instance = new HibernateUserStorage();
            }
        }
        return instance;
    }

    @Override
    public User get(String login) {
        EntityManager entityManager = dbInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        UserEntity userEntity = entityManager.find(UserEntity.class, login);
        User user = null;
        if (userEntity != null) {
            user = User.Builder.createBuilder()
                    .setLogin(userEntity.getLogin())
                    .setPassword(userEntity.getPassword())
                    .setFirstName(userEntity.getFirstName())
                    .setLastName(userEntity.getLastName())
                    .setBirthday(userEntity.getBirthday())
                    .setRegistration(userEntity.getRegistration())
                    .build();
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return user;
    }

    @Override
    public void add(User user) {
        User result = this.get(user.getLogin());
        if (result != null) {
            throw new IllegalArgumentException("Пользователь " + user.getLogin() + " уже существует!");
        }
        UserEntity userEntity = UserEntity.Builder.createBuilder()
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setBirthday(user.getBirthday())
                .setRegistration(user.getRegistration())
                .build();
        EntityManager entityManager = dbInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Collection<User> getAll() {
        EntityManager entityManager = dbInitializer.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> from = query.from(UserEntity.class);
        query.select(from);
        List<UserEntity> result = entityManager.createQuery(query).getResultList();
        List<User> list = new ArrayList<>(result.size());
        for (UserEntity entity : result) {
            User user = User.Builder.createBuilder()
                    .setLogin(entity.getLogin())
                    .setPassword(entity.getPassword())
                    .setFirstName(entity.getFirstName())
                    .setLastName(entity.getLastName())
                    .setBirthday(entity.getBirthday())
                    .setRegistration(entity.getRegistration())
                    .build();
            list.add(user);
        }
        entityManager.close();
        return list;
    }

    @Override
    public long getCount() {
        long result = 0;
        EntityManager entityManager = dbInitializer.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> from = query.from(UserEntity.class);
        query.select(from.get("login"));
        result = entityManager.createQuery(query).getResultList().size();
        entityManager.close();

        return result;


    }

    @Override
    public void close() throws Exception {
        dbInitializer.close();
    }
}
