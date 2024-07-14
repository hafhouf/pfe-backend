package com.example.typeformclone.service;

import com.example.typeformclone.model.Form;
import com.example.typeformclone.model.User;
import com.example.typeformclone.repository.FormRepository;
import com.example.typeformclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;

    @Autowired
    private UserRepository userRepository;

    public Form saveForm(Form form, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        form.setCreatedBy(user);
        form.getQuestions().forEach(question -> question.setForm(form));
        return formRepository.save(form);
    }

    public List<Form> getForms() {
        return formRepository.findAll();
    }

    public Optional<Form> getFormById(Long id) {
        return formRepository.findById(id);
    }

}
