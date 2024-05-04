package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepo;

    public Set<Assignment> loadAssignmentByUser(User user) {
        return assignmentRepo.findByUser(user);
    }

    public Optional<Assignment> loadAssignmentById(Long id) {
        return assignmentRepo.findById(id);
    }

    public Assignment updateAssignment(Long id, Assignment assignment) {
//        Assignment assignment = assignmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Unable to find Assignment to update"));
//        if (newAssignment.getStatus() != null) {
//            assignment.setStatus(newAssignment.getStatus());
//        }
//        if (newAssignment.getNumber() != null) {
//            assignment.setNumber(newAssignment.getNumber());
//        }
//        if (newAssignment.getGithubUrl() != null) {
//            assignment.setGithubUrl(newAssignment.getGithubUrl());
//        }
//        if (newAssignment.getBranch() != null) {
//            assignment.setBranch(newAssignment.getBranch());
//        }
//        if (newAssignment.getCodeReviewVideoUrl() != null) {
//            assignment.setCodeReviewVideoUrl(newAssignment.getCodeReviewVideoUrl());
//        }
//
//        return assignmentRepo.save(assignment);
        return assignmentRepo.save(assignment);
    }

    public Assignment createAssignment(User user) {
        Assignment assignment = new Assignment();
        assignment.setUser(user);
        assignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION.getStatus());
        return assignmentRepo.save(assignment);
    }

    public void delete(Long id) {
        Assignment assignment = assignmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Unable to find Assignment to delete"));
        assignmentRepo.delete(assignment);
    }
}
