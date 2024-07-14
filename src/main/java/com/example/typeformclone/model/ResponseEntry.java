package com.example.typeformclone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ResponseEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    private Long questionId;
    private String answer;
}
