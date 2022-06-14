package kz.alimgaziyev.bankappspringbooth2.repository;

import kz.alimgaziyev.bankappspringbooth2.models.ERole;
import kz.alimgaziyev.bankappspringbooth2.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
}
