package com.example.backend.Service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.example.backend.Classes.Steps;
import com.example.backend.DTO.Request.QuestionRequestDTO;
import com.example.backend.DTO.Response.GetQuestionsResponseDTO;
import com.example.backend.Entity.Question;
import com.example.backend.Repository.QuestionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiTabbedPaneUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.digitalocean.bucketName}")
    private String bucketName;



    private final AmazonS3 amazonS3;
    private final DigitalOceanService spacesService;

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





    public  void addQuestion( QuestionRequestDTO request) throws IOException {

        MultipartFile file = request.getImage();
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        spacesService.uploadFile(file.getOriginalFilename(), convFile);
       String fileUrl = amazonS3.getUrl(bucketName,file.getOriginalFilename()).toString();


        convFile.delete();

        Question question = Question.builder()
                .question(request.getQuestion())
                .solution(request.getSolution())
                .correctAnswer(request.getCorrectAnswer())
                .optionsJson(serializeListToJson(request.getOptions()))
                .stepsJson(serializeListToJson(request.getSteps()))
                .tagsJson(serializeListToJson(request.getTags()))
                .difficulty(request.getDifficulty())
                .imageUrl(fileUrl)
                .build();
        questionRepository.save(question);
    }




    public List<GetQuestionsResponseDTO> getAllQuestions(){
            List<Question> questions = questionRepository.findAll();


            List<GetQuestionsResponseDTO> response = new ArrayList<>();
            for(Question question : questions){
                response.add(GetQuestionsResponseDTO.builder()
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


    public GetQuestionsResponseDTO updateQuestion(Long questionId,QuestionRequestDTO updatedQuestion) throws IOException {
            Question question = getQuestionById(questionId);
            MultipartFile file = updatedQuestion.getImage();
            if(updatedQuestion.getQuestion() != null){
                question.setQuestion(updatedQuestion.getQuestion());
            }
            if(updatedQuestion.getSolution() != null){
                question.setSolution(updatedQuestion.getSolution());
            }
            if(updatedQuestion.getCorrectAnswer() != null){
                question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
            }
            if(updatedQuestion.getOptions() != null){
                question.setOptionsJson(serializeListToJson(updatedQuestion.getOptions()));
            }
            if(updatedQuestion.getSteps() != null){
                question.setStepsJson(serializeListToJson(updatedQuestion.getSteps()));
            }
            if(updatedQuestion.getTags() != null){
                question.setTagsJson(serializeListToJson(updatedQuestion.getTags()));
            }
            if(updatedQuestion.getDifficulty() != null){
                question.setDifficulty(updatedQuestion.getDifficulty());
            }
            if(updatedQuestion.getImageUrl() != null){
                File convFile = new File(file.getOriginalFilename());
                try (FileOutputStream fos = new FileOutputStream(convFile)) {
                    fos.write(file.getBytes());
                }
                spacesService.uploadFile(file.getOriginalFilename(), convFile);
                String fileUrl = amazonS3.getUrl(bucketName,file.getOriginalFilename()).toString();
                question.setImageUrl(fileUrl);
            }


            questionRepository.save(question);
           return GetQuestionsResponseDTO.builder()
                        .id(question.getId())
                        .question(question.getQuestion())
                        .solution(question.getSolution())
                        .correctAnswer(question.getCorrectAnswer())
                        .options(parseJsonToList(question.getOptionsJson(), new TypeReference<List<String>>(){}))
                        .steps(parseJsonToList(question.getStepsJson(), new TypeReference<List<Steps>>(){}))
                        .tags(parseJsonToList(question.getTagsJson(), new TypeReference<List<String>>(){}))
                        .difficulty(question.getDifficulty())
                        .imageUrl(question.getImageUrl())
                        .build();
    }





    public void deleteQuestionById(Long id){
        Question question = getQuestionById(id);
        spacesService.deleteFileByUrl(question.getImageUrl());
        questionRepository.delete(question);
    }







}

