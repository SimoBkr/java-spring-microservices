package com.pm.patientservice.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CustomerController {

    private JobLauncher jobLauncher;
    private Job job;

    @GetMapping("/import")
    public void loadCvsToDb() throws Exception {

        JobParameters jobParams = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(job, jobParams);
    }

}
