package com.example.backend.Entity;


import com.example.backend.Classes.Steps;
import com.example.backend.Enum.Difficulty;
import com.example.backend.Enum.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_question")
public class Question {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "question_id")
    private UUID id;


    @Column(name = "question")
    private String question;

    @Column(name = "solution")
    private String solution;


    @Column(name = "correctAnswer")
    private String correctAnswer;


    @Column(name = "options",columnDefinition = "TEXT",length = 10000)
    private String optionsJson;


    @Column(name = "steps",columnDefinition = "TEXT",length = 10000)
    private String stepsJson;


    @Column(name = "tags",columnDefinition = "TEXT",length = 10000)
    private String tagsJson;






    @Column(name = "imageUrl")
    private String imageUrl;


    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;



}
