package br.com.hubfintech.projeto.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hubfintech.projeto.entity.Conta;

@Repository
public interface ContaRepository extends PagingAndSortingRepository<Conta, Long> {

	@Query("SELECT conta "
			+ " FROM Conta conta "
			+ " JOIN conta.statusConta statusConta "	
			+ " JOIN conta.pessoa pessoa "
			+ " LEFT JOIN conta.contaPai contaPai "
			+ " WHERE conta.numeroConta LIKE %:conta% ")
	public Page<Conta> findByNumeroConta(@Param("conta") String numeroConta, Pageable pageable);

	@Query("SELECT conta "
			+ " FROM Conta conta "
			+ " WHERE conta.statusConta.id in (:listaIds)")
	public List<Conta> findItensModal(@Param("listaIds") List<Long> ids);
 
	public Conta findByNumeroConta(String numeroConta);

	@Query(nativeQuery=true,
			value=" SELECT group_concat(@id := ( " 
	         + " SELECT DISTINCT ID "
	         + " FROM    TB_CONTA "
	         + " WHERE   ID_CONTA_PAI = @id )) AS comment "
	         + " FROM    ( SELECT  @id := :idConta ) vars "
			 + " STRAIGHT_JOIN "
			 + " TB_CONTA "
			 + " WHERE @id IS NOT NULL ")
	public List<Long> findContasFilhasById(@Param("idConta") Long idContaRemetente);
	
} 
