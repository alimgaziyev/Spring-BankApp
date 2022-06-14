package kz.alimgaziyev.bankappspringbooth2.services.transferservice;

import kz.alimgaziyev.bankappspringbooth2.dto.TransferDto;
import org.springframework.stereotype.Service;

@Service
public interface TransferService {
    void transferFromOneToAnotherAccount(String clientId, String fromAccountId, String toAccountId, Double amount);
}
