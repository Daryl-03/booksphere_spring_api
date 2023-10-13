package dev.richryl.booksphere.user_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

    UserHistory findByUserIdAndBookId(String userId, String bookId);

    void deleteByUserIdAndBookId(String userId, String bookId);

    List<UserHistory> findAllByUserId(String userId);
}
