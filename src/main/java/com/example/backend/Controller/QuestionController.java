package com.example.backend.Controller;


import com.example.backend.DTO.Request.QuestionRequestDTO;
import com.example.backend.Service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionService questionService;




    @PostMapping(value = "/addQuestion/")
    public ResponseEntity<?> addQuestion( @ModelAttribute QuestionRequestDTO requestDTO) throws IOException {
        questionService.addQuestion(requestDTO);
        return ResponseEntity.ok("Added");
    }


    @GetMapping(value = "/getAllQuestion")
    public ResponseEntity<?> getQuestion(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }



    @GetMapping(value = "/getQuestionById/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(questionService.getQuestionById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping(value = "/deleteQuestionById/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id){
        try{
            questionService.deleteQuestionById(id);
            return ResponseEntity.ok("Deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/updateQuestionById/{id}")
    public ResponseEntity<?> updateQuestionById(@PathVariable Long id, @RequestBody QuestionRequestDTO requestDTO){
        try{
            return ResponseEntity.ok(questionService.updateQuestion(id,requestDTO));
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
