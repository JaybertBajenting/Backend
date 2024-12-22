package com.example.backend.Controller;


import com.example.backend.DTO.Request.AddQuestionRequestDTO;
import com.example.backend.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionService questionService;





    @PostMapping(value = "/addQuestion/")
    public ResponseEntity<?> addQuestion( @RequestBody AddQuestionRequestDTO requestDTO){
        questionService.addQuestion(requestDTO);
        return ResponseEntity.ok("Added");
    }

//    @PostMapping(value = "/addQuestion/", consumes = {"multipart/form-data"})
//    public ResponseEntity<?> addQuestion(
//            @RequestParam(name = "imageUrl") MultipartFile multipartFile,
//            @RequestPart(name = "requestDTO")AddQuestionRequestDTO requestDTO) {
//        questionService.addQuestion(multipartFile, requestDTO);
//        return ResponseEntity.ok("Added");
//    }

}
