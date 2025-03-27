package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.NoticeRelease;
@Repository
public interface NoticeRepo extends JpaRepository<NoticeRelease, Long> {

}
