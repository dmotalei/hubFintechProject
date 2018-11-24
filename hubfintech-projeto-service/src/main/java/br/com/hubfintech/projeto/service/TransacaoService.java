package br.com.hubfintech.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.hubfintech.projeto.repository.TransacaoRepository;


@Service
@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
public class TransacaoService {
 
	@Autowired 
    private TransacaoRepository repository;
	
}
