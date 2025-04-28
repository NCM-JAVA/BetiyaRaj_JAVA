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
@Repository
public interface CertificatOfficerRepo extends JpaRepository<CertificatOfficer, String> {

	Optional<CertificatOfficer> findTopByDistrictOrderByCurrentdateDesc(String districtName);

	CertificatOfficer findByFileRequeistion(FileRequeistion newObjection);

    @Query(value = "SELECT * FROM certificat_officer WHERE admission_date = :admission_date", nativeQuery = true)

    List<CertificatOfficer> findByAdmissionTime(@Param("admission_date") String admissionTime);


}
