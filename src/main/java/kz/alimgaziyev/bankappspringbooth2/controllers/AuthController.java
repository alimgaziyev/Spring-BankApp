package kz.alimgaziyev.bankappspringbooth2.controllers;

import kz.alimgaziyev.bankappspringbooth2.request.LoginRequest;
import kz.alimgaziyev.bankappspringbooth2.request.SignUpRequest;
import kz.alimgaziyev.bankappspringbooth2.services.authservice.UserAuthenticateService;
import kz.alimgaziyev.bankappspringbooth2.services.registerservice.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final UserAuthenticateService userAuthenticateService;
    private final UserRegisterService userRegisterService;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userAuthenticateService.authenticateUser(loginRequest);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userRegisterService.registerUser(signUpRequest);
    }
}
