package br.com.t3mb.measure.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.service.UserService;
import br.com.t3mb.measure.api.utils.Response;

@RestController
@RequestMapping("api/users")
public class UserResources {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public List<User> listUsers() {
		return userService.listUsers();
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

}
