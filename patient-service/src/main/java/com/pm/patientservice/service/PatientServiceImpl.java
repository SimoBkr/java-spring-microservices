package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    private final PatientMapper mapper;

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = repository.findAll();
        return mapper.patientsToPatientsResponseDTO(patients);
    }
}
