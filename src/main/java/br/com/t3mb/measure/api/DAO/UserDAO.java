package br.com.t3mb.measure.api.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.t3mb.measure.api.model.User;
import br.com.t3mb.measure.api.model.UserGroup;
@Component
public class UserDAO {
	
	private Connection connection;
	
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
						null, 
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status")));
			}
			
			rs.close();
			ps.close();
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
						null, 
						new UserGroup(rs.getLong("gid"), rs.getString("gname")), 
						new User(rs.getLong("sid"), rs.getString("sname")), 
						rs.getBoolean("hassuperior"), 
						rs.getString("status"));
			}
			
			rs.close();
			ps.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}
	
	

}
