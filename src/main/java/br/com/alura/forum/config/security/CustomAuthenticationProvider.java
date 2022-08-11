package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Profile(value = {"prod","test"})
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AutenticacaoService authService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
            String username = authentication.getPrincipal().toString();

            UserDetails user = authService.loadUserByUsername(username); 
            
            if (user == null) {                                    
                throw new AuthenticationCredentialsNotFoundException(username);
            }
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
