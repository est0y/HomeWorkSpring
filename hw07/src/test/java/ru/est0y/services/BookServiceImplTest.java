package ru.est0y.services;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorServiceImpl.class,GenreServiceImpl.class,BookServiceImpl.class})
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private TestEntityManager em;
    @Test
    void updateWithSameAuthorAndGenre() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        bookService.update(1,"updated",1,1);
        em.flush();
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(2);
        sessionFactory.getStatistics().clear();
    }
    @Test
    void updateWithOtherAuthorAndGenre() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        bookService.update(1,"updated",2,2);
        em.flush();
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(4);
        sessionFactory.getStatistics().clear();
    }
}