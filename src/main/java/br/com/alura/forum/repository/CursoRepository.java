package br.com.alura.forum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.forum.modelo.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);

}
