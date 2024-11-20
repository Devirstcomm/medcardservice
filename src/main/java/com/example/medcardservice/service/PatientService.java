package com.example.medcardservice.service;

import com.example.medcardservice.dto.PatientDTO;
import com.example.medcardservice.exception.PatientDuplicateException;
import com.example.medcardservice.exception.PatientNotFoundException;
import com.example.medcardservice.mapper.PatientMapper;
import com.example.medcardservice.model.Patient;
import com.example.medcardservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public PatientDTO getPatientById(int id) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
        return PatientMapper.INSTANCE.toDTO(patient);
    }

    @Transactional
    public void createPatient(PatientDTO patientDTO) {
        Patient patient = PatientMapper.INSTANCE.toEntity(patientDTO); // Используем маппер
        try {
            patientRepository.save(patient);
        } catch (DataIntegrityViolationException e) {
            throw new PatientDuplicateException();
        }
    }

    @Transactional
    public PatientDTO updatePatient(int id, PatientDTO patientDTO) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(PatientNotFoundException::new);
        // Обновляем свойства
        patientMapper.updatePatientFromDto(patientDTO, existingPatient);

        try {
            // Сохраняем пациента с обновленными полями
            patientRepository.save(existingPatient);
            patientRepository.flush(); // Принудительная отправка изменений в базу данных
        } catch (DataIntegrityViolationException e) {
            throw new PatientDuplicateException();
        }
        // Используем маппер и возвращаем назад PatientDTO
        return PatientMapper.INSTANCE.toDTO(existingPatient);
    }

    @Transactional
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
