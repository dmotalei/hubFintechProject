package br.com.hubfintech.projeto.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.hubfintech.projeto.entity.Pessoa;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {

	public Page<Pessoa> findByCpfCnpjContainingIgnoreCase(String cpfCnpj, Pageable pageable);

	public Pessoa findByCpfCnpj(String cpfCnpj);

} 
