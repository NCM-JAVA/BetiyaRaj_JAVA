package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CommisionaryMap;
import com.bor.rcms.entity.PoliceStation;

@Repository
public interface CommisionaryMapRepo extends JpaRepository<CommisionaryMap, Long> {

	List<CommisionaryMap> findByCommisonary(String commissName);

	@Query("SELECT DISTINCT c.commisonary FROM CommisionaryMap c")
	List<String> findDistinctCommisonary();

	@Query("SELECT DISTINCT c.distrct FROM CommisionaryMap c")
	List<String> findDistinctdistrct();

	@Query("from CommisionaryMap where distrct=:distrct")
	List<CommisionaryMap> findByDistrct(@Param("distrct") String distrct);
	
	@Query("from CommisionaryMap where com_id=:com_id")
	  List<CommisionaryMap>	findBycomId(@Param("com_id") Long com_id);


}
