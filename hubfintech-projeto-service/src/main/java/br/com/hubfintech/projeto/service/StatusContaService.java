package br.com.hubfintech.projeto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.hubfintech.projeto.dto.StatusContaDTO;
import br.com.hubfintech.projeto.entity.StatusConta;
import br.com.hubfintech.projeto.exception.ServiceException;
import br.com.hubfintech.projeto.repository.StatusContaRepository;
import br.com.hubfintech.projeto.util.ConverterUtils;


@Service
@Transactional(rollbackFor = ServiceException.class)
public class StatusContaService {
 
	@Autowired 
    private StatusContaRepository repository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public List<StatusContaDTO> findAll() throws ServiceException {
		try {
			List<StatusConta> list = repository.findAll();
			
			List<StatusContaDTO> dtos = (List<StatusContaDTO>) ConverterUtils.convert(list);
			if(dtos == null) {
				dtos = new ArrayList<StatusContaDTO>();
			}
			
			return dtos;
		} catch (Exception e) {
			throw new ServiceException("Erro ao consultar statusConta.", e);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation=Isolation.SERIALIZABLE)
	public StatusConta findById(Long id) {
		StatusConta retono = null;
		
		Optional<StatusConta> optional = repository.findById(id);
		if(optional != null
			&& optional.isPresent()) {
			retono = optional.get();
		}
		
		return retono;
	}
	
}
