package com.nekibook.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nekibook.api.model.input.SenhaInput;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioController {

  @Autowired
  UsuarioService userService;

  @GetMapping
  public ResponseEntity<List<Usuario>> getAll() {
    List<Usuario> users = userService.getAll();
    if (!users.isEmpty()) return new ResponseEntity<>(
      users,
      HttpStatus.OK
    ); else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @GetMapping("/nome/{nomeUsuario}")
  public ResponseEntity<List<Usuario>> getByNome(
    @PathVariable String nomeUsuario
  ) {
    return new ResponseEntity<>(
      userService.findByNomeUsuario(nomeUsuario),
      HttpStatus.OK
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
    Usuario user = userService.getById(id);
    if (user != null) return new ResponseEntity<>(
      user,
      HttpStatus.OK
    ); else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<Usuario> getByEmail(@PathVariable String email) {
    Usuario user = userService.recuperarSenha(email);
    if (user != null) return new ResponseEntity<>(
      user,
      HttpStatus.OK
    ); else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  // @GetMapping("/info")
  // public User getUserDetails(){
  //     // Recuperando o e-mail a partir do contexto de segurança
  //     String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  //     // Devolvendo os dados do usuario a partir do e-mail informado
  //     return userService.findByEmail(email);
  // }

  @PostMapping
  public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
    return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> updateUser(
    @PathVariable Integer id,
    @RequestBody Usuario user
  ) {
    Usuario userAtualizada = userService.updateUser(id, user);
    if (userAtualizada != null) return new ResponseEntity<>(
      userAtualizada,
      HttpStatus.OK
    ); else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @PutMapping(value = "/alterar-foto-usuario/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<Usuario> updateFotoUsuario(
    @PathVariable Integer id,
    @RequestParam("filename") MultipartFile file
  ) throws IOException{
    Usuario usuario = userService.updateFotoUsuario(id, file);
    if(usuario == null)
			return new ResponseEntity<>(usuario, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(usuario, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
    if (userService.deleteUser(id)) return new ResponseEntity<>(
      true,
      HttpStatus.OK
    ); else return new ResponseEntity<>(false, HttpStatus.OK);
  }

  @PutMapping("/{userId}/senha")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void alterarSenha(
    @PathVariable Integer userId,
    @RequestBody @Valid SenhaInput senha
  ) {
    userService.alterarSenha(
      userId,
      senha.getSenhaAtual(),
      senha.getNovaSenha()
    );
  }
}
