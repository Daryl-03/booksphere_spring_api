package dev.richryl.booksphere.book;

import lombok.AllArgsConstructor;
import lombok.Data;

public record BookPageResult(long total_books, int page, int pageSize, int totalPages, Iterable<Book> books) {
}
