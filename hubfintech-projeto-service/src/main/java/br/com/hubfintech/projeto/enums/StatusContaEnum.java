package br.com.hubfintech.projeto.enums;

public enum StatusContaEnum {

	ATIVO(1L),
	BLOQUEADO(2L),
	CANCELADO(3L);
	
	private Long id;

	private StatusContaEnum(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
}
