package de.challengeaccepted.challengeacceptedrestapi.exceptions.config;

import de.challengeaccepted.challengeacceptedrestapi.exceptions.ChallengeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ChallengeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ChallengeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String challengeNotFoundHandler(final ChallengeNotFoundException ex) {
        return ex.getMessage();
    }
}
