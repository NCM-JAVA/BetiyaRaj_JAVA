package com.bor.rcms.repository;

import com.bor.rcms.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByRoleName(String roleName);
}
