package dev.richryl.booksphere.book;

import dev.richryl.booksphere.author.Author;
import dev.richryl.booksphere.genre.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    private String bookId;
    private String title;
    private String description;
    private String coverImg;
    private String isbn;
    private Integer pages;
    private Double rating;
    private Integer numRatings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT), referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT), referencedColumnName = "id")
    )
//    @Transient
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT), referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT), referencedColumnName = "id")
    )
//    @Transient
    private List<Author> authors;
}