package com.example.backend.DTO.Response;


import com.example.backend.Classes.Steps;
import com.example.backend.Enum.Difficulty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllQuestionsResponseDTO {

    private Long id;

    private String question;

    private String solution;


    private String correctAnswer;


    private List<String> options;


    private List<Steps> steps;


    private List<String> tags;


    private Difficulty difficulty;

    private String imageUrl;

}
