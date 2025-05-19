package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.LegalRepresentative;

@Repository
public interface LegalRepersentativeRepo  extends JpaRepository<LegalRepresentative, Long> {

	List<LegalRepresentative> findByFileRequeistion(FileRequeistion newObjection);
	

}
