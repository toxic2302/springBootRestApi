package de.challengeaccepted.challengeacceptedrestapi.controller.modelassemblers;

import de.challengeaccepted.challengeacceptedrestapi.controller.ChallengeController;
import de.challengeaccepted.challengeacceptedrestapi.models.Challenge;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ChallengeModelAssembler implements RepresentationModelAssembler<Challenge, EntityModel<Challenge>> {
    @Override
    public EntityModel<Challenge> toModel(final Challenge challenge) {
        return new EntityModel<>(challenge,
            //todo: linking items in relation
            linkTo(methodOn(ChallengeController.class).one(challenge.getId())).withSelfRel(),
            linkTo(methodOn(ChallengeController.class).all()).withRel("challenges")
        );
    }
}
