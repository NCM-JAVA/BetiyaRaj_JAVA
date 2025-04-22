package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CaseCommissioner;
import com.bor.rcms.entity.CourtAdd;
import com.bor.rcms.entity.UserEntity;
@Repository
public interface CourtAddRepo  extends JpaRepository<CourtAdd, Long> {

	List<CourtAdd> findAllByUserId(UserEntity entity);

	CourtAdd findByOfficeMobile(String officeMobile);

}
