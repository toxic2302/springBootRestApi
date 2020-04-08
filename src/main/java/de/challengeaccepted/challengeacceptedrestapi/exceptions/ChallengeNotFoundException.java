package de.challengeaccepted.challengeacceptedrestapi.exceptions;

public class ChallengeNotFoundException extends RuntimeException {
    public ChallengeNotFoundException(final Long id) {
        super("Could not find challenge with id: <" + id + ">");
    }
}
