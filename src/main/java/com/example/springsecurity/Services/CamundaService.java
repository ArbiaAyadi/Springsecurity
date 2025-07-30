package com.example.springsecurity.Services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class CamundaService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Notification envoyée au manager.");
        System.out.println("Notification envoyée au RH.");

        String decision = (String) execution.getVariable("validation");

        if ("acceptée".equalsIgnoreCase(decision)) {
            System.out.println("Information d'acceptation envoyée à l'employé.");
        } else if ("refusée".equalsIgnoreCase(decision)) {
            System.out.println("Information de refus envoyée à l'employé.");
        } else {
            System.out.println("Statut de validation inconnu.");
        }
    }

}



