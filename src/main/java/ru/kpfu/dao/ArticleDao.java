package ru.kpfu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.kpfu.entity.Article;

import java.util.List;
import java.util.Optional;

public class ArticleDao {

    private final SessionFactory sessionFactory;

    public ArticleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Article article) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(article);
            tx.commit();
        }
    }

    public Optional<Article> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Article.class, id));
        }
    }

    public List<Article> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Article", Article.class).list();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Article article = session.get(Article.class, id);
            if (article != null) {
                session.remove(article);
            }
            tx.commit();
        }
    }
}