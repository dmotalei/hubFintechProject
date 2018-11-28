package br.com.hubfintech.projeto.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransacaoDTO implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ContaDTO contaDestino;
	
	private ContaDTO contaRemetente;
	
	private BigDecimal valor;
	
	private Date dataTransacao;
	
	private Date dataEstorno;

	private Boolean flagAporte;
	
	private String codigoEstornoAporte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContaDTO getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(ContaDTO contaDestino) {
		this.contaDestino = contaDestino;
	}

	public ContaDTO getContaRemetente() {
		return contaRemetente;
	}

	public void setContaRemetente(ContaDTO contaRemetente) {
		this.contaRemetente = contaRemetente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Date getDataEstorno() {
		return dataEstorno;
	}

	public void setDataEstorno(Date dataEstorno) {
		this.dataEstorno = dataEstorno;
	}

	public Boolean getFlagAporte() {
		return flagAporte;
	}

	public void setFlagAporte(Boolean flagAporte) {
		this.flagAporte = flagAporte;
	}

	public String getCodigoEstornoAporte() {
		return codigoEstornoAporte;
	}

	public void setCodigoEstornoAporte(String codigoEstornoAporte) {
		this.codigoEstornoAporte = codigoEstornoAporte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoEstornoAporte == null) ? 0 : codigoEstornoAporte.hashCode());
		result = prime * result + ((contaDestino == null) ? 0 : contaDestino.hashCode());
		result = prime * result + ((contaRemetente == null) ? 0 : contaRemetente.hashCode());
		result = prime * result + ((dataEstorno == null) ? 0 : dataEstorno.hashCode());
		result = prime * result + ((dataTransacao == null) ? 0 : dataTransacao.hashCode());
		result = prime * result + ((flagAporte == null) ? 0 : flagAporte.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		TransacaoDTO other = (TransacaoDTO) obj;
		if (codigoEstornoAporte == null) {
			if (other.codigoEstornoAporte != null)
				return false;
		} else if (!codigoEstornoAporte.equals(other.codigoEstornoAporte))
			return false;
		if (contaDestino == null) {
			if (other.contaDestino != null)
				return false;
		} else if (!contaDestino.equals(other.contaDestino))
			return false;
		if (contaRemetente == null) {
			if (other.contaRemetente != null)
				return false;
		} else if (!contaRemetente.equals(other.contaRemetente))
			return false;
		if (dataEstorno == null) {
			if (other.dataEstorno != null)
				return false;
		} else if (!dataEstorno.equals(other.dataEstorno))
			return false;
		if (dataTransacao == null) {
			if (other.dataTransacao != null)
				return false;
		} else if (!dataTransacao.equals(other.dataTransacao))
			return false;
		if (flagAporte == null) {
			if (other.flagAporte != null)
				return false;
		} else if (!flagAporte.equals(other.flagAporte))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransacaoDTO [id=" + id + ", contaDestino=" + contaDestino + ", contaRemetente=" + contaRemetente
				+ ", valor=" + valor + ", dataTransacao=" + dataTransacao + ", dataEstorno=" + dataEstorno
				+ ", flagAporte=" + flagAporte + ", codigoEstornoAporte=" + codigoEstornoAporte + "]";
	}
	
}
