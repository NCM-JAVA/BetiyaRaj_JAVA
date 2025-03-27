package com.bor.rcms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bor.rcms.entity.NewCaseEntity;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.UserEntity;
@Repository
public interface NewObjectionRepo extends JpaRepository<NewObjection, Long> {
	 @Query("SELECT MAX(o.tokenNo) FROM NewObjection o")
	    String findHighestToken();

	List<NewObjection> findByUserId_UserId(Long userId);
	 @Query("SELECT n FROM NewObjection n WHERE n.districtName = :districtName And n.status = 'pending'")
	    List<NewObjection> findAllPending(@Param("districtName") String districtName);

	 @Query(value = "SELECT * FROM public.new_objectioner WHERE user_id = ?1", nativeQuery = true)
	    List<NewObjection> findByUserId(Long userId);	    
	 @Query("SELECT n FROM NewObjection n WHERE n.status <> 'Reject' AND n.status <> 'pending' AND n.status <> 'Dismiss'")
	 List<NewObjection> findAllApproved();


		NewObjection findByObjectionId(Long objids);
		
		@Query("SELECT n FROM NewObjection n WHERE n.status = 'Dismiss' AND n.userId.userId = :userId")
		List<NewObjection> findAllDismissedByUserId(@Param("userId") Long userId);

		@Query("SELECT n FROM NewObjection n WHERE n.statusCollector IS NOT NULL")
		List<NewObjection> findcollerstatus();

	    @Query("SELECT n.objectionId FROM NewObjection n WHERE n.districtName = :district AND SUBSTRING(CAST(n.objectionId AS text), 9, 2) = :year ORDER BY n.objectionId DESC")
	    String findHighestTokenForDistrictAndYear(@Param("district") String district, @Param("year") String year);

	    @Query(value = "SELECT * FROM new_objectioner WHERE district_name = :districtName ORDER BY current_date DESC, token_no DESC LIMIT 1", nativeQuery = true)
	    Optional<NewObjection> findTopByDistrictNameOrderByCurrentDateDesc(@Param("districtName") String districtName);

	    @Query(value = "SELECT * FROM new_objectioner WHERE district_name = :districtName", nativeQuery = true)
		List<NewObjection> findAlldistrict(@Param("districtName") String districtName);

	 
}
