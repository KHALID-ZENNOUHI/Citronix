package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.citronix.citronix.domain.AppUser;
import org.citronix.citronix.service.impl.UserService;
import org.citronix.citronix.web.vm.LoginVM;
import org.citronix.citronix.web.vm.RegisterVM;
import org.citronix.citronix.web.vm.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<RegisterVM> register(@Valid @RequestBody RegisterVM registerVM) {
        AppUser appUser = userMapper.toUser(registerVM);
        AppUser appUser1 = userService.register(appUser);
        return ResponseEntity.status(201).body(userMapper.toUserVM(appUser1));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginVM loginVM) {
        AppUser appUser = userMapper.toUser(loginVM);
        String token = userService.verify(appUser);
        return ResponseEntity.ok(token);
    }
}
