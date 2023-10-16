insert into AUTHORS (`name`) VALUES('Author 1'),('Author 2');
insert into GENRES (`name`) VALUES('Genre 1'),('Genre 2');
insert into books (name,author_id,genre_id) values ('Book 1',1,1),('Book 2',1,1);
insert into book_comments (text,book_id) values
  ('Comment 1',1), ('Comment 2',1), ('Comment 3',2), ('Comment 4',2);
