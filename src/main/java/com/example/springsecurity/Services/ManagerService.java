package com.example.springsecurity.Services;

import com.example.springsecurity.Models.LeaveRequest;
import com.example.springsecurity.dto.LeaveRequestDTO;
import com.example.springsecurity.dto.ManagerDecision;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final NotificationMessageService notificationService;

    public List<LeaveRequest> getPendingRequests(String managerId) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey("traiter_demande_manager")
                .taskCandidateGroup("managers")
                .list();

        return tasks.stream()
                .map(task -> new LeaveRequest(task.getId(), task.getName()))
                .collect(Collectors.toList());
    }

    public String processRequest(String taskId, ManagerDecision decision) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());

        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("managerDecision", decision.getDecision());
        processVariables.put("managerComment", decision.getComment());
        processVariables.put("managerId", decision.getManagerId());

        taskService.complete(taskId, processVariables);

        // Notify employee if refused
        String employeeId = (String) variables.get("employeeId");
        if ("refus".equalsIgnoreCase(decision.getDecision())) {
            notificationService.notifyEmployee(employeeId, "Votre demande de congé a été refusée");
        }

        return "Décision enregistrée";
    }

    public LeaveRequestDTO getRequestDetails(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());

        LeaveRequestDTO details = new LeaveRequestDTO();
        details.setStartDate((String) variables.get("startDate"));
        details.setEndDate((String) variables.get("endDate"));
        details.setReason((String) variables.get("reason"));
        details.setJustification((String) variables.get("justification"));
        details.setEmployeeName((String) variables.get("employeeName"));
        details.setEmployeePosition((String) variables.get("employeePosition"));

        return details;
    }
}

