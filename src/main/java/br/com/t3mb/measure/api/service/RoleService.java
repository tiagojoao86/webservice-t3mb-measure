package br.com.t3mb.measure.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t3mb.measure.api.DAO.RoleDAO;
import br.com.t3mb.measure.api.model.Role;
import br.com.t3mb.measure.api.model.User;

@Service
public class RoleService {
	
	@Autowired
	RoleDAO roleDAO;
	
	public List<Role> getUserRoles(long userId) {
		return roleDAO.getUserRoles(userId);
	}
	
	public void insertUserRoles(User user) {
		roleDAO.insertUserRoles(user);
	}
	
	public void updateUserRoles(User user) {
		roleDAO.updateUserRoles(user);
	}

}
