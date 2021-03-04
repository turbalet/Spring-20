package kz.edu.service;

import kz.edu.dao.AnswerRepository;
import kz.edu.model.Answer;
import kz.edu.service.interfaces.IEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService implements IEntityService<Answer> {

    private final AnswerRepository answerRepository;

    @Override
    public void add(Answer entity) {
        answerRepository.save(entity);
    }

    @Override
    public void update(Answer entity) {
        answerRepository.save(entity);
    }

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public List<Answer> getAllByQuestionId(long id){
        return answerRepository.findAllByQuestionId(id);
    }

    @Override
    public Answer getById(long id) {
        return answerRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        answerRepository.deleteById(id);
    }
}
