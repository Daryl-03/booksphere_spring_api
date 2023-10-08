package dev.richryl.booksphere.user_history;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_history")
public class UserHistory {
    @Id
    private Integer id;
    private String userId;
    private String bookId;
    @Enumerated(EnumType.STRING)
    private BookState bookState;
    private Integer bookmark;
}
