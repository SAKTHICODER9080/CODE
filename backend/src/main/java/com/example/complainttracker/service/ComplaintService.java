package com.example.complainttracker.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.complainttracker.model.Complaint;
import com.example.complainttracker.repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository repository;

    public ComplaintService(ComplaintRepository repository) {
        this.repository = repository;
    }

    public Complaint registerComplaint(Complaint complaint) {

        // 🔹 Generate complaint number (OTP-style)
        String complaintNumber = "CMP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        complaint.setComplaintNumber(complaintNumber);
        complaint.setStatus("Pending");
        complaint.setLastUpdated(LocalDate.now());

        return repository.save(complaint);
    }

    public Complaint getByComplaintNumber(String complaintNumber) {
        return repository.findByComplaintNumber(complaintNumber)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }
}
