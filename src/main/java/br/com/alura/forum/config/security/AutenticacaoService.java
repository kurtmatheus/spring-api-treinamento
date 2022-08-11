package br.com.alura.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UserRepository;

@Profile(value = {"prod","test"})
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = userRepository.findByEmail(username);
		if (optional.isPresent()) {
			return optional.get();
		}	
		throw new UsernameNotFoundException("Dados Inválidos!");
	}

}
