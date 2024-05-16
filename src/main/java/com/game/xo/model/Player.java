package com.game.xo.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    private String Id;
    private String name;
    private String email;

    public Player(String name, String email) {
        this.Id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }
}