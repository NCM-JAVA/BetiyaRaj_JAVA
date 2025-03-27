package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CaseStatusEntity;

@Repository
public interface CaseStatusRepository extends JpaRepository<CaseStatusEntity, Long> {
    List<CaseStatusEntity> findByCaseEntity_CaseId(Long caseId);
}