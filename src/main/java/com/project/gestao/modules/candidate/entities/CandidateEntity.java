package com.project.gestao.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

//ao passar o Data, do lombok os getters e setters são criados por "baixo dos panos" com essa dependency. control p dependency 
@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Length(min = 3, max = 190, message = "O campo nome deve conter mais de 3 caracteres")
  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo não pode conter espaços")
  private String username;

  @Email(message = "O campo deve conter um e-mail válido")
  private String email;

  @Length(min = 10, max = 20, message = "O password deve conter entre 10 e 20 caracteres")
  private String password;

  @Length(min = 10, max = 120, message = "O campo description deve conter entre 10 e 120 caracteres")
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
