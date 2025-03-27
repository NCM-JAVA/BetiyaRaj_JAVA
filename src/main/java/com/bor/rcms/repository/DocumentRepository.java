package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.NewObjection;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByObjection(NewObjection objection);
    // No need to declare saveAll method, JpaRepository already provides it
}