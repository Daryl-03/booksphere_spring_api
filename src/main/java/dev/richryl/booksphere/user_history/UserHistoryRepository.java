package dev.richryl.booksphere.user_history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

    UserHistory findByUserIdAndBookId(String userId, String bookId);

    void deleteByUserIdAndBookId(String userId, String bookId);

    List<UserHistory> findAllByUserId(String userId);
}
