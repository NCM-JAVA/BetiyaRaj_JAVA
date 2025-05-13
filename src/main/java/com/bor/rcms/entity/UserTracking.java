package com.bor.rcms.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class UserTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String module;
    private String action;
    private String apiEndpoint;
    private String status;
    private Timestamp startTime;
    private Timestamp endTime; 
    private Timestamp executionTime;

    public UserTracking() {
    }

    public UserTracking(Long userId, String module, String action, String apiEndpoint, String status,
                       Timestamp startTime, Timestamp endTime, Timestamp executionTime) {
        this.userId = userId;
        this.module = module;
        this.action = action;
        this.apiEndpoint = apiEndpoint;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.executionTime = executionTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public void setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getExecutionTime() {
        return executionTime;
    }

//    public void setExecutionTime(Timestamp executionTime) {
//        this.executionTime = executionTime;
//    }

	@Override
	public String toString() {
		return "UserTracking [id=" + id + ", userId=" + userId + ", module=" + module + ", action=" + action
				+ ", apiEndpoint=" + apiEndpoint + ", status=" + status + ", startTime=" + startTime + ", endTime="
				+ endTime + ", executionTime=" + executionTime + "]";
	}
}