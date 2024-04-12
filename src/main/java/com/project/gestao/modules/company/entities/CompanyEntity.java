package com.project.gestao.modules.company.entities;

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

@Entity(name = "company")
@Data
public class CompanyEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo não pode conter espaços")
  private String username;

  @Email(message = "O campo deve conter um e-mail válido")
  private String email;

  @Length(min = 10, max = 255, message = "O password deve conter entre 10 e 100 caracteres")
  private String password;

  private String cnpj;
  private String name;
  private String website;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
