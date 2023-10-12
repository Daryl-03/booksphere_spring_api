package dev.richryl.booksphere.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Integer countByBookId(String bookId);

    Rating findByBookIdAndUserId(String bookId, String userId);

    List<Rating> findAllByUserId(String userId);

    @Query(value = "SELECT AVG(rating) FROM Rating WHERE bookId = ?1")
    Double averageByBookId(String bookId);

    void deleteByUserIdAndBookId(String userId, String bookId);
}
