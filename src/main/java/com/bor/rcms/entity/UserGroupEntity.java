package com.bor.rcms.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_groups")
public class UserGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id") // Ensuring correct mapping
    private Long groupId;

    @Column(unique = true, nullable = false)
    private String groupName;

    @ManyToMany(mappedBy = "groups")
    private Set<UserEntity> users;

    public UserGroupEntity() {}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
}
