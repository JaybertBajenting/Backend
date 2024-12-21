package com.example.backend.Controller;


import com.example.backend.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionService questionService;



    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuestions(){
        return ResponseEntity.ok(questionService.questions());
    }
}
