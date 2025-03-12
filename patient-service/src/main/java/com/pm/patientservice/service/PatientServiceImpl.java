package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    private final PatientMapper mapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        return Optional.of(patientRequestDTO)
                .map(mapper::patientRequestDTOToPatient) // Convert DTO to Entity
                .map(repository::save) // Save to repository
                .map(Patient::getId) // Extract saved patient ID
                .flatMap(repository::findById) // Retrieve saved patient
                .map(mapper::patientToPatientResponseDTO) // Convert back to ResponseDTO
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = repository.findAll();
        return mapper.patientsToPatientsResponseDTO(patients);
    }
}
