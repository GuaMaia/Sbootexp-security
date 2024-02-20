package io.github.GuaMaia.sbootexpsecurity.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome")
    private String nome;
}
