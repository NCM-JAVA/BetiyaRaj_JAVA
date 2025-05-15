package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.AddRecoveryAmmount;
@Repository
public interface AddRecoveryAmmountRepo extends JpaRepository<AddRecoveryAmmount, Long> {

	AddRecoveryAmmount findBycaseId(String caseId);

	
}
