package pe.com.mapeo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
	
	/*
	private String tipo = "SQLITE";
	private String jdbcClassName = "org.sqlite.JDBC";
	private  String url = "jdbc:sqlite:\\\\OHUERTASP\\Compartido\\Mapeov4.db";
	*/
	private static String tipo = "DB2";
	private static String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
	private static String url = "jdbc:db2://SDB2BPMBFDES01:50000/BFP_NEXO:retrieveMessagesFromServerOnGetMessage=true;";
	private static String user = "usr_nexo";
	private static String password = "usr_nexo";
	
	public static Connection getConexion(){
		Connection c = null;
	    try {
	    	Class.forName(jdbcClassName);
	    	if(tipo.equals("SQLITE")){
	    		c = DriverManager.getConnection(url);
	    	}
	    	if(tipo.equals("DB2")){
	    		c = DriverManager.getConnection(url, user, password);
	    	}
	    	System.out.println("Conectado a 'Mapeov4' correctamente en : "+url);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	    return c;
	}

}
