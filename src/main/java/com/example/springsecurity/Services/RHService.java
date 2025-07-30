package com.example.springsecurity.Services;

import com.example.springsecurity.dto.RHDecision;
import com.example.springsecurity.dto.LeaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RHService {

    private final TaskService taskService;
    private final RuntimeService runtimeService;
    private final NotificationMessageService notificationService;

    public List<LeaveRequestDTO> getApprovedRequests() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskDefinitionKey("traiter_demande_rh")
                .taskCandidateGroup("hr")
                .list();

        return tasks.stream()
                .map(this::convertToLeaveRequest)
                .collect(Collectors.toList());
    }

    public String processHRRequest(String taskId, RHDecision decision) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());

        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("hrDecision", decision.getDecision());
        processVariables.put("hrComment", decision.getComment());
        processVariables.put("hrId", decision.getHrId());

        taskService.complete(taskId, processVariables);

        String employeeId = (String) variables.get("employeeId");
        String message = "Votre demande de congé a été ";

        if ("approuvé".equalsIgnoreCase(decision.getDecision())) {
            message += "approuvée";
        } else {
            message += "refusée par les RH";
        }

        notificationService.notifyEmployee(employeeId, message);
        return "Décision RH enregistrée";
    }

    public LeaveRequestDTO getHRRequestDetails(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());

        LeaveRequestDTO details = new LeaveRequestDTO();
        details.setStartDate((String) variables.get("startDate"));
        details.setEndDate((String) variables.get("endDate"));
        details.setReason((String) variables.get("reason"));
        details.setJustification((String) variables.get("justification"));
        details.setEmployeeName((String) variables.get("employeeName"));
        details.setEmployeePosition((String) variables.get("employeePosition"));
        details.setManagerDecision((String) variables.get("managerDecision"));
        details.setManagerComment((String) variables.get("managerComment"));

        return details;
    }

    private LeaveRequestDTO convertToLeaveRequest(Task task) {
        Map<String, Object> vars = runtimeService.getVariables(task.getProcessInstanceId());
        LeaveRequestDTO details = new LeaveRequestDTO();

        details.setStartDate((String) vars.get("startDate"));
        details.setEndDate((String) vars.get("endDate"));
        details.setReason((String) vars.get("reason"));
        details.setJustification((String) vars.get("justification"));
        details.setEmployeeName((String) vars.get("employeeName"));
        details.setEmployeePosition((String) vars.get("employeePosition"));
        details.setManagerDecision((String) vars.get("managerDecision"));
        details.setManagerComment((String) vars.get("managerComment"));

        return details;
    }
}

