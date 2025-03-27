package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseCollector;
@Repository("caseCollectorRepoRepository")
public interface CaseCollectorRepo extends JpaRepository<CaseCollector, Long> {
	@Query("SELECT MAX(o.collectorCase) FROM CaseCollector o")
	String findHighestCollectorCase();
	   @Query("SELECT a FROM CaseCollector a WHERE a.status != 'Reject' AND a.status != 'Dismiss'")
	List<CaseCollector> findAlladmit();
	   @Query("SELECT a FROM CaseCollector a WHERE a.hearingDate = :hearingDate AND (a.status != 'Reject' AND a.status != 'Transfer')")
	   List<CaseCollector> findByHearingDateAndStatusNotRejectOrTransfer(@Param("hearingDate") String hearingDate);
	   
		@Query("SELECT a FROM CaseCollector a WHERE a.caseCollectorId = :caseCollectorId AND a.status = 'Dismiss'")
	CaseCollector findByCaseCollectorId(@Param("caseCollectorId") String caseCollectorId);
		CaseCollector findByCollectorCase(String caseId);
		
	    Optional<CaseCollector> findTopByDistrictOrderByCurrentDateDesc(String district);
		CaseCollector findByAdmission(Admission admission2);
		CaseCollector findByObjectioId(String caseId);
	    
	    
//		@Query("SELECT a FROM CaseCollector a WHERE a.collector_case ='Dismiss' AND n.user_id = :userId")
//		List<CaseCollector> findDismiss(@Param("userId") Long userId);
//		CaseCollector findByAdmission(Admission admission2);
	
//	@Query("SELECT a FROM Admission a WHERE a.admisionCase = :admisionCase AND a.status = 'Dismiss'")
//    Admission findByAdmisionCase(@Param("admisionCase") String admisionCase);



}
