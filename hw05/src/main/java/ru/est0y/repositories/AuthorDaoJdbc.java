package ru.est0y.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.est0y.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.query(
                "select id,name from authors", new AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Author(id, name);
        }
    }
}
