package com.project.gestao.modules.company.useCases;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.gestao.modules.company.dto.AuthCompanyDTO;
import com.project.gestao.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${SECURITY.TOKEN.SECRET}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
        () -> {
          throw new UsernameNotFoundException("username/password incorretos");
        });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();

    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create()
        .withIssuer("javagas")
        .withExpiresAt(Instant.now().plus(5, ChronoUnit.HOURS))
        .withSubject(company.getId().toString())
        .sign(algorithm);

    return token;

  }
}
