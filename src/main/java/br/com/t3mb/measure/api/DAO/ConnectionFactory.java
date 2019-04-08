package br.com.t3mb.measure.api.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	
	public static Connection getConexao(String base) throws SQLException {
		if (base.equals("t3mb_measure")){
			try {    
                Class.forName("org.postgresql.Driver");    
                return DriverManager.getConnection("jdbc:postgresql://192.168.0.130:5432/t3mb-measure","postgres","alomundo");    
	        }    
	        catch (ClassNotFoundException e) {    
                throw new SQLException(e.getMessage());    
	        }
        }
        if (base.equals("helpdesk")){
			try {    
                Class.forName("org.postgresql.Driver");    
                return DriverManager.getConnection("jdbc:postgresql://177.12.172.20:5432/clicprovida3","clicprovida3","a0l7u1q0");    
	        }    
	        catch (ClassNotFoundException e) {    
                throw new SQLException(e.getMessage());    
	        }
        }
        
        if (base.equals("clinic")){
        	try {    
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");    
                return DriverManager.getConnection("jdbc:sqlserver://192.168.0.86;user=risc;password=1");    
	        }    
	        catch (ClassNotFoundException e) {    
                throw new SQLException(e.getMessage());    
	        }
        }
        
        if (base.equals("clinic_teste")){
        	try{
        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");    
                return DriverManager.getConnection("jdbc:sqlserver://192.168.0.100/risc;user=risc;password=1"); 
        	}
        	catch(ClassNotFoundException e){
        		throw new SQLException(e.getMessage());
        	}
        }
        
        if (base.equals("tasy_teste")){
        	try{
        		Class.forName("oracle.jdbc.driver.OracleDriver");    
                return DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.208:1521:dbteste", "tasy", "aloisk"); 
        	}
        	catch(ClassNotFoundException e){
        		throw new SQLException(e.getMessage());
        	}
        }
        
        if (base.equals("tasy_prod")){
        	try{
        		Class.forName("oracle.jdbc.driver.OracleDriver");    
                return DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)"
                		+ "(HOST = racscan.provida.local)(PORT = 1521))) (CONNECT_DATA = (SERVICE_NAME = dbprod)))", "tasy", "aloisk"); 
        	}
        	catch(ClassNotFoundException e){
        		throw new SQLException(e.getMessage());
        	}
        }
        
        if (base.equals("bi")){
			try {    
                Class.forName("org.postgresql.Driver");    
                return DriverManager.getConnection("jdbc:postgresql://192.168.0.120:5432/provida","postgres","");    
	        }    
	        catch (ClassNotFoundException e) {    
                throw new SQLException(e.getMessage());    
	        }
        }
        
        return null;
	
	}

}
