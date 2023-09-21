package ru.est0y.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.dto.BookDto;
import ru.est0y.exceptions.UpdateFail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    public void update(BookDto bookDto) {
        var authorId = bookDto.getAuthorId();
        var genreId = bookDto.getGenreId();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", bookDto.getId());
        params.addValue("name", bookDto.getName());
        params.addValue("author_id", authorId);
        params.addValue("genre_id", genreId);
        var rowCount = namedParameterJdbcOperations.update(
                "update books set name=:name,author_id=:author_id,genre_id=:genre_id where id=:id",
                params);
        if (rowCount == 0) {
            throw new UpdateFail();
        }
    }


    public void insert(BookDto bookDto) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        var authorId = bookDto.getAuthorId();
        var genreId = bookDto.getGenreId();
        params.addValue("name",
                bookDto.getName());
        params.addValue("author_id", authorId);
        params.addValue("genre_id", genreId);
        namedParameterJdbcOperations.update(
                "insert into books (name,author_id,genre_id) values (:name,:author_id,:genre_id)",
                params);
    }


    @Override
    public Optional<Book> findById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            var book = namedParameterJdbcOperations.queryForObject(
                    "select b.id as book_id," +
                            " b.name as book_name," +
                            "a.id as author_id," +
                            "a.name as author_name," +
                            "g.id as genre_id," +
                            "g.name as genre_name" +
                            " from books as b left join authors as a on b.author_id=a.id" +
                            " left join genres as g on b.genre_id=g.id" +
                            " where b.id=:id", params, new BookMapper()
            );
            return Optional.ofNullable(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<Book> findAll() {
        return namedParameterJdbcOperations.query(
                "select b.id as book_id," +
                        " b.name as book_name," +
                        "a.id as author_id," +
                        "a.name as author_name," +
                        "g.id as genre_id," +
                        "g.name as genre_name" +
                        " from books as b left join authors as a on b.author_id=a.id" +
                        " left join genres as g on b.genre_id=g.id", new BookMapper()
        );
    }

    private static class BookMapper implements RowMapper<Book> {


        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long authorId = rs.getLong("author_id");
            String authorName = rs.getString("author_name");
            long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");
            long bookId = rs.getLong("book_id");
            String bookName = rs.getString("book_name");
            Author author = new Author(authorId, authorName);
            Genre genre = new Genre(genreId, genreName);
            return new Book(bookId, bookName, author, genre);
        }

    }
}