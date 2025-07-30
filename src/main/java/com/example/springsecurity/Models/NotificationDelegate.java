package com.example.springsecurity.Models;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationDelegate implements JavaDelegate {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationDelegate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String decision = (String) execution.getVariable("decision");
        String userId = (String) execution.getVariable("userId");

        String notificationMessage = "";

        if ("approved".equals(decision)) {
            notificationMessage = "Votre demande a été acceptée.";
        } else if ("rejected".equals(decision)) {
            notificationMessage = "Votre demande a été refusée.";
        } else {
            notificationMessage = "Statut de la demande inconnu.";
        }

        messagingTemplate.convertAndSend("/topic/notifications/" + userId, notificationMessage);

        System.out.println("Notification WebSocket envoyée à l'utilisateur " + userId + ": " + notificationMessage);
    }
}
