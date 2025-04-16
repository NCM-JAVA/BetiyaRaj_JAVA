package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.FreeholdIntitalCollector;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.UserEntity;

@Repository
public interface FileRequeistionRepo extends JpaRepository<FileRequeistion, String> {

	Optional<FileRequeistion> findTopByDistrictNameOrderByCurrentDateDesc(String districtName);

	 @Query("SELECT n FROM FileRequeistion n WHERE n.districtName = :districtName And n.status = 'pending'")
	    List<FileRequeistion> findAllPending(@Param("districtName") String districtName);

	Object findAllByRequeistionId(String obId);

	Optional<FileRequeistion> findByRequeistionId(String objId);

	Optional<FileRequeistion> findTopByDistrictNameAndRequeistionIdStartingWithOrderByRequeistionIdDesc(
			String districtName, String prefix);

	 @Query("SELECT n FROM FileRequeistion n WHERE n.districtName = :districtName And n.status = 'Admit'")
	    List<FileRequeistion> findAllAdmit(@Param("districtName") String districtName);




}
