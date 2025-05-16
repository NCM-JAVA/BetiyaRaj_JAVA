package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CaseNotesPdr;
import com.bor.rcms.entity.FileRequeistion;
@Repository
public interface CaseNotesPdrRepo extends JpaRepository<CaseNotesPdr, Long> {

	CaseNotesPdr findByFileRequeistion(FileRequeistion newObjection);

	@Query(value = "SELECT * FROM case_notes_pdr WHERE case_id = :caseId", nativeQuery = true)
	CaseNotesPdr findByCaseId(@Param("caseId") String caseId);


}
