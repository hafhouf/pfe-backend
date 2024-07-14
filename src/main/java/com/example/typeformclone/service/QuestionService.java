package com.example.typeformclone.service;

import com.example.typeformclone.model.Question;
import com.example.typeformclone.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByFormId(Long formId) {
        return questionRepository.findByFormId(formId);
    }
}
