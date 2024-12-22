package com.example.backend.Service;


import aj.org.objectweb.asm.TypeReference;
import com.example.backend.DTO.Request.AddQuestionRequestDTO;
import com.example.backend.Entity.Question;
import com.example.backend.Repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

//    public void addOption(UUID questionId, String newOption) {
//        Question question = questionRepository.findById(questionId)
//                .orElseThrow(() -> new RuntimeException("Question not found"));
//
//        List<String> options = parseJsonToList(question.getOptionsJson(), new TypeReference<List<String>>() {});
//        options.add(newOption);
//        question.setOptionsJson(serializeListToJson(options));
//
//        questionRepository.save(question);
//    }




//    public void removeOption(UUID questionId, String optionToRemove) {
//        Question question = questionRepository.findById(questionId)
//                .orElseThrow(() -> new RuntimeException("Question not found"));
//
//        List<String> options = parseJsonToList(question.getOptionsJson(), new TypeReference<List<String>>() {});
//        options.remove(optionToRemove);
//        question.setOptionsJson(serializeListToJson(options));
//
//        questionRepository.save(question);
//    }
//
//    public List<String> getOptions(UUID questionId) {
//        Question question = questionRepository.findById(questionId)
//                .orElseThrow(() -> new RuntimeException("Question not found"));
//
//        return parseJsonToList(question.getOptionsJson(), new TypeReference<List<String>>() {});
//    }
//
//    private <T> T parseJsonToList(String json, TypeReference<T> typeReference) {
//        try {
//            return json != null ? objectMapper.readValue(json, typeReference) : objectMapper.convertValue(new ArrayList<>(), typeReference);
//        } catch (Exception e) {
//            throw new RuntimeException("Error parsing JSON", e);
//        }
//    }
//
//    private String serializeListToJson(Object list) {
//        try {
//            return objectMapper.writeValueAsString(list);
//        } catch (Exception e) {
//            throw new RuntimeException("Error serializing list to JSON", e);
//        }
//    }
//
//    // Similarly for steps and tags
//
//
//    private final QuestionRepository questionRepository;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//

//
//
//    private <T> T parseJsonToList(String json, TypeReference<T> typeReference) {
//       try {
//            return json != null ? objectMapper.readValue(json, typeReference) : objectMapper.convertValue(new ArrayList<>(), typeReference);
//        } catch (Exception e) {
//           throw new RuntimeException("Error parsing JSON", e);
//        }
//    }
//
//
//    private String serializeListToJson(Object list) {
//        try{
//            return objectMapper.writeValueAsString(list);
//        }catch (Exception e){
//            throw new RuntimeException("Error serializing list to JSON", e);
//        }
//    }


//
//
//
//    private <T> T parseJsonToList(String json, TypeReference<T> typeReference){
//        try{
//            return json != null ? objectMapper.readValue(json,typeReference) : objectMapper.convertValue(new ArrayList<>(),typeReference);
//        }catch (Exception e){
//            throw new RuntimeException("Error parsing JSON", e);
//        }
//    }



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









    private String serializeListToJson(Object list){
        try{
            return objectMapper.writeValueAsString(list);
        }catch (Exception e){
            throw new RuntimeException("Error serializing list to JSON", e);
        }
    }


}

