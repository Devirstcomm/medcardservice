package com.example.medcardservice.dto;

import com.example.medcardservice.model.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class PatientDTO {

    @Schema(description = "Имя пациента", example = "Иван")
    private String firstName;
    @Schema(description = "Фамилия пациента", example = "Иванов")
    private String lastName;
    @Schema(description = "Отчество пациента", example = "Иванович")
    private String patronymic;
    @Schema(description = "Пол пациента", example = "мужчина")
    private Gender gender;
    @Schema(description = "Дата рождения пациента", example = "2000-01-01")
    private LocalDate birthDate;
    @Schema(description = "Шестнадцатизначный номер полиса пациента", example = "1234567890123456")
    private String policyNumber;

    public PatientDTO(String firstName, String lastName, String patronymic, Gender gender, LocalDate birthDate, String policyNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDate = birthDate;
        this.policyNumber = policyNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
}
