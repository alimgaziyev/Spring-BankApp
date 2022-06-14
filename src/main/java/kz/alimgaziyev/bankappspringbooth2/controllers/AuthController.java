package kz.alimgaziyev.bankappspringbooth2.controllers;

import kz.alimgaziyev.bankappspringbooth2.models.ERole;
import kz.alimgaziyev.bankappspringbooth2.models.Role;
import kz.alimgaziyev.bankappspringbooth2.models.User;
import kz.alimgaziyev.bankappspringbooth2.repository.RoleRepo;
import kz.alimgaziyev.bankappspringbooth2.repository.UserRepo;
import kz.alimgaziyev.bankappspringbooth2.request.JwtResponse;
import kz.alimgaziyev.bankappspringbooth2.request.LoginRequest;
import kz.alimgaziyev.bankappspringbooth2.request.SignUpRequest;
import kz.alimgaziyev.bankappspringbooth2.requestoutput.Messages;
import kz.alimgaziyev.bankappspringbooth2.security.jwt.JwtUtils;
import kz.alimgaziyev.bankappspringbooth2.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RoleRepo roleRepo;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(   jwt,
                                                    userDetails.getId(),
                                                    userDetails.getEmail(),
                                                    roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new Messages(String.format("This email(%s) registered", signUpRequest.getEmail())));
        }
        Set<Role> roles = new HashSet<>();
        if (signUpRequest.getRoles() == null) {
            roles.add(new Role(ERole.ROLE_USER));
        } else {
            signUpRequest
                    .getRoles()
                    .forEach(role ->{
                                    switch (role) {
                                        case "admin":
                                            roles.add(roleRepo.findRoleByName(ERole.ROLE_ADMIN.name()));
                                            break;
                                        case "moderator":
                                            roles.add(roleRepo.findRoleByName(ERole.ROLE_MODERATOR.name()));
                                            break;
                                        default:
                                            roles.add(roleRepo.findRoleByName(ERole.ROLE_USER.name()));
                                    }});
        }
        userRepo.save(User.builder()
                .email(signUpRequest.getEmail())
                .encodedPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(roles)
                .build()
                );
        Messages messages = new Messages("Successfully registered");
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }
}
