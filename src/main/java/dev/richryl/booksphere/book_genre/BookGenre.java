package dev.richryl.booksphere.book_genre;

import jakarta.persistence.Column;
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
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "genre_id")
    private Integer genreId;
}
