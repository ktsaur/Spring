package ru.kpfu.entity;

import java.io.Serializable;
import java.util.Objects;

public class FavouritesId implements Serializable {
    private Long user;
    private Long article;
    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

}
