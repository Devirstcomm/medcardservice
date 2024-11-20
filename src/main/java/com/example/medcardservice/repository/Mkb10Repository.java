package com.example.medcardservice.repository;

import com.example.medcardservice.model.Mkb10Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Mkb10Repository extends JpaRepository<Mkb10Entry, String> {
    Optional<Mkb10Entry> findByCode(String code);
}