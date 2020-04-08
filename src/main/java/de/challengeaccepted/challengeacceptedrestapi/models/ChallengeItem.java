package de.challengeaccepted.challengeacceptedrestapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "challenge_item")
public class ChallengeItem {
    private @Id @GeneratedValue Long id;
    private String name;
    @ManyToOne
    private Challenge challenge;

    public ChallengeItem() {}

    public ChallengeItem(final String name) {
        this.name = name;
    }
}
