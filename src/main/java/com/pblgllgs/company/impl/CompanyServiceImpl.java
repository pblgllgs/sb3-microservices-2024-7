package com.pblgllgs.company.impl;
/*
 *
 * @author pblgl
 * Created on 23-10-2024
 *
 */

import com.pblgllgs.company.Company;
import com.pblgllgs.company.CompanyRepository;
import com.pblgllgs.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Company companyToUpdate = optionalCompany.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Company> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }
}
