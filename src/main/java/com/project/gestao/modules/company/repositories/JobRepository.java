package com.project.gestao.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.gestao.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

}
