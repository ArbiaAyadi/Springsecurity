package com.example.springsecurity.Controller;

import com.example.springsecurity.Models.LeaveRequest;
import com.example.springsecurity.Services.ManagerService;
import com.example.springsecurity.dto.LeaveRequestDTO;
import com.example.springsecurity.dto.ManagerDecision;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/pending-requests")
    public ResponseEntity<List<LeaveRequest>> getPendingRequests(@RequestParam String managerId) {
        return ResponseEntity.ok(managerService.getPendingRequests(managerId));
    }

    @PostMapping("/process-request/{taskId}")
    public ResponseEntity<String> processRequest(
            @PathVariable String taskId,
            @RequestBody ManagerDecision decision) {
        return ResponseEntity.ok(managerService.processRequest(taskId, decision));
    }

    @GetMapping("/request-details/{taskId}")
    public ResponseEntity<LeaveRequestDTO> getRequestDetails(@PathVariable String taskId) {
        return ResponseEntity.ok(managerService.getRequestDetails(taskId));
    }
}

