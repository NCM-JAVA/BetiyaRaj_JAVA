package com.bor.rcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.FreeholdIntitalCollector;
import com.bor.rcms.entity.LocationDetails;

@Repository
public interface  FreeholdIntitalCollectorRepo  extends JpaRepository<FreeholdIntitalCollector, Long> {

}
