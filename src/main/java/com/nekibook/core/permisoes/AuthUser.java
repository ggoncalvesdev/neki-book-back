package com.nekibook.core.permisoes;

import com.nekibook.domain.model.Usuario;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class AuthUser extends User {

  private static final long serialVersionUID = 1L;

  private Integer userId;
  private String fullName;

  public AuthUser(
    Usuario usuario,
    Collection<? extends GrantedAuthority> authorities
  ) {
    super(usuario.getEmail(), usuario.getPassword(), authorities);
    this.userId = usuario.getId();
    this.fullName = usuario.getNome();
  }
}
