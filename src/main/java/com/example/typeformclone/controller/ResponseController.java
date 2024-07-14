package com.example.typeformclone.controller;

import com.example.typeformclone.model.Response;
import com.example.typeformclone.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<Response> createResponse(@RequestBody Response response, @RequestParam Long userId, @RequestParam Long formId) {
        Response createdResponse = responseService.saveResponse(response, userId, formId);
        return ResponseEntity.ok(createdResponse);
    }

    @GetMapping("/form/{formId}")
    public List<Response> getResponsesByFormId(@PathVariable Long formId) {
        return responseService.findByFormId(formId);
    }

    @GetMapping("/user/{userId}")
    public List<Response> getResponsesByUserId(@PathVariable Long userId) {
        return responseService.findByUserId(userId);
    }
}
