package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<PatientResponseDTO> createPatient(PatientRequestDTO patientRequestDTO) {

        if (repository.existsByEmail(patientRequestDTO.getEmail())) throw new EmailAlreadyException("A patient with this email already exist " + patientRequestDTO.getEmail());

        return Optional.of(patientRequestDTO)
                .map(mapper::patientRequestDTOToPatient)
                .map(repository::save)
                .map(mapper::patientToPatientResponseDTO);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {

        Patient patient = repository.findById(id).orElseThrow(() -> new PatientNotFoundException("this patient not found with this id {}" + id));

        if(repository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) throw new EmailAlreadyException("A patient with this email" + "already exists"+ patientRequestDTO.getEmail());

        mapper.updatePatientFromDto(patientRequestDTO,patient);
        repository.save(patient);
        return mapper.patientToPatientResponseDTO(patient);
    }

    @Override
    public void deletePatient(UUID id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
