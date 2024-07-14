package com.example.typeformclone.repository;

import com.example.typeformclone.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByCreatedBy_Id(Long userId);
}
