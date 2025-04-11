package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.AppealCaseCollector;

@Repository
public interface AppealCollectorRepo extends JpaRepository<AppealCaseCollector, Long> {

	@Query("SELECT n FROM AppealCaseCollector n WHERE n.district = :district")
	List<AppealCaseCollector> findAllByDistrict(@Param("district") String district);
//	@Query("SELECT MAX(o.collectorCase) FROM CaseCollector o")
//	String findHighestCollectorCase();


//    @Query("SELECT n FROM caseCollector n WHERE n.status = 'Appeal'")
//	List<CaseCollector> findAllApeal();

}
