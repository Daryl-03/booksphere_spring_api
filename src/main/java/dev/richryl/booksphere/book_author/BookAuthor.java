package dev.richryl.booksphere.book_author;

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
    private String bookId;
    private Integer authorId;
}
