CREATE TABLE "user"
(
    user_id  SERIAL PRIMARY KEY,
    username VARCHAR(225) NOT NULL,
    password VARCHAR(30)  NOT NULL,
    email    VARCHAR(50)  NOT NULL
);

CREATE TABLE article
(
    article_id   SERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    content      TEXT         NOT NULL,
    created_date DATE         NOT NULL
);

CREATE TABLE travel
(
    travel_id      SERIAL PRIMARY KEY,
    user_id        INT          NOT NULL,
    name_travel    VARCHAR(100) NOT NULL,
    description    TEXT,
    start_date     DATE,
    end_date       DATE,
    transport      TEXT,
    list_of_things TEXT,
    notes          TEXT,
    CONSTRAINT fk_travel_user FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE
);


CREATE TABLE favourites
(
    user_id    INT NOT NULL,
    article_id INT NOT NULL,
    PRIMARY KEY (user_id, article_id),
    CONSTRAINT fk_fav_user FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_fav_article FOREIGN KEY (article_id) REFERENCES article (article_id) ON DELETE CASCADE
);
