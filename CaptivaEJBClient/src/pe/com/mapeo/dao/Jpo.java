package pe.com.mapeo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

//import com.google.gson.Gson;

public class Jpo {
	/*
	private String tipo = "SQLITE";
	private String jdbcClassName = "org.sqlite.JDBC";
	private  String url = "jdbc:sqlite:\\\\OHUERTASP\\Compartido\\Mapeov4.db";
	*/
	private String tipo = "POSTGRESQL";
	private String jdbcClassName = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/Captiva";
	private String user = "postgres";
	private String password = "admin";
	
	/* ESQUEMA DEFECTO - Vacio no se adiciona*/
	// private String esquema = "";
	private String esquema = "soul";
	
	private LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
	private LinkedHashMap<String,Object> dataTipo = new LinkedHashMap<String,Object>();
	private LinkedHashMap<String,Object> dataMultiple = new LinkedHashMap<String,Object>();
	
	private Connection c = null;
	
	public Connection getConexion(boolean autoCommit){
	    try {
	    	Class.forName(jdbcClassName);
	    	if(tipo.equals("SQLITE")){
	    		c = DriverManager.getConnection(url);
	    	}
	    	if(tipo.equals("DB2")){
	    		c = DriverManager.getConnection(url, user, password);
	    	}
	    	if(tipo.equals("POSTGRESQL")){
	    		c = DriverManager.getConnection(url, user, password);
	    	}
	    	System.out.println("Conectado a 'Mapeov4' correctamente en : "+url);

	    	if(tipo.equals("POSTGRESQL")){
	    		c.setAutoCommit(false);
	    	} else {
	    		c.setAutoCommit(autoCommit);
	    	}
		} catch ( Exception e ) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.err.println("ERROR AL CONECTARSE A LA BASE DE DATOS :");
			e.printStackTrace();
		}
	    return c;
	}
	
	public void autoCommit(boolean autoCommit) throws SQLException{
		if(c!=null){
			c.setAutoCommit(autoCommit);
		}
	}
	
	private void instanciarJpo(HttpServletRequest request,boolean autoCommit) throws Exception {
		
		getConexion(autoCommit);
		
		try {
			Enumeration keys = request.getParameterNames();  
			while(keys.hasMoreElements()){  
				String llave = (String)keys.nextElement();
				String value = new String(request.getParameter(llave).getBytes("ISO-8859-1"), "UTF-8");

				///System.out.println(" - llave = |"+llave+"| , value= |"+value+"|");
				
				String[] valores = llave.split("_");
				
				// "EJE_F_CODIGO_SOLICITUD"
				if(valores.length>=3 && valores[0].length()==3 && valores[1].length()==1){
					//System.out.println("   --> Es Referencia y Tipo");
					
					String Referencia = valores[0];
					String Tipo = valores[1];
					String llaveOriginal = llave.substring(6);
					
					if(Tipo.equals("M")){// "EJE_M_23_Codigo
						
						String[] Indices = llaveOriginal.split("_");
						
						String indice = Indices[0];
						llaveOriginal = llaveOriginal.substring((Indices[0].length()+1));
						
						LinkedHashMap<String,Object> listaIndices = null;
						if(dataMultiple.get(Referencia) == null){
							listaIndices = new LinkedHashMap<String,Object>();
						} else {
							listaIndices = (LinkedHashMap<String, Object>) dataMultiple.get(Referencia);
						}
						
						LinkedHashMap<String,String> listaParametros = null;
						if(listaIndices.get(indice) == null){
							listaParametros = new LinkedHashMap<String,String>();
						} else {
							listaParametros = (LinkedHashMap<String, String>) listaIndices.get(indice);
						}
						
						listaParametros.put(llaveOriginal, value);
						listaIndices.put(indice, listaParametros);
						
						dataMultiple.put(Referencia, listaIndices);
						
					} else {
						LinkedHashMap<String,Object> listaTipos = null;
						if(dataTipo.get(Referencia) == null){
							listaTipos = new LinkedHashMap<String,Object>();
						} else {
							listaTipos = (LinkedHashMap<String, Object>) dataTipo.get(Referencia);
						}
						
						LinkedHashMap<String,String> listaParametros = null;
						if(listaTipos.get(Tipo) == null){
							listaParametros = new LinkedHashMap<String,String>();
						} else {
							listaParametros = (LinkedHashMap<String, String>) listaTipos.get(Tipo);
						}
						listaParametros.put(llaveOriginal, value);
						listaTipos.put(Tipo, listaParametros);
						dataTipo.put(Referencia, listaTipos);
					}
					
				// "EJE_CODIGO_SOLICITUD"
				} else if(valores.length>=2 && valores[0].length()==3){
					//System.out.println("   --> Es Referencia");
					String Referencia = valores[0];
					String llaveOriginal = llave.substring(4);
					//System.out.println("   --> |"+Referencia+"|"+llaveOriginal+"|");
					LinkedHashMap<String,String> listaParametros = null;
					if(data.get(Referencia) == null){
						listaParametros = new LinkedHashMap<String,String>();
					} else {
						listaParametros = (LinkedHashMap<String, String>) data.get(Referencia);
					}
					listaParametros.put(llaveOriginal, value);
					data.put(Referencia, listaParametros);
				}
				
			}
			/*
			Gson json = new Gson();
			
			System.out.println("INICIA JPO");
			
			System.out.println("\n\n");
			System.out.println(json.toJson(data));
			System.out.println(json.toJson(dataTipo));
			System.out.println(json.toJson(dataMultiple));
			*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error parsear Data");
		}
	}
	
	public Jpo(HttpServletRequest request) throws Exception {
		instanciarJpo(request,false);
	}
	
	public Jpo(HttpServletRequest request,boolean autoCommit) throws Exception {
		instanciarJpo(request,autoCommit);
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
	public Tabla tabla(String nombreTabla,String Referencia){
		String esquemaPre = ((esquema.equals(""))?"":esquema+".");
		return new Tabla(c,esquemaPre+nombreTabla,data.get(Referencia),dataMultiple.get(Referencia),dataTipo.get(Referencia));
	}
	
	public Tabla tabla(String nombreTabla){
		String esquemaPre = ((esquema.equals(""))?"":esquema+".");
		return new Tabla(c,esquemaPre+nombreTabla,null,null,null);
	}
	
	/* CC01 - CONSULTA JOIN */
	public Tablas tablas(String[] nombreTablas,String[] referencias){
		
		String esquemaPre = ((esquema.equals(""))?"":esquema+".");
		
		Object[] datoTipos = new Object[nombreTablas.length];
		
		for(int i = 0;i<nombreTablas.length;i++){
			nombreTablas[i] = esquemaPre+nombreTablas[i];
			String referencia = referencias[i];
			datoTipos[i] = dataTipo.get(referencia);
		}
		
		return new Tablas(c,nombreTablas,referencias,datoTipos);
	}
	
	public Jpo commitear() throws SQLException{
		if(c != null){
			if(!c.getAutoCommit()){
				c.commit();
			}
		}
		return this;
	}
	
	public void finalizar() throws SQLException{
		if(c != null){
			if(!c.isClosed()){
				c.close();
				System.out.println("Desconectado corréctamente");
			}
		}
	}
	
}