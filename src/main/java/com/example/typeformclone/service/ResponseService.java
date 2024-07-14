package com.example.typeformclone.service;

import com.example.typeformclone.model.Form;
import com.example.typeformclone.model.Response;
import com.example.typeformclone.model.User;
import com.example.typeformclone.repository.FormRepository;
import com.example.typeformclone.repository.ResponseRepository;
import com.example.typeformclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormRepository formRepository;

    public Response saveResponse(Response response, Long userId, Long formId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Form form = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));

        response.setUser(user);
        response.setForm(form);
        response.getEntries().forEach(entry -> entry.setResponse(response));

        // Add points to the user
        user.setPoints(user.getPoints() + 10); // Adjust points as needed
        userRepository.save(user);

        return responseRepository.save(response);
    }

    public List<Response> findByFormId(Long formId) {
        return responseRepository.findByForm_Id(formId);
    }

    public List<Response> findByUserId(Long userId) {
        return responseRepository.findByUser_Id(userId);
    }
}
