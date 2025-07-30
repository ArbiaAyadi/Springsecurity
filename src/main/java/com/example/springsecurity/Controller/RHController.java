package com.example.springsecurity.Controller;

import com.example.springsecurity.dto.RHDecision;
import com.example.springsecurity.dto.LeaveRequestDTO;
import com.example.springsecurity.Services.RHService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class RHController {

    private final RHService hrService;

    @GetMapping("/approved-requests")
    public ResponseEntity<List<LeaveRequestDTO>> getApprovedRequests() {
        return ResponseEntity.ok(hrService.getApprovedRequests());
    }

    @PostMapping("/process-request/{taskId}")
    public ResponseEntity<String> processHRRequest(
            @PathVariable String taskId,
            @RequestBody RHDecision decision) {
        return ResponseEntity.ok(hrService.processHRRequest(taskId, decision));
    }

    @GetMapping("/request-details/{taskId}")
    public ResponseEntity<LeaveRequestDTO> getHRRequestDetails(@PathVariable String taskId) {
        return ResponseEntity.ok(hrService.getHRRequestDetails(taskId));
    }
}

