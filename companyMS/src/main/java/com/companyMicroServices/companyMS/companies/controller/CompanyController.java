package com.companyMicroServices.companyMS.companies.controller;

import com.companyMicroServices.companyMS.companies.companyService.CompanyService;
import com.companyMicroServices.companyMS.companies.model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;
    CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping("/")
    public List<Company> getAllCompanies(){
        return this.companyService.getAllCompanies();
    }
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id){
        return this.companyService.getCompanyById(id);
    }
    @PutMapping("/{id}")
    public Company updateCompanyById(@RequestBody Company company, @PathVariable Long id){
        return this.companyService.updateCompanyById(company, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean isDeleted = this.companyService.deleteCompanyById(id);
        return new ResponseEntity<>(isDeleted ? "Deleted Successfully" : "Deletion Unsuccessful", HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        this.companyService.createCompany(company);
        return new ResponseEntity<>("Created Company", HttpStatus.OK);
    }
}
