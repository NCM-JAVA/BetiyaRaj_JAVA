package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CaseTransferPriviouseRecord;

@Repository
public interface CaseTransferPriviouseRecordRepo extends JpaRepository<CaseTransferPriviouseRecord, Long> {

}
