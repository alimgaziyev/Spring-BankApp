package kz.alimgaziyev.bankappspringbooth2.services.authservice;

import kz.alimgaziyev.bankappspringbooth2.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserAuthenticateService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
}
