package io.github.GuaMaia.sbootexpsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    /*
          SecurityFilterChain é implementada para definir uma cadeia de filtros
         de segurança que serão aplicados em uma ordem específica
         para proteger diferentes partes de um aplicativo web.
         ocê pode definir quais filtros de segurança serão aplicados e em que
         ordem eles serão executados. Esses filtros podem incluir autenticação de usuários,
         autorização de acesso a recursos, prevenção de ataques, entre
         outros mecanismos de segurança.

          @EnableMethodSecurity é uma anotação do Spring Security que habilita o suporte
         à segurança baseada em métodos em um aplicativo Spring.
          Quando esta anotação é usada em uma classe de configuração, ela ativa a capacidade
         de aplicar anotações de segurança em métodos específicos para controlar o
         acesso aos mesmos.
          Você pode restringir o acesso a um determinado método apenas
         a usuários com certas funções ou permissões.

         Neste exmplo estou dando permissão para o usua´rop ADMIN.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SenhaMaster  senhaMasterAuthenticationProvider,
            CustomAutheticationProvider customAuthenticationProvider,
            CustomFilter customFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/public").permitAll();
                    customizer.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authenticationProvider(senhaMasterAuthenticationProvider)
                .authenticationProvider(customAuthenticationProvider)
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /// criando usuário em memoria
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails commonUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(commonUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}

