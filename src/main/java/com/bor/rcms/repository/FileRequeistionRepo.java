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

	@Query("SELECT n FROM FileRequeistion n WHERE n.districtName = :districtName AND n.status = 'pending' AND n.isTransOfficer = true")
	    List<FileRequeistion> findAllPending(@Param("districtName") String districtName);

	Object findAllByRequeistionId(String obId);

	Optional<FileRequeistion> findByRequeistionId(String objId);

	Optional<FileRequeistion> findTopByDistrictNameAndRequeistionIdStartingWithOrderByRequeistionIdDesc(
			String districtName, String prefix);

	 @Query("SELECT n FROM FileRequeistion n WHERE n.districtName = :districtName And n.status = 'Admit' AND n.isTransOfficer = true")
	    List<FileRequeistion> findAllAdmit(@Param("districtName") String districtName);

	List<FileRequeistion> findByTransNomId(String userId);

	List<FileRequeistion> findByUserId(UserEntity entity);

	Optional<FileRequeistion> findByRequeistionIdAndCurrentDate(String caseId, String caseDate);

	Optional<FileRequeistion> findByCurrentDate(String caseDate);

	@Query("SELECT n FROM FileRequeistion n WHERE n.userId.userId = :userId AND n.isTransNomOfficer = true")
	List<FileRequeistion> findAllisTransNomOfficer(@Param("userId") String userId);



}
