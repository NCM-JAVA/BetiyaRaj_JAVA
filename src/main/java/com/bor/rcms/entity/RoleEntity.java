package com.bor.rcms.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true, nullable = false)
    private String roleName;

    private String rolePermission;

    public RoleEntity() {}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(String rolePermission) {
		this.rolePermission = rolePermission;
	}

	public RoleEntity(String roleName, String rolePermission) {
		super();
		this.roleName = roleName;
		this.rolePermission = rolePermission;
	}

	public RoleEntity(Long roleId, String roleName, String rolePermission) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.rolePermission = rolePermission;
	}
}
