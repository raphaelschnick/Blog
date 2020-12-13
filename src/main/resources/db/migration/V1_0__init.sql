CREATE TABLE user (
    id bigint NOT NULL AUTO_INCREMENT,
    name character varying(256),
    password character varying(256),

    CONSTRAINT PK_user_id PRIMARY KEY (id)
);

CREATE TABLE category (
    id bigint NOT NULL AUTO_INCREMENT,
    name character varying(256),
    color character,

    CONSTRAINT PK_category_id PRIMARY KEY (id)
);

CREATE TABLE blog (
    id bigint NOT NULL AUTO_INCREMENT,
    name character varying(26),
    date date,
    description character varying(126),
    text character,
    image character,
    user_id bigint,
    category_id bigint,

    CONSTRAINT PK_blog_id PRIMARY KEY (id),
    CONSTRAINT FK_blog_user_id FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT FK_blog_category_id FOREIGN KEY (category_id) REFERENCES category(id)
);
