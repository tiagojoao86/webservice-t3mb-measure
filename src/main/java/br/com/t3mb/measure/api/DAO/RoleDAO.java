package br.com.t3mb.measure.api.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.t3mb.measure.api.model.Role;

@Component
public class RoleDAO {
	
	private Connection connection;
	
	@Autowired
	private SysFunctionDAO sysFunctionDAO;
	
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
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

}
