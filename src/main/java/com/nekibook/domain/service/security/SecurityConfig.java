package com.nekibook.domain.service.security;

import java.util.Collections;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Define que essa classe e uma configuracao para o Spring
@Configuration
//Habilita a seguranca para a api
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JWTFilter filter;

  @Autowired
  private UsuarioDetailsServiceImpl uds;

  //Metodo encarregado de configurar a seguranca da API
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable() // Desabilitando o csrf
      .httpBasic()
      .disable() // Desabilitando a autenticacao por http basico(usuario e senha)
      .cors() // Habilitando o cors
      .and()
      .authorizeHttpRequests() // Autorizando requisicoes de entrada
      /* .antMatchers(HttpMethod.DELETE, "/user/**")
      .hasAuthority("EXCLUIR_CONTA") */
      .antMatchers(
        "/auth/**",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/actuator/**",
        "/email/**"
      )
      .permitAll() // Autorizando requisicoes autenticadas
      .antMatchers(HttpMethod.DELETE, "/user/**")
      .hasAuthority("ADMIN") // Autorizando apenas usuarios com o perfil "USER" a utilizar esse endpoint (nesse caso, path /user/ e uri´s subsequentes
      .anyRequest()
      .authenticated() // Todas as requisicoes, exceto para as definidas acima, precisarao ser autenticadas
      .and()
      .userDetailsService(uds) // Setando o servico "user details" (do modulo Security do Spring) para essa implementacao customizada
      .exceptionHandling()
      .authenticationEntryPoint((request, response, authException) -> //  Logo, isso significa que essa requisicao requer autenticacao e um JWT valido // Rejeitando requisicoes nao autorizadas que chegam ate esse ponto.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
      )
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configurando a Sessao para que cada requisicao seja independente (stateless)
    /*       .and()
      .oauth2ResourceServer()
      .jwt()
      .jwtAuthenticationConverter(jwtAuthenticationConverter()); */

    // Adicionando o filtro JWT
    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
  }

  // Bean (tipo de Service) que sera responsavel por encriptar a senha
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Expondo o bean responsavel pelo gerenciamento do processo de autenticacao
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  /*  private JwtAuthenticationConverter jwtAuthenticationConverter() {
    var jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
      var authorities = jwt.getClaimAsStringList("authorities");

      if (authorities == null) {
        authorities = Collections.emptyList();
      }

      return authorities
        .stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    });

    return jwtAuthenticationConverter;
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    var jwtAccessTokenConverter = new JwtAccessTokenConverter();
    //		jwtAccessTokenConverter.setSigningKey("89a7sd89f7as98f7dsa98fds7fd89sasd9898asdf98s");

    var jksResource = new ClassPathResource("keystores/nekibook.jks");
    var keyStorePass = "147258";
    var keyPairAlias = "nekibook";

    var keyStoreKeyFactory = new KeyStoreKeyFactory(
      jksResource,
      keyStorePass.toCharArray()
    );
    var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);

    jwtAccessTokenConverter.setKeyPair(keyPair);

    return jwtAccessTokenConverter;
  } */
}
