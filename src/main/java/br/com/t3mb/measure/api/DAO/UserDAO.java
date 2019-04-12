package br.com.t3mb.measure.api.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.model.UserGroup;
import br.com.t3mb.measure.api.service.RoleService;
import br.com.t3mb.measure.api.utils.Response;
@Component
public class UserDAO {
	
	private Connection connection;
	
	@Autowired
	private RoleService roleService;
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	
	
	public UserDAO() {
	}

	public List<User> listUsers() {
		List<User> result  = new ArrayList<User>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			PreparedStatement ps = connection.prepareStatement(""
					+ "select a.id uid, a.name uname, a.login, a.password, a.hassuperior, a.status, " + 
					"b.id sid, b.name sname, " + 
					"c.id gid, c.name gname " + 
					"from measure_user a " + 
					"left join measure_user b on a.superior_id = b.id " + 
					"inner join user_group c on a.usergroup_id = c. id ");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(new User(
						rs.getLong("uid"), 
						rs.getString("uname"), 
						rs.getString("login"), 
						rs.getString("password"), 
						roleService.getUserRoles(rs.getLong("uid")),
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status")));
			}
			
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle) {			
			sqle.printStackTrace();			
		}
		
		
		return result;
	}
	
	public List<User> listActiveUsers() {
		List<User> result  = new ArrayList<User>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			PreparedStatement ps = connection.prepareStatement(""
					+ "select a.id uid, a.name uname, a.login, a.password, a.hassuperior, a.status, " + 
					"b.id sid, b.name sname, " + 
					"c.id gid, c.name gname " + 
					"from measure_user a " + 
					"left join measure_user b on a.superior_id = b.id " + 
					"inner join user_group c on a.usergroup_id = c. id "
					+ "where a.status = ?");
			
			ps.setString(1, "A");
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				result.add(new User(
						rs.getLong("uid"), 
						rs.getString("uname"), 
						rs.getString("login"), 
						rs.getString("password"), 
						roleService.getUserRoles(rs.getLong("uid")),
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status")));
			}
			
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle) {			
			sqle.printStackTrace();			
		}
		
		
		return result;
	}
	
	public List<User> listSuperiorsUsers() {
		List<User> result  = new ArrayList<User>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			PreparedStatement ps = connection.prepareStatement(""
					+ "select a.id uid, a.name uname, a.login, a.password, a.hassuperior, a.status, " + 
					"b.id sid, b.name sname, " + 
					"c.id gid, c.name gname " + 
					"from measure_user a " + 
					"left join measure_user b on a.superior_id = b.id " + 
					"inner join user_group c on a.usergroup_id = c. id "
					+ "where a.id in (select id_user from user_roles where id_role = 2)");
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				result.add(new User(
						rs.getLong("uid"), 
						rs.getString("uname"), 
						rs.getString("login"), 
						rs.getString("password"), 
						roleService.getUserRoles(rs.getLong("uid")),
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status")));
			}
			
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle) {			
			sqle.printStackTrace();			
		}
		
		
		return result;
	}

	public User getUser(long id) {
		User result  = null;
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			PreparedStatement ps = connection.prepareStatement(""
					+ "select a.id uid, a.name uname, a.login, a.password, a.hassuperior, a.status, " + 
					"b.id sid, b.name sname, " + 
					"c.id gid, c.name gname " + 
					"from measure_user a " + 
					"left join measure_user b on a.superior_id = b.id " + 
					"inner join user_group c on a.usergroup_id = c. id "
					+ "where "
					+ "a.id = ?");
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next()) {
				result = new User(
						rs.getLong("uid"), 
						rs.getString("uname"), 
						rs.getString("login"), 
						rs.getString("password"), 
						roleService.getUserRoles(rs.getLong("uid")),
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status"));
			}
			
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	public User getUserByLogin(String username) {
		User result  = null;
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			PreparedStatement ps = connection.prepareStatement(""
					+ "select a.id uid, a.name uname, a.login, a.password, a.hassuperior, a.status, " + 
					"b.id sid, b.name sname, " + 
					"c.id gid, c.name gname " + 
					"from measure_user a " + 
					"left join measure_user b on a.superior_id = b.id " + 
					"inner join user_group c on a.usergroup_id = c. id "
					+ "where "
					+ "a.login = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				result = new User(
						rs.getLong("uid"), 
						rs.getString("uname"), 
						rs.getString("login"), 
						rs.getString("password"), 
						roleService.getUserRoles(rs.getLong("uid")),
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status"));			}
			
			rs.close();
			ps.close();
			connection.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
	}

	public Response<User> createUser(@Valid User user) {
		Response<User> response = new Response<User>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			PreparedStatement ps = connection.prepareStatement(""
					+ "INSERT INTO measure_user (id, name, login, password, usergroup_id, superior_id, hassuperior, status) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setLong(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ps.setLong(5, user.getUserGroup().getId());
			ps.setLong(6, user.getSuperior().getId());
			ps.setBoolean(7, user.isHasSuperior());
			ps.setString(8, user.getStatus());
			ps.execute();			
			ps.close();
			
			connection.close();
			
			roleService.insertUserRoles(user);
			
			logHandler(null, 2, "Usuário "+user.toString()+" criado com sucesso");
			
		}
		catch (SQLException sqle) {
			logHandler(sqle, 1, "criar");
		}
		return response;
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
