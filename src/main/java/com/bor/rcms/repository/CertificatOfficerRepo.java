package com.bor.rcms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseStatusEntity;
import com.bor.rcms.entity.CertificatOfficer;
@Repository
public interface CertificatOfficerRepo extends JpaRepository<CertificatOfficer, String> {

	Optional<CertificatOfficer> findTopByDistrictOrderByCurrentdateDesc(String districtName); 

}
