package kz.alimgaziyev.bankappspringbooth2.repository;

import kz.alimgaziyev.bankappspringbooth2.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
