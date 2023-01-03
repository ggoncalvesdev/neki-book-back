package com.nekibook.api.assembler;

import com.nekibook.api.model.input.UsuarioInput;
import com.nekibook.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Usuario toDomainObject(UsuarioInput userInput) {
    return modelMapper.map(userInput, Usuario.class);
  }

  public void copyToDomainObject(UsuarioInput userInput, Usuario user) {
    modelMapper.map(userInput, user);
  }
}
