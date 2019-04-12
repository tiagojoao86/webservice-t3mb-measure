package br.com.t3mb.measure.api.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.model.UserGroup;
import br.com.t3mb.measure.api.service.UserGroupService;
import br.com.t3mb.measure.api.utils.Response;

@RestController
@CrossOrigin
@RequestMapping("api/user-groups")
public class UserGroupResources {
	
	private static final Logger log = LoggerFactory.getLogger(UserGroupResources.class);
	
	@Autowired
	private UserGroupService userGroupService;
	
	@GetMapping()
	@PreAuthorize("hasAnyRole('Administrador')")
	public ResponseEntity<Response<List<UserGroup>>> listUserGroups() {
		Response<List<UserGroup>> response = new Response<List<UserGroup>>();
		response.setData(userGroupService.getUserGroups());
		return new ResponseEntity<Response<List<UserGroup>>>(response,HttpStatus.OK); 
	}
	

}
