package com.stefan.patientservice.service.impl;

import com.stefan.patientservice.dto.PatientRequestDTO;
import com.stefan.patientservice.dto.PatientResponseDTO;
import com.stefan.patientservice.exception.EmailAlreadyExistsException;
import com.stefan.patientservice.exception.InvalidPatientIdException;
import com.stefan.patientservice.mapper.PatientMapper;
import com.stefan.patientservice.model.Patient;
import com.stefan.patientservice.repository.PatientRepository;
import com.stefan.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientDTO) {
        if(patientRepository.existsByEmail(patientDTO.getEmail())) {
            throw new EmailAlreadyExistsException(patientDTO.getEmail());
        }

        Patient patient = patientRepository.save(PatientMapper.toModel(patientDTO));
        return PatientMapper.toDto(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new InvalidPatientIdException(id.toString()));

        if(patientRepository.existsByEmailAndIdNot(patientDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException(patientDTO.getEmail());
        }

        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
