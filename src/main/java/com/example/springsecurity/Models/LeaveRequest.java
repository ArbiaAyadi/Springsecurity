package com.example.springsecurity.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveRequest {
    private String id;
    private String status;
}
