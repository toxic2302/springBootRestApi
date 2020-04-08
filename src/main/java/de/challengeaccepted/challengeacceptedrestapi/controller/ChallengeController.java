package de.challengeaccepted.challengeacceptedrestapi.controller;

import de.challengeaccepted.challengeacceptedrestapi.controller.modelassemblers.ChallengeModelAssembler;
import de.challengeaccepted.challengeacceptedrestapi.exceptions.ChallengeNotFoundException;
import de.challengeaccepted.challengeacceptedrestapi.models.Challenge;
import de.challengeaccepted.challengeacceptedrestapi.repositories.ChallengeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ChallengeController {
    private final ChallengeRepository repository;
    private final ChallengeModelAssembler assembler;

    public ChallengeController(final ChallengeRepository challengeRepository,
                               final ChallengeModelAssembler assembler) {
        this.repository = challengeRepository;
        this.assembler = assembler;
    }

    @GetMapping("/challenges")
    public CollectionModel<EntityModel<Challenge>> all() {
        final List<EntityModel<Challenge>> challenges =
            repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(challenges, linkTo(methodOn(ChallengeController.class).all()).withSelfRel());
    }

    @GetMapping("/challenges/{id}")
    public EntityModel<Challenge> one(@PathVariable final Long id) {
        final Challenge challenge = repository.findById(id).orElseThrow(() -> new ChallengeNotFoundException(id));

        return assembler.toModel(challenge);
    }

    @PostMapping("/challenges")
    ResponseEntity<?> newChallenge(@RequestBody final Challenge newChallenge) throws URISyntaxException {
        final EntityModel<Challenge> entityModel = assembler.toModel(repository.save(newChallenge));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping("/challenges/{id}")
    ResponseEntity<?> update(@RequestBody final Challenge newChallenge, @PathVariable final Long id) throws URISyntaxException {
        final Challenge updatedChallenge = repository.findById(id)
            .map(challenge -> {
                challenge.setTitle(newChallenge.getTitle());
                challenge.setDescription(newChallenge.getDescription());
                challenge.setPublic(newChallenge.isPublic());
                return repository.save(challenge);
            })
            .orElseGet(() -> {
                newChallenge.setId(id);
                return repository.save(newChallenge);
            });

        final EntityModel<Challenge> entityModel = assembler.toModel(updatedChallenge);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
