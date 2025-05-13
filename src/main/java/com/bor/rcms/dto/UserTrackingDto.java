package com.bor.rcms.dto;

import java.sql.Timestamp;

public class UserTrackingDto {
    private Long userId;
    private String module;
    private String action;
    private String apiEndpoint;
    private String status;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp executionTime;
    private String httpMethod;
    private String parameters;
    private String errorMessage;

    // Default constructor
    public UserTrackingDto() {
    }

    // Constructor with essential fields
    public UserTrackingDto(Long userId, String module, String action, String apiEndpoint, 
                         String status, Timestamp startTime, Timestamp endTime, 
                         Timestamp executionTime) {
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

    public void setExecutionTime(Timestamp executionTime) {
        this.executionTime = executionTime;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "UserTrackingDto{" +
                "userId=" + userId +
                ", module='" + module + '\'' +
                ", action='" + action + '\'' +
                ", apiEndpoint='" + apiEndpoint + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", executionTime=" + executionTime +
                ", httpMethod='" + httpMethod + '\'' +
                ", parameters='" + parameters + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}