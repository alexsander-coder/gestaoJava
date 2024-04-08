package com.project.gestao.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gestao.exceptions.UserFoundException;
import com.project.gestao.modules.candidate.CandidateEntity;
import com.project.gestao.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    return this.candidateRepository.save(candidateEntity);
  }

}