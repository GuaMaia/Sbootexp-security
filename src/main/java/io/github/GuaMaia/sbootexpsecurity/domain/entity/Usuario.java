package io.github.GuaMaia.sbootexpsecurity.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Transient
    private List<String> permissoes;
}
