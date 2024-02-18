package com.companyMicroServices.companyMS.companies.companyRepo;

import com.companyMicroServices.companyMS.companies.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {}
