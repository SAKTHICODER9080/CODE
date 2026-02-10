package com.example.complainttracker.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.complainttracker.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {

    Optional<Complaint> findByComplaintNumber(String complaintNumber);
}
