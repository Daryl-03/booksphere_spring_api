package dev.richryl.booksphere.rating;

import dev.richryl.booksphere.book.Book;
import dev.richryl.booksphere.book.BookRepository;
import dev.richryl.booksphere.exception.EntityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;

    public RatingService(RatingRepository ratingRepository, BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
    }

    public void save(Rating rating) {
        if (bookRepository.findById(rating.getBookId()).orElse(null) == null) {
            throw new EntityException("Book not found");
        }

        Rating oldRating = ratingRepository.findByBookIdAndUserId(rating.getBookId(), rating.getUserId());
        if (oldRating != null) {
            rating.setId(oldRating.getId());
        }
        ratingRepository.save(rating);
        updateBookGeneralRating(rating.getBookId());
    }

    public void updateRating(String userId, Rating rating) {
        if (bookRepository.findById(rating.getBookId()).orElse(null) == null) {
            throw new EntityException("Book not found");
        }

        int id = ratingRepository.findByBookIdAndUserId(rating.getBookId(), userId).getId();
        if (id != 0) {
            rating.setId(id);
        }
        ratingRepository.save(rating);
        updateBookGeneralRating(rating.getBookId());
    }

    @Transactional
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
        updateBookGeneralRating(rating.getBookId());
    }

    @Transactional
    public void deleteByUserIdAndBookId(String userId, String bookId) {
        ratingRepository.deleteByUserIdAndBookId(userId, bookId);
        updateBookGeneralRating(bookId);
    }

    public Integer countByBookId(String bookId) {
        return ratingRepository.countByBookId(bookId);
    }

    public Rating findByBookIdAndUserId(String bookId, String userId) {
        return ratingRepository.findByBookIdAndUserId(bookId, userId);
    }

    public List<Rating> findAllByUserId(String userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    public Double averageByBookId(String bookId) {
        return ratingRepository.averageByBookId(bookId);
    }

    private void updateBookGeneralRating(String bookId) {
        Double averageRating = averageByBookId(bookId);
        Integer ratingCount = countByBookId(bookId);

        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            book.setRating(averageRating);
            book.setNumRatings(ratingCount);
            bookRepository.save(book);
        }
    }
}
