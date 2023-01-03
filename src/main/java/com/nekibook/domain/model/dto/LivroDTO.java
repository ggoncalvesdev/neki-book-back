package com.nekibook.domain.model.dto;

import java.util.List;

public class LivroDTO {

    private int idLivro;
    private List<LeituraDTO> leituras;
    private String nomeLivro;
    private String autor;
    private String imagem;

    public LivroDTO(int idLivro, List<LeituraDTO> leituras, String nomeLivro, String autor, String imagem) {
        this.idLivro = idLivro;
        this.leituras = leituras;
        this.nomeLivro = nomeLivro;
        this.autor = autor;
        this.imagem = imagem;
    }

    public LivroDTO() {
        super();
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public List<LeituraDTO> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<LeituraDTO> leituras) {
        this.leituras = leituras;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
