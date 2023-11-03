package dev.richryl.booksphere.book_author;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "book_author")
public class BookAuthor {
    @Id
    private Integer id;
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "author_id")
    private Integer authorId;
}
