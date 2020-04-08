package de.challengeaccepted.challengeacceptedrestapi.controller;

import de.challengeaccepted.challengeacceptedrestapi.controller.modelassemblers.UserModelAssembler;
import de.challengeaccepted.challengeacceptedrestapi.exceptions.UserNotFoundException;
import de.challengeaccepted.challengeacceptedrestapi.models.User;
import de.challengeaccepted.challengeacceptedrestapi.repositories.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    public UserController(final UserRepository repository, final UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        final List<EntityModel<User>> users =
            repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable final Long id) {
        final User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }
}
