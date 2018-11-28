package br.com.hubfintech.projeto.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ContaDTO implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String numeroConta;
	
	private PessoaDTO pessoa;
	
	private StatusContaDTO statusConta;
	
	private ContaDTO contaPai;
	
	private BigDecimal saldo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public StatusContaDTO getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(StatusContaDTO statusConta) {
		this.statusConta = statusConta;
	}

	public ContaDTO getContaPai() {
		return contaPai;
	}

	public void setContaPai(ContaDTO contaPai) {
		this.contaPai = contaPai;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaPai == null) ? 0 : contaPai.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((statusConta == null) ? 0 : statusConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaDTO other = (ContaDTO) obj;
		if (contaPai == null) {
			if (other.contaPai != null)
				return false;
		} else if (!contaPai.equals(other.contaPai))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroConta == null) {
			if (other.numeroConta != null)
				return false;
		} else if (!numeroConta.equals(other.numeroConta))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (statusConta == null) {
			if (other.statusConta != null)
				return false;
		} else if (!statusConta.equals(other.statusConta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", numeroConta=" + numeroConta + ", pessoa=" + pessoa + ", statusConta="
				+ statusConta + ", contaPai=" + contaPai + ", saldo=" + saldo + "]";
	}
	
}
