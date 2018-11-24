package br.com.hubfintech.projeto.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.hubfintech.projeto.entity.Transacao;

@Repository
public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, Long> {
		 
} 
