package ru.kpfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {}

