package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewCaseEntity;

@Repository
public interface Misrepo extends JpaRepository<Mis, Long> {

	Mis findByObjId(String objId);
}
