package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.DraftSaveCaseProceeding;
@Repository
public interface DraftsSaveRepo extends JpaRepository<DraftSaveCaseProceeding, Long> {

	@Query(value = "SELECT * FROM draft_save_case_proceeding WHERE case_id = :caseId ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
	DraftSaveCaseProceeding findLatestDraftByCaseId(@Param("caseId") String caseId);

	DraftSaveCaseProceeding findBycaseId(String caseId);

}
