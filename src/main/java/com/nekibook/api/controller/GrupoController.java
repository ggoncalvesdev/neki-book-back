package com.nekibook.api.controller;

import com.nekibook.api.assembler.GrupoInputDisassembler;
import com.nekibook.api.assembler.GrupoModelAssembler;
import com.nekibook.api.model.GrupoModel;
import com.nekibook.api.model.input.GrupoInput;
import com.nekibook.domain.model.Grupo;
import com.nekibook.domain.repository.GrupoRepository;
import com.nekibook.domain.service.GrupoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

  @Autowired
  private GrupoRepository grupoRepository;

  @Autowired
  private GrupoService cadastroGrupo;

  @Autowired
  private GrupoModelAssembler grupoModelAssembler;

  @Autowired
  private GrupoInputDisassembler grupoInputDisassembler;

  @GetMapping
  public List<GrupoModel> listar() {
    List<Grupo> todosGrupos = grupoRepository.findAll();

    return grupoModelAssembler.toCollectionModel(todosGrupos);
  }

  @GetMapping("/{grupoId}")
  public GrupoModel buscar(@PathVariable Long grupoId) {
    Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

    return grupoModelAssembler.toModel(grupo);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
    Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

    grupo = cadastroGrupo.salvar(grupo);

    return grupoModelAssembler.toModel(grupo);
  }

  @PutMapping("/{grupoId}")
  public GrupoModel atualizar(
    @PathVariable Long grupoId,
    @RequestBody @Valid GrupoInput grupoInput
  ) {
    Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

    grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

    grupoAtual = cadastroGrupo.salvar(grupoAtual);

    return grupoModelAssembler.toModel(grupoAtual);
  }

  @DeleteMapping("/{grupoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long grupoId) {
    cadastroGrupo.excluir(grupoId);
  }
}
