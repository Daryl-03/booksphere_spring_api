package dev.richryl.booksphere.book;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;


public record PaginationInfo(long totalRecords, int page, int pageSize, int totalPages) {
    PaginationInfo(Page page){
        this(page.getTotalElements(), page.getNumber(), page.getSize(), page.getTotalPages());
    }

    public PaginationInfo(long totalRecords, int page, int pageSize, int totalPages) {
        this.totalRecords = totalRecords;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }
}
