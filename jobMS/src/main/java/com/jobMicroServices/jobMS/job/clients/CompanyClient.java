package com.jobMicroServices.jobMS.job.clients;

import com.jobMicroServices.jobMS.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COMPANY-SERVICE")
public interface CompanyClient {
    @GetMapping("/company/{id}")
    Company getCompany(@PathVariable("id") Long id);
}
