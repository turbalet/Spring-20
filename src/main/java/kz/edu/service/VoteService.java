package kz.edu.service;

import kz.edu.dao.VoteRepository;
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
    public void add(Vote entity) {
        voteRepository.save(entity);
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
        voteRepository.deleteById(id);
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
}
