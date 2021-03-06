package com.urjc.es.helseVITA.Security;

import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    Optional<Patient> optionalPatient = patientRepository.findByUsername(auth.getName());
    Patient patient = optionalPatient.orElse(null);
    String password = (String) auth.getCredentials();

    if (patient == null || !new BCryptPasswordEncoder().matches(password, patient.getPasswordHash())) {
        throw new BadCredentialsException("Wrong credentials");
    }

     return null;

}

    @Override
    public boolean supports(Class<?> authentication) {
        // Auto-generated method stub
        return false;
    }
}
