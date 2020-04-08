package de.challengeaccepted.challengeacceptedrestapi.repositories;

import de.challengeaccepted.challengeacceptedrestapi.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Challenge findByTitle(final String title);
}
