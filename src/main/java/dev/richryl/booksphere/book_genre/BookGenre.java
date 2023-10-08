package dev.richryl.booksphere.book_genre;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_genre")
public class BookGenre {
    @Id
    private Integer id;
    private String bookId;
    private Integer genreId;
}
