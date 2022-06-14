package kz.alimgaziyev.bankappspringbooth2.services.registerservice;

import kz.alimgaziyev.bankappspringbooth2.request.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserRegisterService {
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
