package com.example.medcardservice.mapper;

import com.example.medcardservice.dto.DiseaseDTO;
import com.example.medcardservice.model.Disease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DiseaseMapper {
    DiseaseMapper INSTANCE = Mappers.getMapper(DiseaseMapper.class);

    Disease toEntity(DiseaseDTO diseaseDTO);

    // Преобразование списка
    @Mapping(target = "patient", ignore = true)  // игнорируем поле patient
    List<DiseaseDTO> toDTO(List<Disease> diseases);

    DiseaseDTO toDTO(Disease disease);

    @Mapping(target = "patient", ignore = true)  // игнорируем поле patient
    List<Disease> toEntity(List<DiseaseDTO> diseases);

    // Метод для обновления существующего объекта
    void updateDiseaseFromDto(DiseaseDTO diseaseDTO, @MappingTarget Disease patient);
}