package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
	List<FileEntity> findByUser_UserId(Long userId);
}
