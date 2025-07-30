package com.example.springsecurity.Controller;

import com.example.springsecurity.Models.LeaveRequest;
import com.example.springsecurity.Services.EmployeeService;
import com.example.springsecurity.dto.LeaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")

public class EmployeController {


    private final EmployeeService employeService;

    public EmployeController(EmployeeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping("/leave-request")
   public ResponseEntity<LeaveRequest> submitLeaveRequest(@RequestBody LeaveRequestDTO request) {
        return ResponseEntity.ok(employeService.handleLeaveRequest(request));
   }

    @GetMapping("/leave-requests/{employeeId}")
  public ResponseEntity<List<LeaveRequest>> getMyLeaveRequests(@PathVariable String employeeId) {
        return ResponseEntity.ok(employeService.getMyLeaveRequests(employeeId));
    }
}
