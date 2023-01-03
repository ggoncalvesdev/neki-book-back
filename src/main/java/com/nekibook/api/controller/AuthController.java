package com.nekibook.api.controller;

import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.model.dto.CredenciaisLoginDTO;
import com.nekibook.domain.service.UsuarioService;
import com.nekibook.domain.service.security.JWTUtil;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  // Injecting Dependencies
  @Autowired
  private UsuarioService userService;

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Registro de usuario
  @PostMapping("/registro")
  public Map<String, Object> registerHandler(@RequestBody Usuario user) {
    // Encriptando a senha usando o Bcrypt
    String encodedPass = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPass);

    user = userService.saveUser(user);

    // Gerando o token JWT a partir do e-mail do Usuario
    //String token = jwtUtil.generateToken(user.getEmail());

    // Gerando o token JWT a partir dos dados do Usuario
    Usuario usuarioResumido = new Usuario();
    usuarioResumido.setNome(user.getNome());
    usuarioResumido.setEmail(user.getEmail());
    usuarioResumido.setId(user.getId());
    String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

    // Retornando a resposta com o JWT
    return Collections.singletonMap("jwt-token", token);
  }

  // Login de usuario
  @PostMapping("/login")
  public Map<String, Object> loginHandler(
    @RequestBody CredenciaisLoginDTO credenciaisLoginDTO
  ) {
    try {
      // Criando o token que sera usado no processo de autenticacao
      UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
        credenciaisLoginDTO.getEmail(),
        credenciaisLoginDTO.getPassword()
      );

      // Autenticando as credenciais de login
      authManager.authenticate(authInputToken);

      // Se o processo de autenticacao foi concluido com sucesso - etapa anterior,
      // eh gerado o JWT
      //String token = jwtUtil.generateToken(body.getEmail());

      Usuario user = userService.findByEmail(credenciaisLoginDTO.getEmail());
      Usuario usuarioResumido = new Usuario();
      usuarioResumido.setNome(user.getNome());
      usuarioResumido.setEmail(user.getEmail());
      usuarioResumido.setId(user.getId());
      usuarioResumido.setImagemUrl(user.getImagemUrl());
      usuarioResumido.setSetor(user.getSetor());
      // Gerando o token JWT a partir dos dados do Usuario
      String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

      // Responde com o JWT
      return Collections.singletonMap("jwt-token", token);
    } catch (AuthenticationException authExc) {
      throw new RuntimeException("Credenciais Invalidas");
    }
  }
}
