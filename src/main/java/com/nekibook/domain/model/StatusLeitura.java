package com.nekibook.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusLeitura {

    DESEJADO("Desejado"),
	LENDO("Lendo", DESEJADO),
	LIDO("Lido", LENDO);
	
	private String descricao;
	private List<StatusLeitura> statusAnteriores;
	
	StatusLeitura(String descricao, StatusLeitura... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarPara(StatusLeitura novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}
	
	public boolean podeAlterarPara(StatusLeitura novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}

}
