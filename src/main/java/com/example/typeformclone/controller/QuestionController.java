package com.example.typeformclone.controller;

import com.example.typeformclone.model.Question;
import com.example.typeformclone.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping("/form/{formId}")
    public List<Question> getQuestionsByFormId(@PathVariable Long formId) {
        return questionService.getQuestionsByFormId(formId);
    }
}

