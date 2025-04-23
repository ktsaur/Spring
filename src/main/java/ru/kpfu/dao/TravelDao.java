package ru.kpfu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.kpfu.entity.Travel;

import java.util.List;

public class TravelDao {
    private final SessionFactory sessionFactory;

    public TravelDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Travel> findByUserId(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Travel where user.id = :userId", Travel.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

    public void save(Travel travel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(travel);
            tx.commit();
        }
    }
}
