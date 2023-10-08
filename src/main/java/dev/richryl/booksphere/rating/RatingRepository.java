package dev.richryl.booksphere.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Integer countByBookId(String bookId);

    Integer findByBookIdAndUserId(String bookId, String userId);

    @Query(value = "SELECT AVG(rating) FROM Rating WHERE bookId = ?1")
    Double averageByBookId(String bookId);

    void deleteByUserIdAndBookId(String userId, String bookId);
}
