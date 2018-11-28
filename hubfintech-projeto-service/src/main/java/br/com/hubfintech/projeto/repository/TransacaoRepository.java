package br.com.hubfintech.projeto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hubfintech.projeto.entity.Transacao;

@Repository
public interface TransacaoRepository extends PagingAndSortingRepository<Transacao, Long> {

	@Query("SELECT transacao "
			+ " FROM Transacao transacao "
			+ " Join transacao.contaDestino contaDestino "
			+ "	LEFT JOIN transacao.contaRemetente contaRemetente "
			+ " WHERE contaRemetente.numeroConta LIKE %:numeroConta% "
			+ " OR contaDestino.numeroConta LIKE %:numeroConta% ")
	public Page<Transacao> findByConta(@Param("numeroConta") String numeroConta, Pageable pageable);
		 
} 
