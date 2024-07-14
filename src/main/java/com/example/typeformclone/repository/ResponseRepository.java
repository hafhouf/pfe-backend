package com.example.typeformclone.repository;

import com.example.typeformclone.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByForm_Id(Long formId);
    List<Response> findByUser_Id(Long userId);
}
