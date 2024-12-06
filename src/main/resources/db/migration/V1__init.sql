CREATE TABLE categories
(
    id    BIGINT NOT NULL,
    title VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE jt_lecturer
(
    id      BIGINT NOT NULL,
    company VARCHAR(255) NULL,
    course  VARCHAR(255) NULL,
    CONSTRAINT pk_jt_lecturer PRIMARY KEY (id)
);

CREATE TABLE jt_mentor
(
    id             BIGINT NOT NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (id)
);

CREATE TABLE jt_students
(
    id    BIGINT NOT NULL,
    batch VARCHAR(255) NULL,
    CONSTRAINT pk_jt_students PRIMARY KEY (id)
);

CREATE TABLE jt_users
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_jt_users PRIMARY KEY (id)
);

CREATE TABLE mp_lecturers
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    company  VARCHAR(255) NULL,
    course   VARCHAR(255) NULL,
    CONSTRAINT pk_mp_lecturers PRIMARY KEY (id)
);

CREATE TABLE mp_mentors
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_mp_mentors PRIMARY KEY (id)
);

CREATE TABLE mp_students
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_mp_students PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT NOT NULL,
    title         VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    `description` VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE st_users
(
    id             BIGINT NOT NULL,
    dtype          VARCHAR(31) NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    batch          VARCHAR(255) NULL,
    company        VARCHAR(255) NULL,
    course         VARCHAR(255) NULL,
    CONSTRAINT pk_st_users PRIMARY KEY (id)
);

CREATE TABLE t_lecturers
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    company  VARCHAR(255) NULL,
    course   VARCHAR(255) NULL,
    CONSTRAINT pk_t_lecturers PRIMARY KEY (id)
);

CREATE TABLE t_mentors
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    specialization VARCHAR(255) NULL,
    CONSTRAINT pk_t_mentors PRIMARY KEY (id)
);

CREATE TABLE t_students
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    batch    VARCHAR(255) NULL,
    CONSTRAINT pk_t_students PRIMARY KEY (id)
);

CREATE TABLE t_users
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_t_users PRIMARY KEY (id)
);

ALTER TABLE jt_lecturer
    ADD CONSTRAINT FK_JT_LECTURER_ON_ID FOREIGN KEY (id) REFERENCES jt_users (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_ID FOREIGN KEY (id) REFERENCES jt_users (id);

ALTER TABLE jt_students
    ADD CONSTRAINT FK_JT_STUDENTS_ON_ID FOREIGN KEY (id) REFERENCES jt_users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);