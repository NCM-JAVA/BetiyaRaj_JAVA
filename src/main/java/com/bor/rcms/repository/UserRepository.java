package com.bor.rcms.repository;

import com.bor.rcms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
