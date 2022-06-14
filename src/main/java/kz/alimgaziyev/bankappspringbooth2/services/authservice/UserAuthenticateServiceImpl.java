package kz.alimgaziyev.bankappspringbooth2.services.authservice;

import kz.alimgaziyev.bankappspringbooth2.request.JwtResponse;
import kz.alimgaziyev.bankappspringbooth2.request.LoginRequest;
import kz.alimgaziyev.bankappspringbooth2.security.jwt.JwtUtils;
import kz.alimgaziyev.bankappspringbooth2.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAuthenticateServiceImpl implements UserAuthenticateService{
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( loginRequest.getUsername(),
                                                                                            loginRequest.getPassword()));
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
}
