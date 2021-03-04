package kz.edu.rest;


import kz.edu.model.Question;
import kz.edu.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<Question> getAll(){
        return questionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Question question = questionService.getById(id);
        if(question!=null){
            return ResponseEntity.ok(question);
        }
        return ResponseEntity.status(404).body("Question with such id doesn't exist");
    }

    @PostMapping("")
    public ResponseEntity<?> addAnswer(@RequestBody Question question){
        try{
            questionService.add(question);
            return ResponseEntity.ok("Question was successfully added");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAnswer(@RequestBody Question question){
        try{
            questionService.update(question);
            return ResponseEntity.ok("Question was successfully updated");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") long id){
        try{
            questionService.delete(id);
            return ResponseEntity.ok("Question was successfully deleted");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("user/{id}")
    public List<Question> getByUserId(@PathVariable("id") long id){
        return questionService.getAllByUserId(id);
    }
}
