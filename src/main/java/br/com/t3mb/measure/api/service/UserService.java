package br.com.t3mb.measure.api.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t3mb.measure.api.DAO.UserDAO;
import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.utils.Response;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public List<User> listUsers() {
		return userDAO.listUsers();
	}
	
	public List<User> listActiveUsers() {
		return userDAO.listActiveUsers();
	}
	
	public List<User> listSuperiorsUsers() {
		return userDAO.listSuperiorsUsers();
	}

	public User getUser(long id) {
		return userDAO.getUser(id);
	}

	public User getUserByLogin(String username) {
		return userDAO.getUserByLogin(username);
	}

	public Response<User> createUser(@Valid User user) {
		return userDAO.createUser(user);
	}

}
