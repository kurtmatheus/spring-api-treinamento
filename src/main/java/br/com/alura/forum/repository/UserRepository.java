package br.com.alura.forum.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.alura.forum.modelo.Usuario;

public interface UserRepository extends PagingAndSortingRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String username);

}
