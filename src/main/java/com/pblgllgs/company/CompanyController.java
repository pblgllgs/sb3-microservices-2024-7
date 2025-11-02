package com.pblgllgs.company;
/*
 *
 * @author pblgl
 * Created on 23-10-2024
 *
 */


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable long id) {
        Optional<Company> companyOptional = companyService.findCompanyById(id);
        if (companyOptional.isPresent()) {
            return new ResponseEntity<>(companyOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompany(id, company);
        if (updated) {
            return new ResponseEntity<>("company successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("company cant be updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted) {
            return new ResponseEntity<>("company successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("company cant be deleted", HttpStatus.NOT_FOUND);
    }
}
