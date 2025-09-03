package com.stefan.patientservice.mapper;

import com.stefan.patientservice.dto.PatientRequestDTO;
import com.stefan.patientservice.dto.PatientResponseDTO;
import com.stefan.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient patient) {
        PatientResponseDTO patientDto = new PatientResponseDTO();
        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setAddress(patient.getAddress());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDto;
    }

    public static Patient toModel(PatientRequestDTO patientDTO) {
        return new Patient(
                patientDTO.getName(),
                patientDTO.getEmail(),
                patientDTO.getAddress(),
                LocalDate.parse(patientDTO.getDateOfBirth()),
                LocalDate.parse(patientDTO.getRegisteredDate())
        );
    }
}
