package com.example.springsecurity.Services;

import com.example.springsecurity.Models.LeaveRequest;
import com.example.springsecurity.dto.LeaveRequestDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final NotificationMessageService notificationService;

    public EmployeeService(RuntimeService runtimeService, TaskService taskService, NotificationMessageService notificationService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.notificationService = notificationService;
    }

    public LeaveRequest handleLeaveRequest(LeaveRequestDTO request) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "leave-request-process",
                request.getEmployeeId(),
                Map.of(
                        "startDate", request.getStartDate(),
                        "endDate", request.getEndDate(),
                        "reason", request.getReason(),
                        "justification", request.getJustification(),
                        "certificatePath", request.getCertificatePath(),
                        "employeeName", request.getEmployeeName(),
                        "employeePosition", request.getEmployeePosition()
                )
        );

        notificationService.notifyManager(request.getEmployeeName());

        return new LeaveRequest(processInstance.getId(), "Demande soumise");
    }

    public List<LeaveRequest> getMyLeaveRequests(String employeeId) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(employeeId)
                .list();

        return tasks.stream()
                .map(task -> new LeaveRequest(task.getId(), task.getName()))
                .collect(Collectors.toList());
    }
}
