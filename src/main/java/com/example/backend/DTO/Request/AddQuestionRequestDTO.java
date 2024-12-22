package com.example.backend.DTO.Request;


import com.example.backend.Classes.Steps;
import com.example.backend.Enum.Difficulty;
import lombok.Data;

import java.util.List;

@Data
public class AddQuestionRequestDTO {



    private String question;

    private String solution;


    private String correctAnswer;


    private List<String> options;


    private List<Steps> steps;


    private List<String> tags;


    private Difficulty difficulty;

    private String imageUrl;



}
