package com.jobMicroServices.jobMS.job.JobRepo;

import com.jobMicroServices.jobMS.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {}
