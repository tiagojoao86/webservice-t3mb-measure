package br.com.t3mb.measure.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t3mb.measure.api.DAO.UserGroupDAO;
import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.model.UserGroup;

@Service
public class UserGroupService {
	
	@Autowired
	UserGroupDAO userGroupDAO;
	
	public List<UserGroup> getUserGroups() {
		return userGroupDAO.getUserGroups();
	}	

}
