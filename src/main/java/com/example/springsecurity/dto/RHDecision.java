package com.example.springsecurity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RHDecision {
    private String decision;
    private String comment;
    private String hrId;
}

