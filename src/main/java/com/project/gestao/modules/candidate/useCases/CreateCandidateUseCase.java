package com.project.gestao.modules.candidate.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gestao.exceptions.UserFoundException;
import com.project.gestao.modules.candidate.entities.CandidateEntity;
import com.project.gestao.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    // Verificar se já existe um candidato com o mesmo username ou email
    Optional<CandidateEntity> existingCandidate = candidateRepository
        .findByUsernameOrEmail(
            candidateEntity.getUsername(), candidateEntity.getEmail());

    // Se já existe um candidato com o mesmo username ou email
    if (existingCandidate.isPresent()) {
      String errorMessage = "";
      if (existingCandidate.get().getUsername().equals(candidateEntity.getUsername())) {
        errorMessage += "Username. ";
      }
      if (existingCandidate.get().getEmail().equals(candidateEntity.getEmail())) {
        errorMessage += "Email.";
      }
      throw new UserFoundException(errorMessage);
    }

    return candidateRepository.save(candidateEntity);
  }
}
