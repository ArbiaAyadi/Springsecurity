package com.example.springsecurity.Controller;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/conge")
public class StartProcessController {

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/start")
    public ResponseEntity<String> startProcess(@RequestBody Map<String, Object> variables) {
        runtimeService.startProcessInstanceByKey("process_demande_conge", variables);
        return ResponseEntity.ok("Processus de demande de congé démarré !");
    }
}
