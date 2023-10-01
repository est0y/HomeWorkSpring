package ru.est0y.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
@Data
@NamedEntityGraph(name = "books-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;

    @JoinColumn(name = "genre_id")
    @ManyToOne
    private Genre genre;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
    private List<BookComment> comments;

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
