package de.challengeaccepted.challengeacceptedrestapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "challenge")
public class Challenge {
    //TODO: check if ids are generated automatically
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String title;
    private String description;
    private boolean isPublic;
    @ManyToOne
    private User user;

    public Challenge() {}

    public Challenge(final String title, final String description, final boolean isPublic) {
        this.title = title;
        this.description = description;
        this.isPublic = isPublic;
    }
}
