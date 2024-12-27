package com.example.backend.DTO.Request;


import com.example.backend.Classes.Steps;
import com.example.backend.Enum.Difficulty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class QuestionRequestDTO {



    private String question;

    private String solution;


    private String correctAnswer;


    private List<String> options;


    private List<Steps> steps;


    private List<String> tags;


    private Difficulty difficulty;

    private String imageUrl;


    MultipartFile image;


}
