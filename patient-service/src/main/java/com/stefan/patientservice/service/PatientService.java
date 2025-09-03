package com.stefan.patientservice.service;

import com.stefan.patientservice.dto.PatientRequestDTO;
import com.stefan.patientservice.dto.PatientResponseDTO;
import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patientDTO);
    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientDTO);
    void deletePatient(UUID id);
}
