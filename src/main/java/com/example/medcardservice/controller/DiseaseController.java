package com.example.medcardservice.controller;

import com.example.medcardservice.dto.DiseaseDTO;
import com.example.medcardservice.service.DiseaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient/{patientId}/disease")
public class DiseaseController {
    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public ResponseEntity <List<DiseaseDTO>> getDiseases(@PathVariable int patientId) {
        List<DiseaseDTO> mkb10Entries = diseaseService.getAllDiseasesByPatientId(patientId);
        return ResponseEntity.ok(mkb10Entries);
    }

    @PostMapping
    public ResponseEntity<DiseaseDTO> addDisease(@Valid @PathVariable int patientId, @RequestBody DiseaseDTO diseaseDTO) {
        return ResponseEntity.ok(diseaseService.addDisease(patientId, diseaseDTO));
    }

    @PutMapping
    public ResponseEntity<Void> updateDiseases(@Valid @PathVariable int patientId, @RequestBody List<DiseaseDTO> diseaseDTOS) {
        diseaseService.updateDiseases(patientId, diseaseDTOS);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDiseases(@PathVariable int patientId) {
        diseaseService.deleteDiseases(patientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
