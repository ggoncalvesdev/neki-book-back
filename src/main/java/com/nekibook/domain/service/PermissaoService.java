package com.nekibook.domain.service;

import com.nekibook.domain.exception.PermissaoNaoEncontradaException;
import com.nekibook.domain.model.Permissao;
import com.nekibook.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

  @Autowired
  private PermissaoRepository permissaoRepository;

  public Permissao buscarOuFalhar(Long permissaoId) {
    return permissaoRepository
      .findById(permissaoId)
      .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
  }
}
