package de.challengeaccepted.challengeacceptedrestapi.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
public class Role {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {}

    public Role(final String name, final Set<User> users) {
        this.name = name;
        this.users = users;
    }
}
