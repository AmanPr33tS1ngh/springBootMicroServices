package com.companyMicroServices.companyMS.companies.companyService;

import com.companyMicroServices.companyMS.companies.companyRepo.CompanyRepository;
import com.companyMicroServices.companyMS.companies.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CompanyServiceImp implements CompanyService{
    CompanyRepository companyRepository;
    public CompanyServiceImp(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company updateCompanyById(Company reqCompany, Long id) {
        Optional<Company> optionalCompany = this.companyRepository.findById(id);
        if(optionalCompany.isEmpty())return null;
        Company company = optionalCompany.get();
        company.setDescription(reqCompany.getDescription());
        company.setName(reqCompany.getName());
        this.companyRepository.save(company);
        return company;
    }

    @Override
    public Company getCompanyById(Long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        this.companyRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean createCompany(Company company) {
        this.companyRepository.save(company);
        return true;
    }
}
