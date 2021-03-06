package br.com.hubfintech.projeto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_TRANSACAO", schema="DB_PROJETO")
public class Transacao implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CONTA_DESTINO", referencedColumnName="ID", nullable=false)
	private Conta contaDestino;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CONTA_REMETENTE", referencedColumnName="ID")
	private Conta contaRemetente;
	
	@Column(name="VALOR")
	private BigDecimal valor;
	
	@Column(name="DATA_TRANSACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTransacao;
	
	@Column(name="DATA_ESTORNO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEstorno;

	@Column(name="FLAG_APORTE")
	private Boolean flagAporte;
	
	@Column(name="CODIGO_ESTORNO_APORTE")
	private String codigoEstornoAporte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Conta getContaRemetente() {
		return contaRemetente;
	}

	public void setContaRemetente(Conta contaRemetente) {
		this.contaRemetente = contaRemetente;
	}

	public String getCodigoEstornoAporte() {
		return codigoEstornoAporte;
	}

	public void setCodigoEstornoAporte(String codigoEstornoAporte) {
		this.codigoEstornoAporte = codigoEstornoAporte;
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
		Transacao other = (Transacao) obj;
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
		return "Transacao [id=" + id + ", contaDestino=" + contaDestino + ", contaRemetente=" + contaRemetente
				+ ", valor=" + valor + ", dataTransacao=" + dataTransacao + ", dataEstorno=" + dataEstorno
				+ ", flagAporte=" + flagAporte + ", codigoEstornoAporte=" + codigoEstornoAporte + "]";
	}
	
}
