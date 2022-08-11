package br.com.alura.forum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends PagingAndSortingRepository<Topico, Long>{

}
