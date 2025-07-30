package com.example.springsecurity.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private String sender;
    private String content;
    private String receiverRole; // RH, MANAGER, EMPLOYEE-username
}

