package ru.kpfu.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.kpfu.entity.User;

import java.util.List;

@Component
public class UserRepositoryHiber {

    private final SessionFactory sessionFactory;

    public UserRepositoryHiber(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAll() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session.createQuery("from User").list();
    }
}
