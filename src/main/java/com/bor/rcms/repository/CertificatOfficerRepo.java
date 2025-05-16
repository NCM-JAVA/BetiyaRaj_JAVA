package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseStatusEntity;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.UserEntity;
@Repository
public interface CertificatOfficerRepo extends JpaRepository<CertificatOfficer, String> {

	Optional<CertificatOfficer> findTopByDistrictOrderByCurrentdateDesc(String districtName);
	
	 @Query(value = "SELECT * FROM certificat_officer " +
             "WHERE cert_officer_id ILIKE CONCAT(:prefix, '%') " +
             "ORDER BY cert_officer_id DESC " +
             "LIMIT 1",
     nativeQuery = true)
Optional<CertificatOfficer> findLatestByCertOfficerIdPrefixNative(@Param("prefix") String prefix);

	

	CertificatOfficer findByFileRequeistion(FileRequeistion newObjection);

    @Query(value = "SELECT * FROM certificat_officer WHERE admission_date = :admission_date", nativeQuery = true)

    List<CertificatOfficer> findByAdmissionTime(@Param("admission_date") String admissionTime);

    Optional<CertificatOfficer> findByReequeistionIdAndHearingDate(String reequeistionId, String hearingDate);

	Optional<CertificatOfficer> findByCertOfficerIdAndHearingDate(String caseId, String caseDate);

	Optional<CertificatOfficer> findByCertOfficerId(String caseId);

	Optional<CertificatOfficer> findByadmissionDate(String caseDate);


	List<CertificatOfficer> findByDistrict(String district);


	List<CertificatOfficer> findAllByuserId(UserEntity entity);

	Optional<CertificatOfficer> findTopByDistrictAndAdmisionCaseStartingWithOrderByAdmisionCaseDesc(String district,
			String prefix);



}
