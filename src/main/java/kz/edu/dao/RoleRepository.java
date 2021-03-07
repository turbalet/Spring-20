package kz.edu.dao;

import kz.edu.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);
}
