package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.CertificateDebator;
@Repository
public interface CertificatDebatorRepo extends JpaRepository<CertificateDebator, Long> {

	CertificateDebator findByPhoneNumber(String phoneNumber);

	CertificateDebator findByEmail(String email);


    @Query(value = "SELECT * FROM certificate_debator WHERE debator_id = :debatorId", nativeQuery = true)
    CertificateDebator findByDebatorIdNative(@Param("debatorId") Long debatorId);
    }
