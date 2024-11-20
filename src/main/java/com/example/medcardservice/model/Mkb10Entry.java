package com.example.medcardservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mkb10_dictionary")
public class Mkb10Entry {

    @Id
    @Pattern(regexp = "^[A-Z]\\d{2}(\\.\\d{1,2})?$", message = "Некорректный код заболевания МКБ-10")
    @Column(name = "code", nullable = false, unique = true)
    private String code; // Код болезни

    @NotNull(message = "Название болезни не должно быть пустым")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "mkb10Entry")
    private List<Disease> diseases;

    public Mkb10Entry() {
    }

    public Mkb10Entry(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mkb10Entry that = (Mkb10Entry) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
