package br.com.matheus.Person.Services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.matheus.Person.Models.User;
import br.com.matheus.Person.Repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService {

	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	private UserRepository userRepository;
			
	public UserServices(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Procurando por 1 usuário pelo nome " + username + "");
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			return user;
		}
		else {
			throw new UsernameNotFoundException("Username " + username + " não encontrado");
		}
	}
}
