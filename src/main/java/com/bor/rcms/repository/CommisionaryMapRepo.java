package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.CommisionaryMap;

@Repository
public interface CommisionaryMapRepo  extends JpaRepository<CommisionaryMap, Long>{

	List<CommisionaryMap> findByCommisonary(String commissName);
	@Query("SELECT DISTINCT c.commisonary FROM CommisionaryMap c")
	List<String> findDistinctCommisonary();
	
	@Query("SELECT DISTINCT c.distrct FROM CommisionaryMap c")
	List<String> findDistinctdistrct();

}
