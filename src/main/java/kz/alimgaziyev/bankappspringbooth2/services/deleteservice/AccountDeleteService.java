package kz.alimgaziyev.bankappspringbooth2.services.deleteservice;

import org.springframework.stereotype.Service;

@Service
public interface AccountDeleteService {
    void delete(String accountId);
}
