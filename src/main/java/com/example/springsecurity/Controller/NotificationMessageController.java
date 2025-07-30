package com.example.springsecurity.Controller;


import com.example.springsecurity.Models.NotificationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import com.example.springsecurity.Services.NotificationMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationMessageController {

    private final NotificationMessageService notificationService;

    @MessageMapping("/notifyManager")
    public void notifyManager(@Payload NotificationMessage message) {
        notificationService.notifyManager(message.getSender());
    }

    @MessageMapping("/notifyEmployee")
    public void notifyEmployee(@Payload NotificationMessage message) {
        notificationService.notifyEmployee(message.getReceiverRole(), message.getContent());
    }

    @MessageMapping("/notifyRh")
    public void notifyRh(@Payload NotificationMessage message) {
        notificationService.notifyRh(message.getSender());
    }

    // Juste pour ajouter un utilisateur dans la session
    @MessageMapping("/addUser")
    public void addUser(@Payload NotificationMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
    }
}

