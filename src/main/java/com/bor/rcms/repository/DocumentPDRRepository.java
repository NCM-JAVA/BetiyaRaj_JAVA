package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.DocumentEntityPdr;

@Repository
public interface DocumentPDRRepository extends JpaRepository<DocumentEntityPdr, Long> {
  //  List<DocumentEntity> findByObjection(DocumentEntityPdr objection);
    // No need to declare saveAll method, JpaRepository already provides it
}
