package com.nekibook.domain.model.dto;

import java.util.List;

import com.nekibook.domain.model.Leitura;

public class UsuarioDTO {

    private int user_id;
    private String nome;

    private String email;
    private String setor;
    private String password;

    private List<LeituraDTO> leituras;

    public UsuarioDTO(int user_id, String nome, String email, String setor, String password,
            List<LeituraDTO> leituras) {
        this.user_id = user_id;
        this.nome = nome;
        this.email = email;
        this.setor = setor;
        this.password = password;
        this.leituras = leituras;
    }

    public UsuarioDTO() {
        super();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LeituraDTO> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<LeituraDTO> leituras) {
        this.leituras = leituras;
    }

}
