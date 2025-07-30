package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class LeaveRequestDTO {
    private String employeeId;
    private String startDate;
    private String endDate;
    private String reason;
    private String justification;
    private String certificatePath;
    private String employeeName;
    private String employeePosition;
    private String managerId;

    public void setManagerDecision(String managerDecision) {
    }

    public void setManagerComment(String managerComment) {
    }
}

