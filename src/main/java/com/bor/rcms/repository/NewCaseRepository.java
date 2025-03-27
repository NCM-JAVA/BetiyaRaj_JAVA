package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.NewCaseEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface NewCaseRepository extends JpaRepository<NewCaseEntity, Long> {
	List<NewCaseEntity> findByUser_UserId(Long userId);
}