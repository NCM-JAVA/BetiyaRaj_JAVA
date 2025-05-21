package com.bor.rcms.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//not used
@Entity
public class CourtAdd {

	  @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	  private String officeDetails;
		
		private String officeName;

		private String officerEmail;

		private String officeMobile;
		
		private String status;
		
		private String  password;
		
		private String assignUSer;

		  @OneToOne
			@JoinColumn(name = "user_id", referencedColumnName = "user_id")

		    private UserEntity userId;
		  
		  

	 private String Role;

		public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getOfficeDetails() {
			return officeDetails;
		}

		public void setOfficeDetails(String officeDetails) {
			this.officeDetails = officeDetails;
		}

		public String getOfficeName() {
			return officeName;
		}

		public void setOfficeName(String officeName) {
			this.officeName = officeName;
		}

		public String getOfficerEmail() {
			return officerEmail;
		}

		public void setOfficerEmail(String officerEmail) {
			this.officerEmail = officerEmail;
		}

		public String getOfficeMobile() {
			return officeMobile;
		}

		public void setOfficeMobile(String officeMobile) {
			this.officeMobile = officeMobile;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAssignUSer() {
			return assignUSer;
		}

		public void setAssignUSer(String assignUSer) {
			this.assignUSer = assignUSer;
		}

		

	
		public UserEntity getUserId() {
			return userId;
		}

		public void setUserId(UserEntity userId) {
			this.userId = userId;
		}

		
		@Override
		public String toString() {
			return "CourtAdd [id=" + id + ", officeDetails=" + officeDetails + ", officeName=" + officeName
					+ ", officerEmail=" + officerEmail + ", officeMobile=" + officeMobile + ", status=" + status
					+ ", password=" + password + ", assignUSer=" + assignUSer + ", userId=" + userId + ", Role=" + Role
					+ "]";
		}

		public CourtAdd() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		


}