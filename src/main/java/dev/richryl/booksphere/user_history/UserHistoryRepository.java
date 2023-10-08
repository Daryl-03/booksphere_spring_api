package dev.richryl.booksphere.user_history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

    Integer findByUserIdAndBookId(String userId, String bookId);
}
