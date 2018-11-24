package br.com.hubfintech.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.hubfintech.projeto.dto.PessoaDTO;
import br.com.hubfintech.projeto.entity.Pessoa;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.repository.PessoaRepository;


@Service
@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
public class PessoaService {
 
	@Autowired 
    private PessoaRepository repository;
	
	public PessoaDTO findById(Long id) throws ServiceException {
		
		
		try {
			PessoaDTO retorno = null;
			Optional<Pessoa> pessoa = repository.findById(id);
			if(pessoa != null
				&& pessoa.isPresent()) {
				retorno = convert(pessoa.get());
			}
			
			return retorno;
		} catch (Exception e) {
			throw new ServiceException("findById(Long) - ", e);
		}
		
	}

	private PessoaDTO convert(Pessoa pessoa) {
		
		PessoaDTO retorno = null;
		
		if(pessoa != null) {
			retorno = new PessoaDTO();
			retorno.setCpfCnpj(pessoa.getCpfCnpj());
			retorno.setDataNascimento(pessoa.getDataNascimento());
			retorno.setId(pessoa.getId());
			retorno.setNome(pessoa.getNome());
			retorno.setRazaoSocial(pessoa.getRazaoSocial());
			retorno.setTipoPessoa(pessoa.getTipoPessoa());
		}
		
		return retorno;
	}
}
