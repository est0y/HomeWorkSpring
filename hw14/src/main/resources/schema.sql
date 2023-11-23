DROP TABLE IF EXISTS PERSONS;
CREATE TABLE GENRES
(
    ID   bigserial PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE AUTHORS
(
    ID   bigserial PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE BOOKS
(
    ID   bigserial PRIMARY KEY,
    NAME VARCHAR(255),
    AUTHOR_ID BIGINT REFERENCES "AUTHORS" ("ID"),
    GENRE_ID BIGINT REFERENCES "GENRES" ("ID")
);