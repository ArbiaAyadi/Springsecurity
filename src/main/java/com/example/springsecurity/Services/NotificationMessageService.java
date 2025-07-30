package com.example.springsecurity.Services;

import com.example.springsecurity.Models.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationMessageService {

    private final SimpMessageSendingOperations messagingTemplate;

    public void notifyManager(String demandeur) {
        send("Nouvelle demande de congé de " + demandeur, "MANAGER");
    }

    public void notifyEmployee(String username, String message) {
        send(message, "EMPLOYEE-" + username); // si tu veux ciblé un employé précis
    }

    public void notifyRh(String demandeur) {
        send("Demande de congé acceptée par manager: " + demandeur, "RH");
    }

    private void send(String content, String receiverRole) {
        NotificationMessage message = NotificationMessage.builder()
                .content(content)
                .receiverRole(receiverRole)
                .build();

        messagingTemplate.convertAndSend("/topic/" + receiverRole, message);
    }
}

