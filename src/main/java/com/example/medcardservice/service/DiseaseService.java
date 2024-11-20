package com.example.medcardservice.service;

import com.example.medcardservice.dto.DiseaseDTO;
import com.example.medcardservice.exception.*;
import com.example.medcardservice.mapper.DiseaseMapper;
import com.example.medcardservice.model.Disease;
import com.example.medcardservice.model.Mkb10Entry;
import com.example.medcardservice.model.Patient;
import com.example.medcardservice.repository.DiseaseRepository;
import com.example.medcardservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;
    private final Mkb10Service mkb10Service;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository,
                          PatientRepository patientRepository,
                          Mkb10Service mkb10Service) {
        this.diseaseRepository = diseaseRepository;
        this.patientRepository = patientRepository;
        this.mkb10Service = mkb10Service;
    }

    public List<DiseaseDTO> getAllDiseasesByPatientId(int patientId) throws DiseaseNotFoundException {
        List<Disease> diseases = diseaseRepository.findDiseasesByPatientId(patientId);

        if (diseases.isEmpty()) {
            throw new DiseaseNotFoundException();
        }

        return DiseaseMapper.INSTANCE.toDTO(diseases);
    }

    @Transactional
    public DiseaseDTO addDisease(int patientID, DiseaseDTO diseaseDTO) {
        Patient patient = patientRepository.findById(patientID).orElseThrow(PatientNotFoundException::new);

        // Достаем код mkb10Entry из переданной сущности
        Mkb10Entry isMkb10EntryExist = diseaseDTO.getMkb10Entry();

        // Проверяем, существует ли сущность в кеше
        if (!mkb10Service.getAllMkb10Entries().contains(isMkb10EntryExist)) {
            throw new Mkb10EntryNotFoundException("Код МКБ-10 не найден");
        }

        // Создаем заболевание и связываем его с пациентом
        Disease disease = DiseaseMapper.INSTANCE.toEntity(diseaseDTO);
        disease.setPatient(patient);

        // Сохраняем заболевание в репозитории
        diseaseRepository.save(disease);

        return DiseaseMapper.INSTANCE.toDTO(disease);
    }


    @Transactional
    public void updateDiseases(int patientID, List<DiseaseDTO> diseaseDTOS) {
        Patient patient = patientRepository.findById(patientID).orElseThrow(PatientNotFoundException::new);

        //Получаем все заболевания пациента
        List<Disease> diseasesOfPatient = patient.getDiseases();

        //Проверяем существуют ли переданные коды в кеше
        Set<Mkb10Entry> mkb10EntrySet = mkb10Service.getAllMkb10Entries();
        diseaseDTOS.forEach(diseaseDTO -> {
            if (!mkb10EntrySet.contains(diseaseDTO.getMkb10Entry())) {
                throw new Mkb10EntryNotFoundException("Код МКБ-10 не найден");
            }
        });

        // Преобразуем DTO в сущности
        List<Disease> updatedDiseases = DiseaseMapper.INSTANCE.toEntity(diseaseDTOS);

        // Устанавливаем пациента для новых заболеваний
        updatedDiseases.forEach(disease -> disease.setPatient(patient));

        // Удаляем старые заболевания и сохраняем новые
        diseaseRepository.deleteDiseasesByPatientId(patientID);

        // Сохраняем обновленные заболевания
        diseaseRepository.saveAll(updatedDiseases);
    }

    @Transactional
    public void deleteDiseases(int patientID) {
        diseaseRepository.deleteDiseasesByPatientId(patientID);
    }

}

