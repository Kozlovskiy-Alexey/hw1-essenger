package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateUserStorageMain1 {
    public static void main(String[] args) {
        HibernateDBInitializer initializer = HibernateDBInitializer.getInstance();
        EntityManager entityManager = initializer.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> from = query.from(UserEntity.class);

        query.select(from);

        List<UserEntity> list = entityManager.createQuery(query).getResultList();
        list.forEach(System.out::println);
    }
}
