package dev.richryl.booksphere.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    private String bookId;
    private String title;
    private String description;
    private String coverImg;
    private String isbn;
    private Integer pages;
    private Double rating;
    private Integer numRatings;

    @Transient
    private List<String> genres;
    @Transient
    private List<String> authors;
}
