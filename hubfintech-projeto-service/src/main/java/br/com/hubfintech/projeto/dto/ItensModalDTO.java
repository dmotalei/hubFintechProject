package br.com.hubfintech.projeto.dto;

import java.io.Serializable;
import java.util.List;

public class ItensModalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4586232181741744545L;

	private List<StatusContaDTO> statusList;
	
	private List<ContaDTO> contaList;
	
	private List<PessoaDTO> pessoaList;

	public List<StatusContaDTO> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<StatusContaDTO> statusList) {
		this.statusList = statusList;
	}

	public List<ContaDTO> getContaList() {
		return contaList;
	}

	public void setContaList(List<ContaDTO> contaList) {
		this.contaList = contaList;
	}

	public List<PessoaDTO> getPessoaList() {
		return pessoaList;
	}

	public void setPessoaList(List<PessoaDTO> pessoaList) {
		this.pessoaList = pessoaList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contaList == null) ? 0 : contaList.hashCode());
		result = prime * result + ((pessoaList == null) ? 0 : pessoaList.hashCode());
		result = prime * result + ((statusList == null) ? 0 : statusList.hashCode());
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
		ItensModalDTO other = (ItensModalDTO) obj;
		if (contaList == null) {
			if (other.contaList != null)
				return false;
		} else if (!contaList.equals(other.contaList))
			return false;
		if (pessoaList == null) {
			if (other.pessoaList != null)
				return false;
		} else if (!pessoaList.equals(other.pessoaList))
			return false;
		if (statusList == null) {
			if (other.statusList != null)
				return false;
		} else if (!statusList.equals(other.statusList))
			return false;
		return true;
	}
	
	
}
