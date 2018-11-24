package br.com.hubfintech.projeto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CONTA_DESTINO", referencedColumnName="ID")
	private Conta contaDestino;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CONTA_PAI", referencedColumnName="ID", nullable=false)
	private Conta contaRemetente;
	
	@Column(name="SALDO")
	private BigDecimal saldo;
	
	@Column(name="DATA_SOLICITACA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSolicitacao;
	
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

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
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
		result = prime * result + ((dataSolicitacao == null) ? 0 : dataSolicitacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
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
		if (dataSolicitacao == null) {
			if (other.dataSolicitacao != null)
				return false;
		} else if (!dataSolicitacao.equals(other.dataSolicitacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", contaDestino=" + contaDestino + ", contaRemetente=" + contaRemetente
				+ ", saldo=" + saldo + ", dataSolicitacao=" + dataSolicitacao + ", codigoEstornoAporte="
				+ codigoEstornoAporte + "]";
	}
	
}
