package br.com.matheus.Person.Controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.Person.Repositories.UserRepository;
import br.com.matheus.Person.Security.JWT.JwtTokenProvider;
import br.com.matheus.Person.Util.MediaType;
import br.com.matheus.Person.Vo1.Security.AccountCredentialsVO;
import br.com.matheus.Person.Vo1.Security.TokenVO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	//@ApiOperation(value = "Autentica um usuário por suas credenciais")
	@PostMapping(value = "/signin",
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public ResponseEntity signin (@RequestBody AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = userRepository.findByUsername(username);
			
			TokenVO token = new TokenVO();
			
			if(user != null) {
				token = tokenProvider.createAccessToken(username, user.getRoles());
			}
			else {
				throw new UsernameNotFoundException("Username" + username + " não encontrado");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		}
		catch(AuthenticationException ex) {
			throw new BadCredentialsException("Usuário/senha fornecidos são inválidos");
		}
	}
}
