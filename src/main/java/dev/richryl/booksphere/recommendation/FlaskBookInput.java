package dev.richryl.booksphere.recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlaskBookInput {

    Map<String, Double> bookIdMap;
    List<String> unreadBookIds;
}
