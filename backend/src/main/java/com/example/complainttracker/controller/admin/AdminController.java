package com.example.complainttracker.controller.admin;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.complainttracker.model.Complaint;
import com.example.complainttracker.repository.ComplaintRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final ComplaintRepository repository;

    public AdminController(ComplaintRepository repository) {
        this.repository = repository;
    }

    // 🔹 Get all complaints
    @GetMapping("/complaints")
    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    // 🔹 Update complaint status
    @PutMapping("/complaints/{complaintNumber}/status")
      public Complaint updateStatus(
            @PathVariable String complaintNumber,
            @RequestParam String status) {

        Complaint complaint = repository.findByComplaintNumber(complaintNumber)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);
        return repository.save(complaint);
    }
}
