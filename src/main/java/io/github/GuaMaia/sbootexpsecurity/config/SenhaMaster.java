package io.github.GuaMaia.sbootexpsecurity.config;

import io.github.GuaMaia.sbootexpsecurity.domain.security.CustomAuthetication;
import io.github.GuaMaia.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SenhaMaster implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login = authentication.getName();
        var senha = (String) authentication.getCredentials();

        String loginMaster = "master";
        String senhaMaster = "@147";

        if(loginMaster.equals(login) && senhaMaster.equals(senha)){
            IdentificacaoUsuario  identificacaoUsuario = new IdentificacaoUsuario(
                    "Sou Master",
                    "Master",
                    loginMaster,
                    List.of("ADMIN"));

            return new CustomAuthetication(identificacaoUsuario);
        }
        return null;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
