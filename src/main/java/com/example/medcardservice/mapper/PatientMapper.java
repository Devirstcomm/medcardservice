package com.example.medcardservice.mapper;

import com.example.medcardservice.dto.PatientDTO;
import com.example.medcardservice.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    Patient toEntity(PatientDTO patientDTO);

    PatientDTO toDTO(Patient patient);

    // Метод для обновления существующего объекта
    void updatePatientFromDto(PatientDTO patientDTO, @MappingTarget Patient patient);
}

