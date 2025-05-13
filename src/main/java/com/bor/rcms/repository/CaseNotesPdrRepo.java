package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CaseNotesPdr;
import com.bor.rcms.entity.FileRequeistion;
@Repository
public interface CaseNotesPdrRepo extends JpaRepository<CaseNotesPdr, Long> {

	CaseNotesPdr findByFileRequeistion(FileRequeistion newObjection);

	CaseNotesPdr findByCaseId(String caseId);


}
