package com.example.medcardservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "disease")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "mkb10_dictionary", nullable = false, referencedColumnName = "code")
    private Mkb10Entry mkb10Entry;

    @NotNull(message = "Дата начала болезни не должна быть пустой")
    @PastOrPresent(message = "Дата начала болезни не должна быть в будущем")
    @Column(name = "start_date")
    private LocalDate startDate;

    @PastOrPresent(message = "Дата окончания болезни не должна быть в будущем")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull(message = "Назначения не должны быть пустыми")
    @Size(max = 1024, message = "Максимальная длина назначений 1024 символа")
    @Column(name = "prescriptions")
    private String prescriptions;

    public Disease() {
    }

    //Конструктор с обязательными полями
    public Disease(Patient patient, Mkb10Entry mkb10Entry, LocalDate startDate, String prescriptions) {
        this.patient = patient;
        this.mkb10Entry = mkb10Entry;
        this.startDate = startDate;
        this.prescriptions = prescriptions;
    }

    //Конструктор полными полями
    public Disease(Patient patient, Mkb10Entry mkb10Entry, LocalDate startDate, LocalDate endDate, String prescriptions) {
        this.patient = patient;
        this.mkb10Entry = mkb10Entry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prescriptions = prescriptions;
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Mkb10Entry getMkb10Entry() {
        return mkb10Entry;
    }

    public void setMkb10Entry(Mkb10Entry mkb10Entry) {
        this.mkb10Entry = mkb10Entry;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

}