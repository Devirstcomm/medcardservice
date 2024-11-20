package com.example.medcardservice.dto;

import com.example.medcardservice.model.Mkb10Entry;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class DiseaseDTO {

    @Schema(
            description = "Заболевание",
            example = "{\"code\": \"A00\", \"name\": \"Холера\" }"
    )
    private Mkb10Entry mkb10Entry;

    @Schema(description = "Дата начала болезни", example = "2023-01-01")
    private LocalDate startDate;

    @Schema(description = " Дата окончания болезни", example = "2023-02-01")
    private LocalDate endDate;

    @Schema(description = "Назначения", example = "Обильное питьё и отдых")
    private String prescriptions;

    public DiseaseDTO() {
    }

    //Конструктор с обязательными полями
    public DiseaseDTO(Mkb10Entry mkb10Entry, LocalDate startDate, String prescriptions) {
        this.mkb10Entry = mkb10Entry;
        this.startDate = startDate;
        this.prescriptions = prescriptions;
    }

    //Конструктор полными полями
    public DiseaseDTO(Mkb10Entry mkb10Entry, LocalDate startDate, LocalDate endDate, String prescriptions) {
        this.mkb10Entry = mkb10Entry;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prescriptions = prescriptions;
    }

    // Геттеры и сеттеры

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
