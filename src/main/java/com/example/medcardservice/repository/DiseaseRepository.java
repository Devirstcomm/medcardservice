package com.example.medcardservice.repository;

import com.example.medcardservice.model.Disease;
import com.example.medcardservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, String> {

    @Query("SELECT d FROM Disease d " +
            "JOIN FETCH d.mkb10Entry " +
            "JOIN FETCH d.patient " +
            "WHERE d.patient.id = :patientId")
    List<Disease> findDiseasesByPatientId(@Param("patientId") int patientId);

    @Modifying
    @Query("UPDATE Disease d SET d.patient = :patient WHERE d IN :diseases")
    void updateDiseasesByPatient(@Param("patient") Patient patient, @Param("diseases") List<Disease> diseases);


    @Modifying
    @Query("DELETE FROM Disease d WHERE d.patient.id = :patientId")
    void deleteDiseasesByPatientId(@Param("patientId") int patientId);
}
