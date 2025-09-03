package com.stefan.patientservice.controller;

import com.stefan.patientservice.dto.PatientRequestDTO;
import com.stefan.patientservice.dto.PatientResponseDTO;
import com.stefan.patientservice.dto.validators.CreatePatientValidationGroup;
import com.stefan.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody
            PatientRequestDTO patientDTO
    ) {
        return ResponseEntity.ok().body(patientService.createPatient(patientDTO));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update Patient by it's id")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable UUID id,
            @Validated({Default.class})
            @RequestBody
            PatientRequestDTO patientDTO
    ) {
        return ResponseEntity.ok().body(patientService.updatePatient(id, patientDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Patient by it'd id")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
