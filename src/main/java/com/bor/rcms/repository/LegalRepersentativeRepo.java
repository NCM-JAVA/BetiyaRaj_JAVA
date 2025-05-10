package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.LegalRepresentative;

@Repository
public interface LegalRepersentativeRepo  extends JpaRepository<LegalRepresentative, Long> {
	

}
