package com.hcc.controllers;

import com.hcc.dtos.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import com.hcc.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @GetMapping
    public ResponseEntity<?> getAssignments(@AuthenticationPrincipal User user) {
        Set<Assignment> assignments = assignmentService.loadAssignmentByUser(user);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAssignment(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<Assignment> assignmentOpt = assignmentService.loadAssignmentById(id);
        return ResponseEntity.ok(new AssignmentResponseDto(assignmentOpt.orElse(new Assignment())));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putAssignment(@PathVariable Long id, @RequestBody Assignment assignment, @AuthenticationPrincipal User user) {
        Assignment newAssignment = assignmentService.updateAssignment(id, assignment);
        return ResponseEntity.ok(newAssignment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id, @AuthenticationPrincipal User user) {
        assignmentService.delete(id);
        return ResponseEntity.ok("Assignment Deleted");
    }

    @PostMapping
    public ResponseEntity<?> postAssignment(@AuthenticationPrincipal User user) {
        Assignment assignment = assignmentService.createAssignment(user);
        return ResponseEntity.ok(assignment);
    }
}
