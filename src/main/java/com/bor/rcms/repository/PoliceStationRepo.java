package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.PoliceStation;

@Repository
public interface PoliceStationRepo extends JpaRepository<PoliceStation, Long> {
@Query("from PoliceStation where com_id=:com_id")
  List<PoliceStation>	findBycomId(@Param("com_id") Long com_id);

}
