package kz.edu.dao;

import kz.edu.model.Group;
import kz.edu.model.Question;
import kz.edu.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByQuestionId(long id);
    List<Vote> findAllByAnswerId(long id);
    List<Vote> findAllByUserId(long id);
    int countByQuestion(Question question);
    boolean existsByQuestionIdAndUserId(long questionId, long userId);
    Vote findByQuestionIdAndUserId(long questionId, long userId);
    List<Vote> findAllByUserGroupAndQuestionIdAndUserIdNot(Group group, long questionId, long userId);
    int countByAnswerId(long id);
    void deleteByVoteIdAndUserId(long voteId, long userId);
}
