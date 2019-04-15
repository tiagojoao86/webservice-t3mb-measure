package br.com.t3mb.measure.api.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.t3mb.measure.api.model.Role;
import br.com.t3mb.measure.api.model.User;

@Component
public class RoleDAO {
	
	private Connection connection;
	
	@Autowired
	private SysFunctionDAO sysFunctionDAO;
	private static final Logger log = LoggerFactory.getLogger(RoleDAO.class);
	
	public RoleDAO() {}
	
	public List<Role> getUserRoles(long userId) {
		
		List<Role> result = new ArrayList<Role>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			
			PreparedStatement ps = connection.prepareStatement(""
					+ "select id, name from user_role " 
					+ "where id in (select id_role from user_roles where id_user = ?)");
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new Role(rs.getLong("id"), sysFunctionDAO.getRoleFunctions(rs.getLong("id")), rs.getString("name")));
			}
			
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle) {
			logHandler(sqle, 1, "listar papeis");
		}
		
		return result;
	}
	
	public void updateUserRoles(User user) {
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");			
			PreparedStatement ps = connection.prepareStatement(""
					+ "delete from user_roles where id_user = ?");
			ps.setLong(1, user.getId());
			ps.execute();
			ps.close();
			
			
			user.getRoles().forEach(role -> {
				try { 
					PreparedStatement psRoles = connection.prepareStatement(""
							+ "INSERT INTO user_roles (id_user, id_role) "
							+ "VALUES (? ,?)");
					
					psRoles.setLong(1, user.getId());
					psRoles.setLong(2, role.getId());
					psRoles.execute();
					psRoles.close();
				}
				catch(SQLException sqle) {
					logHandler(sqle, 1, "atualizar papeis do usuário");
				}
			});
			connection.close();
		}
		catch(SQLException sqle) {
			logHandler(sqle, 1, "atualizar papeis do usuário");
		}
		
	}
	
	public void insertUserRoles(User user) {
		
		user.getRoles().forEach(role -> {
			try { 
				connection = ConnectionFactory.getConexao("t3mb_measure");	
				PreparedStatement psRoles = connection.prepareStatement(""
						+ "INSERT INTO user_roles (id_user, id_role) "
						+ "VALUES (? ,?)");
				
				psRoles.setLong(1, user.getId());
				psRoles.setLong(2, role.getId());
				psRoles.execute();
				psRoles.close();
				connection.close();
			}
			catch(SQLException sqle) {
				logHandler(sqle, 1, "atualizar papeis do usuário");
			}
		});
		
		
		
	}
	
	public String logHandler(Exception e, int type, String message) {
		String error = "";
		log.info("------------------------------------------------------------------------");
		if (type == 1) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				error += stackTrace[i].toString()+"\n";
			}			
			log.error("Erro de Persistencia ao "+message+" {}", error);			
		}
		if (type == 2) {
			log.info(message);
		}
		
		return error;
	}

}
