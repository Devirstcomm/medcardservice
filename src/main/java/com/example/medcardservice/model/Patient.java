package com.example.medcardservice.model;

import com.example.medcardservice.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно иметь размер от 2 до 30 символов")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "фамилия не должна быть пустой")
    @Size(min = 2, max = 70, message = "Фамилия должна иметь размер от 2 до 70 символов")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @NotNull(message = "дата рождения не должна быть пустой")
    @Past(message = "Дата рождения не должна быть в будущем")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull(message = "пол не должен быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Pattern(regexp = "\\d{16}", message = "номер полиса ОМС должен содержать 16 цифр")
    @Column(name = "policy_number", unique = true, nullable = false)
    private String policyNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Disease> diseases;

    public Patient() {}

    // Конструктор с обязательными полями
    public Patient(String lastName, String firstName, Gender gender, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    // Полный конструктор
    public Patient(String lastName, String firstName, String patronymic, Gender gender, LocalDate birthDate, String policyNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.birthDate = birthDate;
        this.policyNumber = policyNumber;
    }

    // Геттеры и сеттеры

    public void addDisease(Disease disease) {
        diseases.add(disease);
    }

    public int getId() {
        return id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
