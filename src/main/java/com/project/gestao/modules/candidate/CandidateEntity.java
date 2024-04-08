package com.project.gestao.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//ao passar o Data, do lombok os getters e setters são criados por "baixo dos panos" com essa dependency. control p dependency 
@Data
public class CandidateEntity {
  private UUID id;
  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo não pode conter espaços")
  private String username;

  @Email(message = "O campo deve conter um e-mail válido")
  private String email;

  @Length(min = 10, max = 20, message = "O password deve conter entre 10 e 20 caracteres")
  private String password;
  private String description;
  private String curriculum;
}
