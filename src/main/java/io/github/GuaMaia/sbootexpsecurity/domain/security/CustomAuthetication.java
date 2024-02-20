package io.github.GuaMaia.sbootexpsecurity.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomAuthetication implements Authentication {

    private final IdentificacaoUsuario identificacaoUsuario;

    public CustomAuthetication(IdentificacaoUsuario identificacaoUsuario) {
        if (identificacaoUsuario == null) {
            throw new ExceptionInInitializerError("Não possível criar um customauthetication sem a identificacao do usuario");
        }
        this.identificacaoUsuario = identificacaoUsuario;
    }

    /*
    GrantedAuthority
      É uma interface no Spring Security que representa uma autoridade concedida a um usuário
     autenticado.
      No contexto do Spring Security, uma autoridade é frequentemente associada a
       uma função (role) ou permissão que o usuário possui dentro do sistema.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identificacaoUsuario
                .getPermissoes()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.identificacaoUsuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Nao precisar chamar, já estamos autenticados");
    }

    @Override
    public String getName() {
        return this.identificacaoUsuario.getNome();
    }
}
