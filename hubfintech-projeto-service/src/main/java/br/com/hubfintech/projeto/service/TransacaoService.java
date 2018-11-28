package br.com.hubfintech.projeto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.hubfintech.projeto.dto.TransacaoDTO;
import br.com.hubfintech.projeto.entity.Conta;
import br.com.hubfintech.projeto.entity.Transacao;
import br.com.hubfintech.projeto.enums.StatusContaEnum;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.repository.TransacaoRepository;
import br.com.hubfintech.projeto.util.ConverterUtils;

@Service
@Transactional(rollbackFor = ServiceException.class)
public class TransacaoService {

	@Autowired
	private TransacaoRepository repository;

	@Autowired
	private ContaService contaService;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
	public Page<TransacaoDTO> find(String numeroConta, Integer page, Integer size, Direction direction,
			String colunaOrdenacao) throws ServiceException {

		try {

			Order order = new Order(direction, colunaOrdenacao);
			Page<Transacao> pageTransacao = repository.findByConta(numeroConta,
					PageRequest.of(page, size, Sort.by(order)));

			List<TransacaoDTO> dtos = (List<TransacaoDTO>) ConverterUtils.convert(pageTransacao.getContent());
			if (dtos == null) {
				dtos = new ArrayList<TransacaoDTO>();
			}
			Page<TransacaoDTO> pageDto = new PageImpl<TransacaoDTO>(dtos, pageTransacao.getPageable(), dtos.size());

			return pageDto;
		} catch (Exception e) {
			throw new ServiceException("Erro ao consultar transações.", e);
		}

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public void estornar(Long id, String codigoEstorno) throws ServiceException {

		try {
			if (id == null) {
				throw new Exception();
			}

			Optional<Transacao> optional = repository.findById(id);
			if (optional == null || !optional.isPresent()) {
				throw new ServiceException("Transação não localizada.");
			}

			Transacao transacao = optional.get();
			if (transacao.getDataEstorno() != null) {
				throw new ServiceException("Transação já estornada anteriormente.");
			}

			List<Conta> contas = new ArrayList<Conta>();
			Conta contaDestino = contaService.findById(transacao.getContaDestino().getId());
			if (contaDestino == null) {
				throw new ServiceException("Conta destino não localizada.");
			} else if(StatusContaEnum.CANCELADO.getId().equals(contaDestino.getStatusConta().getId())) {
				throw new ServiceException("Conta destino cancelada. Por favor contate o administrador.");
			}

			if (transacao.getFlagAporte() == null || !transacao.getFlagAporte()) {
				Conta contaRemetente = contaService.findById(transacao.getContaRemetente().getId());
				if (contaRemetente == null) {
					throw new ServiceException("Conta remetente não localizada.");
				} else if(StatusContaEnum.CANCELADO.getId().equals(contaRemetente.getStatusConta().getId())) {
					throw new ServiceException("Conta remetente cancelada. Por favor contate o administrador.");
				}
				
				contaRemetente
				.setSaldo(contaRemetente.getSaldo() != null ? contaRemetente.getSaldo().add(transacao.getValor())
						: BigDecimal.ZERO.add(transacao.getValor()));
				
				contas.add(contaRemetente);
			} else if(StringUtils.isBlank(codigoEstorno)){
				throw new ServiceException("Preencha o código de estorno para o aporte.");
			}

			transacao.setDataEstorno(new Date());
			transacao.setCodigoEstornoAporte(codigoEstorno);
			contaDestino
					.setSaldo(contaDestino.getSaldo() != null ? contaDestino.getSaldo().subtract(transacao.getValor())
							: BigDecimal.ZERO.subtract(transacao.getValor()));
			contas.add(contaDestino);

			repository.save(transacao);
			contaService.saveAll(contas);

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("Erro ao estornar transação.", e);
		}

	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public void salvar(TransacaoDTO dto) throws ServiceException {

		try {
			if (dto == null
				 || dto.getContaDestino() == null
				 || dto.getValor() == null
				 || dto.getFlagAporte() == null
				 || StringUtils.isBlank(dto.getContaDestino().getNumeroConta())) {
				throw new ServiceException("Preencha todos os campos, por favor!");
			} else if(!dto.getFlagAporte() 
				&& (dto.getContaRemetente() == null
				|| StringUtils.isBlank(dto.getContaRemetente().getNumeroConta()))) {
				throw new ServiceException("Preencha todos os campos, por favor!");
			} else if(BigDecimal.ZERO.compareTo(dto.getValor()) > -1) {
				throw new ServiceException("Não é possível transferir o valor desejado, somente valores positivos serão aceitos!");
			}

			Transacao transacao = new Transacao();
			transacao.setValor(dto.getValor());
			transacao.setDataTransacao(new Date());
			transacao.setFlagAporte(dto.getFlagAporte());
			
			List<Conta> contas = new ArrayList<Conta>();
			Conta contaDestino = contaService.findByNumeroConta(dto.getContaDestino().getNumeroConta());
			if (contaDestino == null) {
				throw new ServiceException("Conta destino não localizada.");
			} else if(!StatusContaEnum.ATIVO.getId().equals(contaDestino.getStatusConta().getId())) {
				throw new ServiceException("Conta destino não está ativa.");
			}

			if (transacao.getFlagAporte() == null || !transacao.getFlagAporte()) {
				Conta contaRemetente = contaService.findByNumeroConta(dto.getContaRemetente().getNumeroConta());
				
				if (contaRemetente == null) {
					throw new ServiceException("Conta remetente não localizada.");
				} else if(!contaService.isContaFilha(contaRemetente.getId(), contaDestino.getId())){
					throw new ServiceException("A conta informada não é uma filial da conta remetente.");
				} else if(!StatusContaEnum.ATIVO.getId().equals(contaRemetente.getStatusConta().getId())) {
					throw new ServiceException("Conta não está ativa.");
				} else if(dto.getValor().compareTo(contaRemetente.getSaldo()) == 1) {
					throw new ServiceException("Saldo insuficiente.");
				}
				
				contaRemetente
				.setSaldo(contaRemetente.getSaldo() != null ? contaRemetente.getSaldo().subtract(transacao.getValor())
						: BigDecimal.ZERO.subtract(transacao.getValor()));
				transacao.setContaRemetente(contaRemetente);
				
				contas.add(contaRemetente);
			} else if(contaDestino.getContaPai() != null){
				throw new ServiceException("A conta informada não é uma conta matriz.");
			}

			transacao.setDataEstorno(null);
			transacao.setCodigoEstornoAporte(null);
			contaDestino
					.setSaldo(contaDestino.getSaldo() != null ? contaDestino.getSaldo().add(transacao.getValor())
							: BigDecimal.ZERO.add(transacao.getValor()));
			transacao.setContaDestino(contaDestino);
			contas.add(contaDestino);

			repository.save(transacao);
			contaService.saveAll(contas);

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("Erro ao estornar transação.", e);
		}

	}

}
