package kz.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    private Question question;

    private Map<Answer, Integer> answers;

    //private Map<User, Answer> groupmates;

    private int voteNumber;
}
