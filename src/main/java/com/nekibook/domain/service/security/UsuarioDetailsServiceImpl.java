package com.nekibook.domain.service.security;

import com.nekibook.core.permisoes.AuthUser;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.repository.UsuarioRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// O UserDetailService e usado para recuperar os detalhes do usuario que esta tentando se autenticar
// na aplicacao. Isso e feito atraves do metodo loadUserByUsername.
// Se o usuario nao for encontrado e disparada uma excecao do tipo UsernameNotFoundException

@Component
public class UsuarioDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository userRepo;

  /*  @Override
  public UserDetails loadUserByUsername(String email)
    throws UsernameNotFoundException {
    Optional<Usuario> userRes = userRepo.findByEmail(email);
    if (userRes.isEmpty()) throw new UsernameNotFoundException(
      "Não foi possível encontrar usuário com o email = " + email
    );

    Usuario user = userRes.get();
    return new org.springframework.security.core.userdetails.User(
      email,
      user.getPassword(),
      Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    ); // Define, de forma estatica, o perfil do usuario encontrado
  } */

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    Usuario usuario = userRepo
      .findByEmail(username)
      .orElseThrow(() ->
        new UsernameNotFoundException(
          "Usuário não encontrado com e-mail informado"
        )
      );

    return new AuthUser(usuario, getAuthorities(usuario));
  }

  private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
    return usuario
      .getGrupos()
      .stream()
      .flatMap(grupo -> grupo.getPermissoes().stream())
      .map(permissao ->
        new SimpleGrantedAuthority(permissao.getNome().toUpperCase())
      )
      .collect(Collectors.toSet());
  }
}
