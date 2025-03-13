package com.pm.patientservice.mapper;

import com.pm.patientservice.config.MapStructConfig;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface PatientMapper {

    PatientResponseDTO patientToPatientResponseDTO(Patient patient);

    Patient patientRequestDTOToPatient(PatientRequestDTO patientRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatientFromDto(PatientRequestDTO dto, @MappingTarget Patient patient);

    List<PatientResponseDTO> patientsToPatientsResponseDTO(List<Patient> patient);
}
