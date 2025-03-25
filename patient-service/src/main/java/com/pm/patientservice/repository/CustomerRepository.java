package com.pm.patientservice.repository;

import com.pm.patientservice.model.Customer;
import com.pm.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
}
