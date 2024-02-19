package com.jobMicroServices.jobMS.job.JobService;

import com.jobMicroServices.jobMS.job.DTO.JobWithCompanyDTO;
import com.jobMicroServices.jobMS.job.Job;
import com.jobMicroServices.jobMS.job.JobRepo.JobRepository;
import com.jobMicroServices.jobMS.job.external.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImp implements JobService{
    JobRepository jobRepository;
    public JobServiceImp(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    @Override
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        List<Job> jobs = this.jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        for(Job job: jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.getForObject("http://localhost:8083/company/" + job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTOS.add(jobWithCompanyDTO);
        }

        return new ResponseEntity<>(jobWithCompanyDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createJob(Job job) {
        this.jobRepository.save(job);
        return new ResponseEntity<>("Job Added!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Job> getJobById(Long id) {
        return new ResponseEntity<>(this.jobRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> DeleteJobById(Long id) {
        try{
            this.jobRepository.deleteById(id);
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Err: " + e, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> updateJobById(Long id, Job reqJob) {
        Optional<Job> jobOptional = this.jobRepository.findById(id);
        if(jobOptional.isEmpty()) return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);

        Job job = jobOptional.get();
        job.setTitle(reqJob.getTitle());
        job.setDescription(reqJob.getDescription());
        job.setMaxSalary(reqJob.getMaxSalary());
        job.setMinSalary(reqJob.getMinSalary());
        job.setLocation(reqJob.getLocation());
        this.jobRepository.save(job);
        return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
    }
}
