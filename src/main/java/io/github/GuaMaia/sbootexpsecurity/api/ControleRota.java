package io.github.GuaMaia.sbootexpsecurity.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControleRota {
    /*
        ResponseEntity no Spring é uma classe que
         representa toda a resposta HTTP: status, cabeçalhos e corpo.
         É usada para construir uma resposta HTTP a ser
         retornada de um controlador em um aplicativo da web Spring.
    */
    // Route Public
    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){

        return ResponseEntity.ok("Public route ok");
    }

    // Route Privada
    @GetMapping("/private")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> privateRoute(Authentication authentication){

        return ResponseEntity.ok("Private route ok! Usuário conectado: " + authentication.getName());
    }
    /*
    @PreAuthorize
      Ela é usada para aplicar expressões de segurança antes da execução de um método,
     permitindo controlar o acesso a esse método com base em condições especificadas
     na expressão.
      @PreAuthorize("hasRole('ADMIN')"), a expressão hasRole('ADMIN') é utilizada
     para verificar se o usuário atual possui a função (role) de "ADMIN".
      Se o usuário tiver essa função, ele será autorizado a acessar o método
     protegido pela anotação @PreAuthorize.
     */

    @GetMapping("/admin")
    public ResponseEntity<String> adminRoute(Authentication authentication){
        return ResponseEntity.ok("Admin route ok");
    }

}