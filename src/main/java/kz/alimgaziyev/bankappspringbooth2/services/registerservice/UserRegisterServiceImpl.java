package kz.alimgaziyev.bankappspringbooth2.services.registerservice;

import kz.alimgaziyev.bankappspringbooth2.models.ERole;
import kz.alimgaziyev.bankappspringbooth2.models.Role;
import kz.alimgaziyev.bankappspringbooth2.models.User;
import kz.alimgaziyev.bankappspringbooth2.repository.RoleRepo;
import kz.alimgaziyev.bankappspringbooth2.repository.UserRepo;
import kz.alimgaziyev.bankappspringbooth2.request.SignUpRequest;
import kz.alimgaziyev.bankappspringbooth2.requestoutput.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
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
