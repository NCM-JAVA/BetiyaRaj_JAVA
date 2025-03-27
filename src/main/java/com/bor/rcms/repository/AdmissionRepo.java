package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;

@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Long> {

    Admission findByNewObjection(NewObjection newObjection);

    Admission findByAdmissionId(Long caseId);

    // Use JPQL and ensure status is a field in the Admission entity
    @Query("SELECT a FROM Admission a WHERE a.status = 'Dismiss'")
    List<Admission> findAlldismis();

    @Query("SELECT a FROM Admission a WHERE a.status = 'Appeal'")
    List<Admission> findAlldismispending();
    
    
    @Query("SELECT a FROM Admission a WHERE a.hearingDate = :hearingDate AND (a.status != 'Reject' AND a.status != 'Transfer')")
    List<Admission> findByHearingDateOrStatus(@Param("hearingDate") String hearingDate);
//    @Query("SELECT a FROM Admission a WHERE a.hearingDate = :hearingDate AND (a.status != 'Reject' AND a.status != 'Transfer')")
//    List<Admission> findByHearingDateOrStatus(String hearingDate);
//
////	@Query("SELECT * FROM Admission n WHERE n.status = 'Dismiss' AND n.admission_id = :userId")
//
//	List<Admission> findAllDismissedById(String adId);
    @Query(value = "SELECT * FROM admission n WHERE n.status = 'Dismiss' AND n.status_collector = 'pending' AND n.user_id = :userId", nativeQuery = true)
    List<Admission> findDismissStatusByUserId(@Param("userId") Long userId);


    @Query("SELECT MAX(o.admisionCase) FROM Admission o")
    String findHighestAdmisionCase();

    @Query("SELECT a FROM Admission a WHERE a.admisionCase = :admisionCase AND a.status = 'Dismiss'")
    Admission findByAdmisionCase(@Param("admisionCase") String admisionCase);

    Optional<Admission> findTopByDistrictOrderByCurrentdateDesc(String district);

    @Query(value = "SELECT * FROM admission n WHERE n.status = 'Dismiss' AND n.statusCollector = 'Freehold'", nativeQuery = true)
	List<Admission> findAlldivdetails();

    
    @Query(value = "SELECT * FROM admission n WHERE n.status = 'Dismiss' AND n.status_collector = 'Dismiss' AND n.collector_freehold='Freehold' AND n.user_id = :userId", nativeQuery = true)
	List<Admission> findDismissStatusByUserIdfreeHold(@Param("userId") Long userId);

    @Query("SELECT a FROM Admission a WHERE a.admisionCase = :admisionCase AND a.status != 'Dismiss'")
    Admission findByAdmisionCasemodify(String admisionCase);
    @Query(value = "SELECT * FROM admission n WHERE n.status = 'Dismiss' AND n.status_collector = 'Dismiss' AND n.collector_freehold='Freehold' And n.status_commissioner='pending'" , nativeQuery = true)
	List<Admission> findAlldivdetailsById();


//    @Query("SELECT a FROM Admission a WHERE a.admissionCase = :admissionCase AND a.status = 'Dismiss'")
//    List<Admission> findByAdmissionCaseAndStatusDismissed(String caseId);



}
