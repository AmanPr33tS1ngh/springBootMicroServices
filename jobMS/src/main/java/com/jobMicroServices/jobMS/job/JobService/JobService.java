package com.jobMicroServices.jobMS.job.JobService;

import com.jobMicroServices.jobMS.job.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    ResponseEntity<List<Job>> findAll();
    ResponseEntity<String> createJob(Job job);
    ResponseEntity<Job> getJobById(Long id);
    ResponseEntity<String> DeleteJobById(Long id);
    ResponseEntity<String> updateJobById(Long id, Job reqJob);
}
