package com.example.typeformclone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String type;

    @ElementCollection
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonBackReference
    private Form form;
}
