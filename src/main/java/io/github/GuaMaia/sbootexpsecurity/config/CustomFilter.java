package io.github.GuaMaia.sbootexpsecurity.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {
    /*
      Ela é usada para criar filtros de segurança que são executados
     apenas uma vez por solicitação HTTP.

       A principal função dessa classe é garantir que o filtro seja executado apenas uma vez
      por solicitação, independentemente de como a cadeia de filtros está configurada.
       Isso é útil em cenários em que você deseja garantir que o filtro seja
      aplicado apenas uma vez durante o processamento de uma solicitação HTTP,
      evitando assim múltiplas execuções desnecessárias do filtro.
    */

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String secretHeader = request.getHeader("x-secret");

        if (secretHeader != null){
            if (secretHeader.equals("secr3t")) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        "Muito secreto",null,
                        List.of(new SimpleGrantedAuthority("ADMIN"))
                );

               SecurityContext securityContext = SecurityContextHolder.getContext();
               securityContext.setAuthentication(authentication);
               /*
                SecurityContextHolder
                 Essa classe fornece um mecanismo para armazenar e recuperar o
                contexto de segurança associado à thread atual.
                 O contexto de segurança contém informações sobre o
                 principal (usuário autenticado),
                 suas credenciais (normalmente uma senha ou token),
                 bem como detalhes de autorização
                 (como as funções ou permissões associadas ao principal).
                  É uma estrutura fundamental no Spring Security para controlar
                 o acesso a recursos protegidos em um aplicativo.
                */
            }
        }
        filterChain.doFilter(request,response);
    }
}
