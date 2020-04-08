package de.challengeaccepted.challengeacceptedrestapi.repositories;

import de.challengeaccepted.challengeacceptedrestapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
