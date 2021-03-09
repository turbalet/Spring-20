package kz.edu.service;

import kz.edu.dao.VoteRepository;
import kz.edu.model.Group;
import kz.edu.model.Vote;
import kz.edu.service.interfaces.IEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteService implements IEntityService<Vote> {

    private final VoteRepository voteRepository;

    @Override
    public Vote add(Vote entity) {
        return voteRepository.save(entity);
    }

    @Override
    public void update(Vote entity) {
        voteRepository.save(entity);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote getById(long id) {
        return voteRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {

    }

    public void delete(long voteId, long userId) {
        voteRepository.deleteByVoteIdAndUserId(voteId, userId);
    }

    public List<Vote> getAllByQuestionId(long id){
        return voteRepository.findAllByQuestionId(id);
    }
    public List<Vote> getAllByAnswerId(long id){
        return voteRepository.findAllByAnswerId(id);
    }
    public List<Vote> getAllByUserId(long id){
        return voteRepository.findAllByUserId(id);
    }
    public List<Vote> getVotedGroupmates(Group group, long questionId,long userId){
        return voteRepository.findAllByUserGroupAndQuestionIdAndUserIdNot(group,questionId, userId);
    }
    public boolean isVoted(long questionId, long userId){
        return voteRepository.existsByQuestionIdAndUserId(questionId, userId);
    }

    public int getVoteCountByAnswer(long id){
        return voteRepository.countByAnswerId(id);
    }

    public Vote getUserVote(long questionId, long userId){
        return voteRepository.findByQuestionIdAndUserId(questionId, userId);
    }
}
