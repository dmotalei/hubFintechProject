package br.com.hubfintech.projeto.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

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

import br.com.hubfintech.projeto.dto.ContaDTO;
import br.com.hubfintech.projeto.entity.Conta;
import br.com.hubfintech.projeto.entity.Pessoa;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.repository.ContaRepository;
import br.com.hubfintech.projeto.util.ConverterUtils;


@Service
@Transactional(rollbackFor = ServiceException.class)
public class ContaService {
 
	@Autowired 
    private ContaRepository repository;
	
	@Autowired 
    private PessoaService pessoaService;
	
	@Autowired 
    private StatusContaService statusContaService;
	
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public Page<ContaDTO> findByNumeroConta(String numeroConta, Integer page, Integer size, Direction direction,
			String colunaOrdenacao) throws ServiceException {
		try {
			Order order = new Order(direction, colunaOrdenacao);
			Page<Conta> contaPage = repository.findByNumeroConta(numeroConta,  PageRequest.of(page, size, Sort.by(order)));
			
			List<ContaDTO> dtos = (List<ContaDTO>) ConverterUtils.convert(contaPage.getContent());
			if(dtos == null) {
				dtos = new ArrayList<ContaDTO>();
			}
			Page<ContaDTO> pageDto = new PageImpl<ContaDTO>(dtos, contaPage.getPageable(), dtos.size());
			
			return pageDto;
		} catch (Exception e) {
			throw new ServiceException("Erro ao consultar contas.", e);
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE)
	public void save(ContaDTO dto) throws ServiceException {
		try {
			if(dto == null 
				|| StringUtils.isBlank(dto.getNumeroConta())
				|| dto.getPessoa() == null
				|| StringUtils.isBlank(dto.getPessoa().getCpfCnpj())
				|| dto.getStatusConta() == null
				|| dto.getStatusConta().getId() == null) {
				throw new ServiceException("Preencha os campos obrigatórios, por favor!");
			} 
			
			Conta conta = repository.findByNumeroConta(dto.getNumeroConta());
			if(conta != null) {
				if(dto.getId() == null) {
					throw new ServiceException("Número Conta já utilizado.");
				} else if(!dto.getId().equals(conta.getId())) {
					throw new ServiceException("Número Conta já utilizado.");
				}
			} else {
				if(dto.getId() != null) {
					Optional<Conta> optional = repository.findById(dto.getId());
					if(optional != null
						&& optional.isPresent()) {
						throw new ServiceException("Dados inconsistentes, contate o administrador.");
					}
				}
				
				conta = new Conta();
				conta.setSaldo(BigDecimal.ZERO);
				conta.setNumeroConta(dto.getNumeroConta());
			}
			
			Pessoa pessoa = pessoaService.findByCpfCnpj(dto.getPessoa());
			if(pessoa == null) {
				throw new ServiceException("Cpf/Cnpj não encontrado.");
			}
			conta.setPessoa(pessoa);
			
			if(dto.getContaPai() != null
				&& StringUtils.isNotBlank(dto.getContaPai().getNumeroConta())) {
				if(dto.getNumeroConta().equals(dto.getContaPai().getNumeroConta())) {
					throw new ServiceException("Número da conta pai não pode ser igual a da filial.");
				}
				
				Conta contaPai = repository.findByNumeroConta(dto.getContaPai().getNumeroConta());
				if(contaPai == null) {
					throw new ServiceException("Conta pai não encontrada.");
				}
				conta.setContaPai(contaPai);
			} else {
				conta.setContaPai(null);
			}
			
			conta.setStatusConta(statusContaService.findById(dto.getStatusConta().getId()));
			
			repository.save(conta);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("Erro ao salvar nova pessoa.", e);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public Conta findById(Long id) throws ServiceException {

		try {
			Conta conta = null;
			
			Optional<Conta> optional = repository.findById(id);
			if(optional != null
				&& optional.isPresent()) {
				conta = optional.get();
			}
			
			return conta;
		} catch (Exception e) {
			throw new ServiceException("Erro ao consultar contas.", e);
		}
		
	}
	
	public void saveAll(List<Conta> contas) {
		repository.saveAll(contas);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public Conta findByNumeroConta(String numeroConta) {
		return repository.findByNumeroConta(numeroConta);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE)
	public boolean isContaFilha(Long idContaRemetente, Long idContaDestino) throws ServiceException {
		try {
			Boolean resultado = Boolean.FALSE;
			
			//StoredProcedureQuery query = em.createStoredProcedureQuery("getFiliaisFrom ("+ idContaRemetente +")");
		    StoredProcedureQuery query = em.createStoredProcedureQuery("getFiliaisFrom");
		    query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
		    query.setParameter(1, idContaRemetente);
			
			
			List<Object[]> contasDestinos = query.getResultList();
			
			
			
			if(contasDestinos != null
				&& !contasDestinos.isEmpty()) {
				for(Object[] destinos : contasDestinos) {
					BigInteger bigInteger = (BigInteger) destinos[0];
					if(idContaDestino.equals(bigInteger.longValue())) {
						resultado = Boolean.TRUE;
						break;
					}
				}
			} 
			
			return resultado;
		} catch (Exception e) {
			throw new ServiceException("Erro ao verificar contas filiais.", e);
		}
	}
	
}
