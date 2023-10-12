package dev.richryl.booksphere.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public record BookPageResult(PaginationInfo meta, List<Book> data) {

    public BookPageResult(Page<Book> page) {
        this(new PaginationInfo(page.getTotalElements(), page.getNumber(), page.getSize(), page.getTotalPages()), page.toList());
    }

    public BookPageResult(Page<Book> page, List<Book> books) {
        this(new PaginationInfo(page.getTotalElements(), page.getNumber(), page.getSize(), page.getTotalPages()), books);
    }

    public BookPageResult(PaginationInfo meta, List<Book> data) {
        this.meta = meta;
        this.data = data;
    }

    public static BookPageResult merge(BookPageResult bookPageResult1, BookPageResult bookPageResult2) {
        long totalRecords = bookPageResult1.meta.totalRecords() + bookPageResult2.meta.totalRecords();
        int pageSize = bookPageResult1.meta.pageSize();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        int currentPage = bookPageResult1.meta.totalRecords() > bookPageResult2.meta.totalRecords() ? bookPageResult1.meta.page() : bookPageResult2.meta.page();
        List<Book> books = new ArrayList<>();
        books.addAll(bookPageResult1.data);
        books.addAll(bookPageResult2.data);

        return new BookPageResult(new PaginationInfo(totalRecords, currentPage, pageSize, totalPages), books);
    }
}
