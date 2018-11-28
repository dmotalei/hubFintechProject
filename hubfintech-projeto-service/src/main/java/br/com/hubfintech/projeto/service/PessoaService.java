package br.com.hubfintech.projeto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.hubfintech.projeto.dto.PessoaDTO;
import br.com.hubfintech.projeto.entity.Pessoa;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.repository.PessoaRepository;
import br.com.hubfintech.projeto.util.ConverterUtils;
import br.com.hubfintech.projeto.util.Utils;


@Service
@Transactional(rollbackFor = ServiceException.class)
public class PessoaService {
 
	@Autowired 
    private PessoaRepository repository;
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public Pessoa findById(Long id) throws ServiceException {
		
		
		try {
			Pessoa retorno = null;
			Optional<Pessoa> pessoa = repository.findById(id);
			if(pessoa != null
				&& pessoa.isPresent()) {
				retorno = pessoa.get();
			}
			
			return retorno;
		} catch (Exception e) {
			throw new ServiceException("findById(Long) - ", e);
		}
		
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public Page<PessoaDTO> findByCpfCnpj(String cpfCnpj, Integer page, Integer size, Direction direction,
			String colunaOrdenacao) throws ServiceException {

		try {
			Order order = new Order(direction, colunaOrdenacao);
			Page<Pessoa> pessoaPage = repository.findByCpfCnpjContainingIgnoreCase(Utils.removerMascaraCpfCnpj(cpfCnpj),  PageRequest.of(page, size, Sort.by(order)));
			
			List<PessoaDTO> dtos = (List<PessoaDTO>) ConverterUtils.convert(pessoaPage.getContent());
			if(dtos == null) {
				dtos = new ArrayList<PessoaDTO>();
			}
			Page<PessoaDTO> pageDto = new PageImpl<PessoaDTO>(dtos, pessoaPage.getPageable(), dtos.size());
			
			return pageDto;
		} catch (Exception e) {
			throw new ServiceException("Erro ao consultar pessoas.", e);
		}
		
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public Pessoa findByCpfCnpj(PessoaDTO dto) {
		Pessoa pessoa = null;
		if(dto != null 
			&& StringUtils.isNotBlank(dto.getCpfCnpj())) {
			pessoa = repository.findByCpfCnpj(dto.getCpfCnpj());
		}
		return pessoa;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE)
	public void save(PessoaDTO dto) throws ServiceException {

		try {
			if(dto == null 
				|| StringUtils.isBlank(dto.getCpfCnpj())
				|| dto.getTipoPessoa() == null) {
				throw new ServiceException("Preencha os campos obrigatórios, por favor!");
			} 
			
			dto.setCpfCnpj(Utils.formatCpfCnpj(dto.getCpfCnpj(), dto.getTipoPessoa()));
			
			Pessoa pessoa = findByCpfCnpj(dto);
			if(pessoa != null) {
				if(dto.getId() == null) {
					throw new ServiceException("Cpf/Cnpj já utilizado.");
				} else if(!dto.getId().equals(pessoa.getId())){
					throw new ServiceException("Cpf/Cnpj já utilizado.");
				}
			} else {
				if(dto.getId() != null) {
					Optional<Pessoa> optional = repository.findById(dto.getId());
					if(optional != null
						&& optional.isPresent()) {
						throw new ServiceException("Dados inconsistentes, contate o administrador.");
					}
				}
				
				pessoa = new Pessoa();
			}
		
		
			pessoa.setCpfCnpj(dto.getCpfCnpj());
			pessoa.setNome(dto.getNome());
			pessoa.setTipoPessoa(dto.getTipoPessoa());
			if(Utils.isPf(dto.getTipoPessoa())) {
				pessoa.setDataNascimento(dto.getDataNascimento());
			} else {
				pessoa.setRazaoSocial(dto.getRazaoSocial());
			}
			
			repository.save(pessoa);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("Erro ao salvar nova pessoa.", e);
		}
		
	}

}
