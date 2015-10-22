package pe.com.mapeo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConexionBD {
	
	private Connection c = null;
	
	public ConexionBD(Connection c) {
		this.c = c;
	}
	
	public Connection getConexion(){
		return this.c;
	}
	
	public boolean dml(String query) throws Exception { // Sirve para registrar, editar, eliminar
		System.out.println(query);
		Statement stmt = null;
		Connection conexion = getConexion();
	    try {
	    	stmt = conexion.createStatement();
	    	StringBuilder Tablas = new StringBuilder();
	    	Tablas.append(query);
	    	stmt.executeUpdate(Tablas.toString());
	    	stmt.close();
	    	//conexion.close();
	    	//System.out.println("dml registrado correctamente");
	    	return true;
	    } catch ( Exception e ) { System.out.println("catch");
	    	conexion.rollback();
	    	conexion.close();
	    	//System.out.println("ERROR QUERY ="+query);
	    	throw new Exception("BD: "+e.getClass().getName()+"="+e.getMessage()); 
	    } finally {
	    	System.out.println("final");
	    	if(stmt != null){
	    		stmt.close();
	    	}
	    }
	}
	
	public boolean dml(String[] querys) throws Exception { // Sirve para registrar, editar, eliminar
		System.out.println(querys);
		Statement stmt = null;
		int i = 0;
		Connection conexion = getConexion();
		try {
	    	stmt = conexion.createStatement();
	    	StringBuilder Tablas = new StringBuilder();
	    	for(i = 0;i<querys.length;i++){
	    		//Tablas.append(querys[i]+";");
	    		stmt.executeUpdate(querys[i]);
	    	}
	    	//System.out.println("|"+Tablas.toString()+"|");
	    	
	    	stmt.close();
	    	//conexion.close();
	    	//System.out.println("dmls registrados correctamente");
	    	return true;
	    } catch ( Exception e ) {
	    	conexion.rollback();
	    	conexion.close();
	    	//System.out.println("ERROR MULTIPLE QUERY");
	    	throw new Exception("BD: "+e.getClass().getName()+"="+e.getMessage()); 
	    } finally {
	    	if(stmt != null){
	    		stmt.close();
	    	}
	    }
	}
	
	public int contarRegistro(String query) throws Exception{
		System.out.println(query);
		int total = 0;
		Connection conexion = getConexion();
		Statement stmt = null;
		ResultSet rs = null;
		try {
	      	stmt = conexion.createStatement();
	      	rs = stmt.executeQuery(query);
	      	while(rs.next()){
				total = rs.getInt("COUNT");
			}
	      	rs.close();
	      	stmt.close();
	      	//conexion.close();
	      	return total;
	    } catch ( Exception e ) {
	    	conexion.rollback();
	    	conexion.close();
	    	//System.out.println("ERROR QUERY ="+query);
	    	throw new Exception("BD: "+e.getClass().getName()+"="+e.getMessage());
	    } finally {
	    	if(rs != null){
	    		rs.close();
	    	}
	    	if(stmt != null){
	    		stmt.close();
	    	}
	    }
	}
	
	public Map<String, Object> obtener(String query) throws Exception{
		System.out.println(query);
		Map<String, Object> detalle = new LinkedHashMap<String, Object>();
		Connection conexion = getConexion();
      	Statement stmt = null;
      	ResultSet rs = null;
		try {
			stmt = conexion.createStatement();
	      	rs = stmt.executeQuery(query);
	      	ResultSetMetaData metaData = rs.getMetaData();
	      	int columnCount = metaData.getColumnCount();
			while(rs.next()){
			    for (int i = 1; i <= columnCount; i++) {
			    	Object valor = new Object();
			    	if(metaData.getColumnTypeName(i).equals("CHARACTER") && rs.getObject(i) !=null){
			    		if(rs.getObject(i).toString().equals("1")){
			    			valor = true;
			    		} else {
			    			valor = false;
			    		}
			    	} else {
			    		valor = rs.getObject(i);
			    	}
			    	detalle.put(metaData.getColumnLabel(i), valor);
			    }
			    break;
			}
			rs.close();
			stmt.close();
			return detalle;
	    } catch ( Exception e ) {
	    	conexion.rollback();
	    	conexion.close();
	    	//System.out.println("ERROR QUERY ="+query);
	    	throw new Exception("BD: "+e.getClass().getName()+"="+e.getMessage()); 
	    } finally {
	    	if(rs != null){
	    		rs.close();
	    	}
	    	if(stmt != null){
	    		stmt.close();
	    	}
	    }
	    
	}

	public List<Map<String, Object>> seleccionar(String query) throws Exception{
		System.out.println(query);
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Connection conexion = getConexion();
      	Statement stmt = null;
      	ResultSet rs = null;
		try {
			stmt = conexion.createStatement();

	      	rs = stmt.executeQuery(query);
	      	ResultSetMetaData metaData = rs.getMetaData();
	      	int columnCount = metaData.getColumnCount();
	      	
			while(rs.next()){
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
			    for (int i = 1; i <= columnCount; i++) {
			    	Object valor = new Object();
			    	if(metaData.getColumnTypeName(i).equals("CHARACTER") && rs.getObject(i) !=null){
			    		if(rs.getObject(i).toString().equals("1")){
			    			valor = true;
			    		} else {
			    			valor = false;
			    		}
			    	} else {
			    		valor = rs.getObject(i);
			    	}
			    	if(valor != null){
			    		columns.put(metaData.getColumnLabel(i), valor);
			    	}
			    	
			    }
			    rows.add(columns);
			}
		  rs.close();
	      stmt.close();
	      return rows;
	    } catch ( Exception e ) {
	    	conexion.rollback();
	    	conexion.close();
	    	//System.out.println("ERROR QUERY ="+query);
	    	throw new Exception("BD: "+e.getClass().getName()+"="+e.getMessage()); 
	    } finally {
	    	if(rs != null){
	    		rs.close();
	    	}
	    	if(stmt != null){
	    		stmt.close();
	    	}
	    }
	}
	
}