package by.it.academy.hw1_messenger.messenger.controller.mains;

import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.HibernateDBInitializer;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.AuditUserEntity;
import by.it.academy.hw1_messenger.messenger.storage.hibernate.api.entity.MessageEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateAuditStotageMain1 {
    public static void main(String[] args) {
        HibernateDBInitializer initializer = HibernateDBInitializer.getInstance();
        EntityManager entityManager = initializer.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuditUserEntity> query = cb.createQuery(AuditUserEntity.class);
        Root<AuditUserEntity> from = query.from(AuditUserEntity.class);

        query.select(from);


        List<AuditUserEntity> list = entityManager.createQuery(query).getResultList();
        list.forEach(System.out::println);
    }
}
