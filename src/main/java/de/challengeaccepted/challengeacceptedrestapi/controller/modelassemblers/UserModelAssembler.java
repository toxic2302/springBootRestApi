package de.challengeaccepted.challengeacceptedrestapi.controller.modelassemblers;

import de.challengeaccepted.challengeacceptedrestapi.controller.UserController;
import de.challengeaccepted.challengeacceptedrestapi.models.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(final User user) {
        return new EntityModel<>(user,
            linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("users")
        );
    }
}
