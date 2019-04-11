package br.com.t3mb.measure.api.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.t3mb.measure.api.model.SysFunction;

@Component
public class SysFunctionDAO {
	
	private Connection connection;
	
	public SysFunctionDAO() {}
	
	public List<SysFunction> getRoleFunctions(long roleId) {
		List<SysFunction> result = new ArrayList<SysFunction>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			
			PreparedStatement ps = connection.prepareStatement(""
					+ "select id, name, url from sysfunction " 
					+ "where id in (select id_sysfunction from role_sysfunctions where id_role = ?)");
			ps.setLong(1, roleId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(new SysFunction(rs.getLong("id"),rs.getString("name"),rs.getString("url"),getSubFunctions(rs.getLong("id"))));				
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
	
	public List<SysFunction> getSubFunctions(long functionId) {
		List<SysFunction> result = new ArrayList<SysFunction>();
		try {
			connection = ConnectionFactory.getConexao("t3mb_measure");
			
			PreparedStatement ps = connection.prepareStatement(""
					+ "select id, name, url from sysfunction " 
					+ "where id in (select id_subfunction from sysfunction_subfunctions where id_sysfunction = ?)");
			ps.setLong(1, functionId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(new SysFunction(rs.getLong("id"),rs.getString("name"),rs.getString("url"),null));
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

}
