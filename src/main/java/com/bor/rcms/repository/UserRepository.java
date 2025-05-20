package com.bor.rcms.repository;

import com.bor.rcms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
    
	UserEntity findByPhoneNumber(String phoneNumber);
	UserEntity findByAdhar(String aadharNumber);
	List<UserEntity> findByDistrict(String district);
	UserEntity findByEmail(String email);
	List<UserEntity> findByCreatedByuser(Long userId);
	UserEntity findByUserId(Long valueOf);
	 @Query("SELECT u FROM UserEntity u WHERE " +
	           "(:bank IS NULL OR u.bankName = :bank) OR " +
	           "(:branchCode IS NULL OR u.branchCode = :branchCode) OR " +
	           "(:sector IS NULL OR u.sector = :sector) OR " +
	           "(:department IS NULL OR u.department = :department)")
	    List<UserEntity> findByOptionalFields(@Param("bank") String bank,
	                                          @Param("branchCode") String branchCode,
	                                          @Param("sector") String sector,
	                                          @Param("department") String department);

	 
	 @Query(
			    value = "SELECT * FROM users u WHERE LOWER(u.district) LIKE LOWER(CONCAT('%', :district, '%')) AND u.role_id IN (1, 2)",
			    nativeQuery = true
			)
			List<UserEntity> findUsersByDistrictAndRole(@Param("district") String district);

}
