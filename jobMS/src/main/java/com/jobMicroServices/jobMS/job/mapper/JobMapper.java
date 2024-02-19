package com.jobMicroServices.jobMS.job.mapper;

import com.jobMicroServices.jobMS.job.DTO.JobWithCompanyDTO;
import com.jobMicroServices.jobMS.job.Job;
import com.jobMicroServices.jobMS.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO mapJobWithCompanyDTO(Job job, Company company){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        return jobWithCompanyDTO;
    }
}
