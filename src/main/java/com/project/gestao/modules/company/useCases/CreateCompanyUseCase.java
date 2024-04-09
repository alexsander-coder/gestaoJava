package com.project.gestao.modules.company.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gestao.exceptions.UserFoundException;
import com.project.gestao.modules.company.entities.CompanyEntity;
import com.project.gestao.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    Optional<CompanyEntity> existingCompany = companyRepository.findByUsernameOrEmail(companyEntity.getUsername(),

        companyEntity.getEmail());

    if (existingCompany.isPresent()) {
      String errorMessage = "";

      if (existingCompany.get().getUsername().equals(companyEntity.getUsername())) {
        errorMessage += "Username, ";
      }
      if (existingCompany.get().getEmail().equals(companyEntity.getEmail())) {
        errorMessage += "Email.";
      }

      throw new UserFoundException(errorMessage);
    }

    return companyRepository.save(companyEntity);
  }
}