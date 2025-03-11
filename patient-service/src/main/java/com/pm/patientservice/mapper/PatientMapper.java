package com.pm.patientservice.mapper;

import com.pm.patientservice.config.MapStructConfig;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface PatientMapper {

    PatientResponseDTO patientToPatientResponseDTO(Patient patient);

    List<PatientResponseDTO> patientsToPatientsResponseDTO(List<Patient> patient);
}
