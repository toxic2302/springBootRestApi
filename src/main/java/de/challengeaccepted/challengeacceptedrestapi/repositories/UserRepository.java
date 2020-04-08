package de.challengeaccepted.challengeacceptedrestapi.repositories;

import de.challengeaccepted.challengeacceptedrestapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(final String username);
    User findByEmail(final String email);
}
