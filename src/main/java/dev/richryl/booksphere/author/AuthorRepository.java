package dev.richryl.booksphere.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Page<Author> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
