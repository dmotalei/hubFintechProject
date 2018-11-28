package br.com.hubfintech.projeto.util;

import java.util.ArrayList;
import java.util.List;

import br.com.hubfintech.projeto.dto.ContaDTO;
import br.com.hubfintech.projeto.dto.PessoaDTO;
import br.com.hubfintech.projeto.dto.StatusContaDTO;
import br.com.hubfintech.projeto.dto.TransacaoDTO;
import br.com.hubfintech.projeto.entity.Conta;
import br.com.hubfintech.projeto.entity.Pessoa;
import br.com.hubfintech.projeto.entity.StatusConta;
import br.com.hubfintech.projeto.entity.Transacao;

public class ConverterUtils {

	public static Object convert(Object object) {
		
		Object retorno = null;
		
		if(object != null) {
			if(object instanceof List) {
				retorno = convertList(object);
			}
			if (object instanceof Transacao) {
				retorno = convertTransacao(object);
			}
			if (object instanceof Conta) {
				retorno = convertConta(object);
			}
			if (object instanceof Pessoa) {
				retorno = convertPessoa(object);
			}
			if (object instanceof StatusConta) {
				retorno = convertStatusConta(object);
			}
		}
		
		return retorno;
		
	}

	private static Object convertList(Object object) {
		Object retorno;
		List<Object> dtos = new ArrayList<Object>();
		List<Object> lista = (List<Object>) object;
		if(lista.size() > 0) {
			for(Object entity : lista) {
				Object dto = convert(entity);
				dtos.add(dto);
			}
		}
		retorno = dtos;
		return retorno;
	}

	private static Object convertTransacao(Object object) {
		Object retorno;
		Transacao entity = (Transacao) object;
		TransacaoDTO dto = new TransacaoDTO();
		dto.setCodigoEstornoAporte(entity.getCodigoEstornoAporte());
		dto.setContaDestino((ContaDTO) convertContaPai(entity.getContaDestino()));
		dto.setContaRemetente((ContaDTO) convertContaPai(entity.getContaRemetente()));
		dto.setDataTransacao(entity.getDataTransacao());
		dto.setId(entity.getId()); 
		dto.setValor(entity.getValor());
		dto.setFlagAporte(entity.getFlagAporte());
		dto.setDataEstorno(entity.getDataEstorno());
		
		retorno = dto;
		return retorno;
	}

	private static Object convertConta(Object object) {
		Object retorno;
		Conta entity = (Conta) object;
		ContaDTO dto = new ContaDTO();
		dto.setId(entity.getId());
		dto.setContaPai((ContaDTO) convertContaPai(entity.getContaPai()));
		dto.setNumeroConta(entity.getNumeroConta());
		dto.setPessoa((PessoaDTO) convert(entity.getPessoa()));
		dto.setSaldo(entity.getSaldo());
		dto.setStatusConta((StatusContaDTO) convert(entity.getStatusConta()));
		
		retorno = dto;
		return retorno;
	}

	private static Object convertContaPai(Conta object) {
		Object retorno = null;
		if(object != null) {
			Conta entity = (Conta) object;
			ContaDTO dto = new ContaDTO();
			dto.setId(entity.getId());
			dto.setNumeroConta(entity.getNumeroConta());
			dto.setPessoa((PessoaDTO) convert(entity.getPessoa()));
			dto.setSaldo(entity.getSaldo());
			dto.setStatusConta((StatusContaDTO) convert(entity.getStatusConta()));
			
			retorno = dto;
		}
		
		return retorno;
	}

	private static Object convertPessoa(Object object) {
		Object retorno;
		Pessoa entity = (Pessoa) object;
		PessoaDTO dto = new PessoaDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCpfCnpj(entity.getCpfCnpj());
		dto.setDataNascimento(entity.getDataNascimento());
		dto.setRazaoSocial(entity.getRazaoSocial());
		dto.setTipoPessoa(entity.getTipoPessoa());
		retorno = dto;
		return retorno;
	}

	private static Object convertStatusConta(Object object) {
		Object retorno;
		StatusConta entity = (StatusConta) object;
		StatusContaDTO dto = new StatusContaDTO();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		retorno = dto;
		return retorno;
	}
	
}
