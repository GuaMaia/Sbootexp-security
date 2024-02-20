package io.github.GuaMaia.sbootexpsecurity.api.dto;

import io.github.GuaMaia.sbootexpsecurity.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroControllerDTO {
    private Usuario usuario;
    private List<String> permissoes;
}
