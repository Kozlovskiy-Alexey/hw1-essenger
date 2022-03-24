package by.it.academy.hw1_messenger.messenger.storage.hibernate;

import by.it.academy.hw1_messenger.messenger.model.Message;
import by.it.academy.hw1_messenger.messenger.storage.api.IChatStorage;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.MessageEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class HibernateChatStorage implements IChatStorage {
    private static volatile HibernateChatStorage instance;
    private final HibernateDBInitializer dbInitializer;

    private HibernateChatStorage() {
        this.dbInitializer = HibernateDBInitializer.getInstance();
    }

    public static HibernateChatStorage getInstance() {
        HibernateChatStorage result = instance;
        if (result != null) {
            return instance;
        }
        synchronized (HibernateChatStorage.class) {
            if (instance == null) {
                instance = new HibernateChatStorage();
            }
        }
        return instance;
    }


    @Override
    public List<Message> get(String toLogin) {
        EntityManager entityManager = dbInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> from = query.from(MessageEntity.class);
        query.select(from).where(cb.equal(from.get("toLogin"), toLogin));

        List<MessageEntity> resultList = entityManager.createQuery(query).getResultList();
        List<Message> list = new ArrayList<>(resultList.size());

        for (MessageEntity entity : resultList) {
            Message message = Message.Builder.createBuilder()
                    .setDateTime(entity.getDateTime())
                    .setFromLogin(entity.getFromLogin())
                    .setTextMessage(entity.getTextMessage())
                    .build();
            list.add(message);
        }
        entityManager.close();
        return list;
    }

    @Override
    public void addMessage(String toLogin, Message message) {
        EntityManager entityManager = dbInitializer.getEntityManager();
        MessageEntity entity = MessageEntity.Builder.createBuilder()
                .setToLogin(toLogin)
                .setFromLogin(message.getFromLogin())
                .setDateTime(message.getDateTime())
                .setTextMessage(message.getTextMessage())
                .build();

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public long getCount() {
        long size = 0;
        EntityManager entityManager = dbInitializer.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(root.get("id"));

        size = entityManager.createQuery(query).getResultList().size();
        return size;
    }
}
