package com.nekibook.api.controller;

import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

  @Autowired
  private UsuarioService userService;

  @GetMapping("/{email}")
  public ResponseEntity<Usuario> getByEmail(@PathVariable String email) {
    Usuario user = userService.recuperarSenha(email);
    if (user != null) return new ResponseEntity<>(
      user,
      HttpStatus.OK
    ); else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
