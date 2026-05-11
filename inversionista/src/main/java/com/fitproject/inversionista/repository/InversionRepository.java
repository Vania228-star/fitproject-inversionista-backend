package com.fitproject.inversionista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitproject.inversionista.model.Inversion;

@Repository
public interface InversionRepository extends JpaRepository<Inversion, Long> {
    
}
