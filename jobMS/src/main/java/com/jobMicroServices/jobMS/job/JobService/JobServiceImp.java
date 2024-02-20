package com.jobMicroServices.jobMS.job.JobService;

import com.jobMicroServices.jobMS.job.DTO.JobWithCompanyDTO;
import com.jobMicroServices.jobMS.job.clients.CompanyClient;
import com.jobMicroServices.jobMS.job.mapper.JobMapper;
import com.jobMicroServices.jobMS.job.Job;
import com.jobMicroServices.jobMS.job.JobRepo.JobRepository;
import com.jobMicroServices.jobMS.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    RestTemplate restTemplate;
    private CompanyClient companyClient;
    public JobServiceImp(JobRepository jobRepository, CompanyClient companyClient){
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
    }

    @Override
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        List<Job> jobs = this.jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();

        for(Job job: jobs){
            jobWithCompanyDTOS.add(getJobWithCompanyDTO(job));
        }

        return new ResponseEntity<>(jobWithCompanyDTOS, HttpStatus.OK);
    }
    public JobWithCompanyDTO getJobWithCompanyDTO(Job job){
        Company company = this.companyClient.getCompany(job.getCompanyId());
        // Company company = this.restTemplate.getForObject("http://COMPANY-SERVICE:8083/company/" + job.getCompanyId(), Company.class);
        JobWithCompanyDTO jobWithCompanyDTO = JobMapper.mapJobWithCompanyDTO(job, company);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }

    @Override
    public ResponseEntity<String> createJob(Job job) {
        this.jobRepository.save(job);
        return new ResponseEntity<>("Job Added!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JobWithCompanyDTO> getJobById(Long id) {
        Job job = this.jobRepository.findById(id).orElse(null);
        if (job == null) return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(this.getJobWithCompanyDTO(job), HttpStatus.OK);
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
