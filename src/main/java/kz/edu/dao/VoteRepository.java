package kz.edu.dao;

import kz.edu.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findAllByQuestionId(long id);
    List<Vote> findAllByAnswerId(long id);
    List<Vote> findAllByUserId(long id);
}
