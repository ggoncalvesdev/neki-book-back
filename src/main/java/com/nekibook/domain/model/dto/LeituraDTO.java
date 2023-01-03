package com.nekibook.domain.model.dto;

import com.nekibook.domain.model.StatusLeitura;

public class LeituraDTO {

    private int idLeitura;
    private UsuarioDTO usuarios;
    private LivroDTO livros;
    private StatusLeitura status;

    public LeituraDTO(int idLeitura, UsuarioDTO usuarios, LivroDTO livros, StatusLeitura status) {
        this.idLeitura = idLeitura;
        this.usuarios = usuarios;
        this.livros = livros;
        this.status = status;
    }

    public LeituraDTO() {
        super();
    }

    public int getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(int idLeitura) {
        this.idLeitura = idLeitura;
    }

    public UsuarioDTO getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(UsuarioDTO usuarios) {
        this.usuarios = usuarios;
    }

    public LivroDTO getLivros() {
        return livros;
    }

    public void setLivros(LivroDTO livros) {
        this.livros = livros;
    }

    public StatusLeitura getStatus() {
        return status;
    }

    public void setStatus(StatusLeitura status) {
        this.status = status;
    }

}
