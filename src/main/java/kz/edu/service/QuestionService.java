package kz.edu.service;


import kz.edu.dao.QuestionRepository;
import kz.edu.model.Question;
import kz.edu.service.interfaces.IEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService implements IEntityService<Question> {


    private final QuestionRepository questionRepository;

    @Override
    public void add(Question entity) {
        questionRepository.save(entity);
    }

    @Override
    public void update(Question entity) {
        questionRepository.save(entity);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAllQuestionByOrderByPublishedDesc();
    }

    public List<Question> getAllByUserId(long id){
        return questionRepository.findAllByUserId(id);
    }

    @Override
    public Question getById(long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        questionRepository.deleteById(id);
    }

}
