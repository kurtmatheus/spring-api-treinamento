package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDTO {
	private Long id;
	private String mensagem;
	private String nomeAutor;
	
	
	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.nomeAutor = resposta.getNomeAutor();
	}


	public Long getId() {
		return id;
	}


	public String getMensagem() {
		return mensagem;
	}


	public String getNomeAutor() {
		return nomeAutor;
	}
	
}
