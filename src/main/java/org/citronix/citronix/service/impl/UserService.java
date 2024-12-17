package org.citronix.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.citronix.citronix.domain.AppUser;
import org.citronix.citronix.repository.AppUserRepository;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AppUser register(AppUser appUser) {
        if (appUser.getId() != null) {
            throw new IdMustBeNullException();
        }
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    public String verify(AppUser appUser) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(appUser.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}
