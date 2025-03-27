package com.bor.rcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.LocationDetails;
import com.bor.rcms.entity.Mis;
@Repository
public interface LoctaionRepo extends JpaRepository<LocationDetails, Long> {

	@Query(value = "SELECT DISTINCT state FROM Location_details", nativeQuery = true)
	List<String> findDistinctState();
	  @Query(value = "SELECT DISTINCT district FROM public.location_details WHERE state = :state", nativeQuery = true)
	    List<String> findDistinctDistrict(@Param("state") String state);
	  
	  @Query("SELECT DISTINCT circle FROM LocationDetails WHERE state = :state AND district = :district")
	  List<String> findDistinctCircle(@Param("state") String state, @Param("district") String district);
	  @Query(value = "SELECT DISTINCT halka FROM public.location_details WHERE LOWER(state) = LOWER(:state) AND LOWER(district) = LOWER(:district) AND LOWER(circle) = LOWER(:circle)", nativeQuery = true)
	  List<String> findDistinctHalka(@Param("state") String state, @Param("district") String district, @Param("circle") String circle);

	  @Query(value = "SELECT DISTINCT mauja FROM Location_details WHERE state = :state AND district = :district AND halka = :halka AND circle = :circle", nativeQuery = true)
	  List<String> findDistinctMauja(@Param("state") String state, @Param("district") String district, @Param("halka") String halka, @Param("circle") String circle);
	  @Query(value = "SELECT DISTINCT khatano FROM Location_details WHERE state = :state AND district = :district AND halka = :halka AND circle = :circle AND mauja = :mauja", nativeQuery = true)
	  List<String> findDistinctKhataNumber(@Param("state") String state, 
	                                       @Param("district") String district, 
	                                       @Param("halka") String halka, 
	                                       @Param("circle") String circle, 
	                                       @Param("mauja") String mauja);

	  @Query(value = "SELECT DISTINCT plotno FROM Location_details WHERE state = :state AND district = :district AND halka = :halka AND circle = :circle AND mauja = :mauja AND khatano = :khatano ", nativeQuery = true)
	    List<String> findDistinctPlotno(@Param("state") String state, @Param("district") String district,
	                                    @Param("halka") String halka, @Param("circle") String circle,
	                                    @Param("mauja") String mauja, @Param("khatano") String khatano);
}
