package br.com.t3mb.measure.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t3mb.measure.api.DAO.UserDAO;
import br.com.t3mb.measure.api.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public List<User> listUsers() {
		return userDAO.listUsers();
	}

	public User getUser(long id) {
		return userDAO.getUser(id);
	}

}
