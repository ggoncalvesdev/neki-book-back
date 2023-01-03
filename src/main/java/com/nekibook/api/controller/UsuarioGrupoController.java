package com.nekibook.api.controller;

import com.nekibook.api.assembler.GrupoModelAssembler;
import com.nekibook.api.model.GrupoModel;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

  @Autowired
  private UsuarioService cadastroUsuario;

  @Autowired
  private GrupoModelAssembler grupoModelAssembler;

  @GetMapping
  public List<GrupoModel> listar(@PathVariable Integer usuarioId) {
    Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

    return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
  }

  @DeleteMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(
    @PathVariable Integer usuarioId,
    @PathVariable Long grupoId
  ) {
    cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
  }

  @PutMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(
    @PathVariable Integer usuarioId,
    @PathVariable Long grupoId
  ) {
    cadastroUsuario.associarGrupo(usuarioId, grupoId);
  }
}
