package com.example.typeformclone.controller;

import com.example.typeformclone.model.Form;
import com.example.typeformclone.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping("/{userId}")
    public ResponseEntity<Form> createForm(@PathVariable Long userId, @RequestBody Form form) {
        Form createdForm = formService.saveForm(form, userId);
        return ResponseEntity.ok(createdForm);
    }

    @GetMapping
    public List<Form> getForms() {
        return formService.getForms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Long id) {
        Optional<Form> form = formService.getFormById(id);
        if (form.isPresent()) {
            return ResponseEntity.ok(form.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
