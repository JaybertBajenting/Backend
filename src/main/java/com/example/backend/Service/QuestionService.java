package com.example.backend.Service;


import com.example.backend.Classes.Steps;
import com.example.backend.DTO.Request.AddQuestionRequestDTO;
import com.example.backend.DTO.Response.GetAllQuestionsResponseDTO;
import com.example.backend.Entity.Question;
import com.example.backend.Repository.QuestionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();




    private <T> T parseJsonToList(String json, TypeReference<T> typeReference){
        try{
            return json != null ? objectMapper.readValue(json,typeReference) : objectMapper.convertValue(new ArrayList<>(),typeReference);
        }catch (Exception e){
            throw new RuntimeException("Error parsing JSON", e);
        }
    }


    private String serializeListToJson(Object list){
        try{
            return objectMapper.writeValueAsString(list);
        }catch (Exception e){
            throw new RuntimeException("Error serializing list to JSON", e);
        }
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Question not found")
        );
    }


    public  void addQuestion( AddQuestionRequestDTO request){
        Question question = Question.builder()
                .question(request.getQuestion())
                .solution(request.getSolution())
                .correctAnswer(request.getCorrectAnswer())
                .optionsJson(serializeListToJson(request.getOptions()))
                .stepsJson(serializeListToJson(request.getSteps()))
                .tagsJson(serializeListToJson(request.getTags()))
                .difficulty(request.getDifficulty())
                .imageUrl("hehe")
                .build();
        questionRepository.save(question);
    }










    public List<GetAllQuestionsResponseDTO> getAllQuestions(){
            List<Question> questions = questionRepository.findAll();

            List<GetAllQuestionsResponseDTO> response = new ArrayList<>();
            for(Question question : questions){
                response.add(GetAllQuestionsResponseDTO.builder()
                        .id(question.getId())
                        .question(question.getQuestion())
                        .solution(question.getSolution())
                        .correctAnswer(question.getCorrectAnswer())
                        .options(parseJsonToList(question.getOptionsJson(), new TypeReference<List<String>>(){}))
                        .steps(parseJsonToList(question.getStepsJson(), new TypeReference<List<Steps>>(){}))
                        .tags(parseJsonToList(question.getTagsJson(), new TypeReference<List<String>>(){}))
                        .difficulty(question.getDifficulty())
                        .imageUrl(question.getImageUrl())
                        .build());
            }

            return response;
    }


    public void deleteQuestionById(Long id){
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }







}

