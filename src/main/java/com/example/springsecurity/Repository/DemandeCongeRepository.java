package com.example.springsecurity.Repository;

import com.example.springsecurity.Models.DemandeConge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Long> {
}

