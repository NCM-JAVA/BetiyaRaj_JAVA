package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CaseCollector;
import com.bor.rcms.entity.CaseCommissioner;
import com.bor.rcms.entity.DocumentEntity;

@Repository
public interface CommissionerRepo extends JpaRepository<CaseCommissioner, Long> {

	Optional<CaseCollector> findTopByDistrictOrderByCurrentDateDesc(String district);

	   @Query("SELECT a FROM CaseCommissioner a WHERE a.status ='Admit'")
	List<CaseCommissioner> findAlladmit();

}
