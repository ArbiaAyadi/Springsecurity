package com.example.springsecurity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ManagerDecision {
    private String decision;
    private String comment;
    private String managerId;
}

