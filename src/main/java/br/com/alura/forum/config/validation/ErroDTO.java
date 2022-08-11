package br.com.alura.forum.config.validation;

public class ErroDTO {
	
	private String campoErro;
	private String erro;
	
	public ErroDTO(String campoErro, String erro) {
		this.campoErro = campoErro;
		this.erro = erro;
	}

	public String getCampoErro() {
		return campoErro;
	}

	public String getErro() {
		return erro;
	}
	
	@Override
	public String toString() {
		return String.format("campoErro: s%\nErro: %s.", campoErro, erro);
	}	
}
