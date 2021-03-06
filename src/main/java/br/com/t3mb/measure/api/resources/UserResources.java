package br.com.t3mb.measure.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.service.UserService;
import br.com.t3mb.measure.api.utils.Response;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserResources {
	
	private static final Logger log = LoggerFactory.getLogger(UserResources.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	@PreAuthorize("hasAnyRole('Administrador')")
	public ResponseEntity<Response<List<User>>> listUsers() {
		Response<List<User>> response = new Response<List<User>>();
		response.setData(userService.listUsers());
		return new ResponseEntity<Response<List<User>>>(response,HttpStatus.OK); 
	}
	
	@GetMapping(value = "/active")
	@PreAuthorize("hasAnyRole('Administrador')")
	public ResponseEntity<Response<List<User>>> listActiveUsers() {
		Response<List<User>> response = new Response<List<User>>();
		response.setData(userService.listActiveUsers());
		return new ResponseEntity<Response<List<User>>>(response,HttpStatus.OK); 
	}
	
	@GetMapping(value = "/superiors")
	@PreAuthorize("hasAnyRole('Administrador')")
	public ResponseEntity<Response<List<User>>> listSuperiorsUsers() {
		Response<List<User>> response = new Response<List<User>>();
		response.setData(userService.listSuperiorsUsers());
		return new ResponseEntity<Response<List<User>>>(response,HttpStatus.OK); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<User>> getUser(@PathVariable("id") long id) {
		Response<User> response = new Response<User>();
		User user = userService.getUser(id);
		response.setData(user);
		
		if (user == null) {
			return new ResponseEntity<Response<User>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<User>>(response,HttpStatus.OK);
	}
	
	@GetMapping(value = "/login/{login}")
	public ResponseEntity<Response<User>> getUser(@PathVariable("login") String login) {
		Response<User> response = new Response<User>();
		User user = userService.getUserByLogin(login);
		response.setData(user);
		
		if (user == null) {
			return new ResponseEntity<Response<User>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Response<User>>(response,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Response<User>> createUser(@Valid @RequestBody User user, BindingResult result) {
		Response<User> response = new Response<User>();
		response.setData(user);
		
		if (result.hasErrors()) {
			log.error("Erro validando lançamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		else {
			log.info("Inserindo Usuário {}.", user.toString());			
			userService.createUser(response);
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping(value = "/nextid")
	@PreAuthorize("hasAnyRole('Administrador')")
	public ResponseEntity<Response<Long>> getNextId() {
		Response<Long> response = new Response<Long>();
		response.setData(userService.getNextId());
		return new ResponseEntity<Response<Long>>(response,HttpStatus.OK); 
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('Administrador')")
	public ResponseEntity<Response<User>> updateUser(@RequestBody User user) {
		Response<User> response = new Response<User>();
		response.setData(user);
		response = userService.updateUser(response);
		if (response.getErrors().size() == 0) {
			return ResponseEntity.ok(response);
		}
		else {
			return new ResponseEntity<Response<User>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}

}
