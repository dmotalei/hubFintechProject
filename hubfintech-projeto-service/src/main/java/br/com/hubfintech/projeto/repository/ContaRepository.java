package br.com.hubfintech.projeto.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.hubfintech.projeto.entity.Conta;

@Repository
public interface ContaRepository extends PagingAndSortingRepository<Conta, Long> {
		 
} 
