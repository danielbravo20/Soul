package pe.com.mapeo.generar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import pe.com.mapeo.dao.ConexionBD;
import pe.com.mapeo.util.MapeoUtil;

public class generarDLL extends ConexionBD {
	
	private static void cargarTablas(){
		
		Statement stmt = null;
	    try {
	      Connection conexion = getConexion();
	      stmt = conexion.createStatement();
	      
	      StringBuilder Tablas = new StringBuilder();
	      String sql = "";
	      
	      /*
			      sql = "CREATE TABLE Usuario (" +
						"		codigoUsuario VARCHAR(50) PRIMARY KEY   NOT NULL," +
						"		clave		  VARCHAR(100)              NOT NULL," +
						"		nombre        VARCHAR(100)              NOT NULL," + 
						"		perfil        VARCHAR(50)             	NOT NULL," + 
						"		descripcion   VARCHAR(250) " + 
						");";
			      Tablas.append(sql);
	      
	      sql = "CREATE TABLE Proyecto (" +
				"		codigoProyecto VARCHAR(50) PRIMARY KEY 	NOT NULL," +
				"		nombre VARCHAR(100) 					NOT NULL," +
				"		descripcion VARCHAR(255)," +
				"		estado CHARACTER(1)," +
				"		fechaCreacion DATETIME," +
				"		fechaFinalizacion DATETIME" +
				");";
	      Tablas.append(sql);
	      
	      sql = "CREATE TABLE Version (" +
				"		codigoProyecto VARCHAR(50)  	NOT NULL," +
				"		numeroVersion VARCHAR(10)    	NOT NULL," +
				"		estado CHARACTER(1)," +
				"		tiempoEstimado INTEGER," +
				"		fechaCreacion DATETIME," +
				"		fechaFinalizacion DATETIME," +
				"		PRIMARY KEY(codigoProyecto,numeroVersion)" +
				");";
	      Tablas.append(sql);
	    
		  sql = "CREATE TABLE Mantenimiento (" +
				"		codigoProyecto VARCHAR(50)  	NOT NULL," +
				"		numeroVersion VARCHAR(10)    	NOT NULL," +
				"		codigoMantenimiento VARCHAR(50) NOT NULL," +
				"		nombre VARCHAR(100)             NOT NULL," +
				"		descripcion VARCHAR(255)," +
				"		codigoEsquema VARCHAR(50)," +
				"		codigoDataSource VARCHAR(50)," +
				"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoMantenimiento)" +
				");";
		  Tablas.append(sql);
		  
		  sql = "CREATE TABLE MantenimientoAtributo (" +
				"		codigoProyecto VARCHAR(50)  	NOT NULL," +
				"		numeroVersion VARCHAR(10)    	NOT NULL," +
				"		codigoMantenimiento VARCHAR(50) NOT NULL," +
				"		codigoAtributo INTEGER          NOT NULL," +
				"		nombre VARCHAR(50)              NOT NULL," +
				"		tipoDato VARCHAR(50)            NOT NULL," +
				"		longitud INTEGER," +
				"		precision INTEGER," +
				"		esLlavePrimaria CHARACTER(1)," +
				"		esListado CHARACTER(1)," +
				"		esBusqueda CHARACTER(1)," +
				"		esObligatorio CHARACTER(1)," +
				"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoMantenimiento,codigoAtributo)" +
				");";
		  Tablas.append(sql);
		  
				  sql = "CREATE TABLE Esquema (" +
						"		codigoProyecto VARCHAR(50)  	NOT NULL," +
						"		codigoEsquema VARCHAR(50)    NOT NULL," +
						"		descripcion VARCHAR(255)        NOT NULL," +
						"		estado CHARACTER(1)," +
						"		PRIMARY KEY(codigoProyecto,codigoEsquema)" +
						");";
				  Tablas.append(sql);
				  
				  sql = "CREATE TABLE DataSource (" +
						"		codigoProyecto VARCHAR(50)  	NOT NULL," +
						"		codigoDataSource VARCHAR(50)    NOT NULL," +
						"		descripcion VARCHAR(255)        NOT NULL," +
						"		estado CHARACTER(1)," +
						"		PRIMARY KEY(codigoProyecto,codigoDataSource)" +
						");";
				  Tablas.append(sql);
				  
				  sql = "CREATE TABLE Rol (" +
						"		codigoProyecto VARCHAR(50)  	NOT NULL," +
						"		codigoRol VARCHAR(100)    		NOT NULL," +
						"		descripcion VARCHAR(255)        NOT NULL," +
						"		estado CHARACTER(1)," +
						"		PRIMARY KEY(codigoProyecto,codigoRol)" +
						");";
			      Tablas.append(sql);
			      
			      sql = "CREATE TABLE UnidadNegocio (" +
					"		codigoUnidadNegocio VARCHAR(50)            NOT NULL," +
					"		descripcion   		VARCHAR(250)                   ," + 
					"		PRIMARY KEY(codigoUnidadNegocio)" + 
					");";
			      Tablas.append(sql);
	     
		  sql = "CREATE TABLE Clase (" +
				"		codigoProyecto VARCHAR(50)  	NOT NULL," +
				"		numeroVersion VARCHAR(10)       NOT NULL," +
				"		codigoClase VARCHAR(100)        NOT NULL," +
				"		java_esEntidad CHARACTER(1)     NOT NULL," +
				"		java_entidad VARCHAR(100)       NOT NULL," +
				"		proceso_esEntidad CHARACTER(1)     		," +
				"		proceso_entidad VARCHAR(100)       		," +
				"		bds_esEntidad CHARACTER(1)     			," +
				"		bds_entidad VARCHAR(100)       			," +
				"		descripcion VARCHAR(255)," +
				"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoClase)" +
				");";
		  Tablas.append(sql);
		  
	      
		  sql = "CREATE TABLE Atributo (" +
				"		codigoProyecto VARCHAR(50) NOT NULL," +
				"		numeroVersion VARCHAR(10) NOT NULL," +
				"		codigoClase VARCHAR(100) NOT NULL," +
				"		codigoAtributo VARCHAR(100) NOT NULL," +
				"		descripcion VARCHAR(255) NOT NULL," +
				"		tipoDato VARCHAR(50) NOT NULL," +
				"		esTipoDatoEntidad CHARACTER(1)," +
				"		java_esAtributo CHARACTER(1)," +
				"		java_atributo VARCHAR(100)," +
				"		java_cambioTipoDato CHARACTER(1)," +
				"		java_tipoDato VARCHAR(50)," +
				"		java_esLista CHARACTER(1)," +
				"		web_esAtributo CHARACTER(1)," +
				"		web_atributo VARCHAR(100)," +
				"		web_formato VARCHAR(50)," +
				"		proceso_esAtributo CHARACTER(1)," +
				"		proceso_atributo VARCHAR(100)," +
				"		proceso_cambioTipoDato CHARACTER(1)," +
				"		proceso_tipoDato VARCHAR(50)," +
				"		bds_esAtributo CHARACTER(1)," +
				"		bds_atributo VARCHAR(100)," +
				"		bds_cambioTipoDato CHARACTER(1)," +
				"		bds_tipoDato VARCHAR(100)," +
				"		bds_longitud INTEGER," +
				"		bds_precision INTEGER," +
				"		bds_esPK CHARACTER(1)," +
				"		bds_esObligatorio CHARACTER(1)," +
				"		bds_valorDefecto VARCHAR(100)," +
				"		bds_tablaReferenciada VARCHAR(100)," +
				"		bds_campoReferenciado VARCHAR(100)," +
				"		bds_relacionUnoMuchos CHARACTER(1)," +
				"		bds_valorxFuncion VARCHAR(255)," +
				"		orden INTEGER," +
				"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoClase,codigoAtributo)" +
				");";
		  Tablas.append(sql);
		  
		  sql = "CREATE TABLE Configuracion (" +
				"		codigoProyecto VARCHAR(50)  			NOT NULL," +
				"		empresa VARCHAR(100)     				NOT NULL," +
				"		producto VARCHAR(50)       				NOT NULL," +
				"		productoDescripcion VARCHAR(100) 		," +
				"		urlPrefijoPaquete VARCHAR(50)           NOT NULL," +
				"		directorioLib VARCHAR(100)           	NOT NULL," +
				"		directorioEJBClient VARCHAR(100)        NOT NULL," +
				"		directorioEJBAuto VARCHAR(100)          NOT NULL," +
				"		directorioWebAuto VARCHAR(100)          NOT NULL," +
				"		PRIMARY KEY(codigoProyecto)" +
				");";
		  Tablas.append(sql); 
	      
	      sql = "CREATE TABLE MantenimientoRoles (" +
				"		codigoProyecto VARCHAR(50)  		NOT NULL," +
				"		numeroVersion VARCHAR(10)  			NOT NULL," +
				"		codigoMantenimiento VARCHAR(50)  	NOT NULL," +
				"		codigoRol VARCHAR(100)    			NOT NULL," +
				"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoMantenimiento,codigoRol)" +
				");";
	      Tablas.append(sql);
    
    
			      sql = "CREATE TABLE Equipo (" +
						"		codigoProyecto VARCHAR(50) NOT NULL," +
						"		codigoUsuario VARCHAR(50)  NOT NULL," +
						"		esResponsable CHARACTER(1)," +
						"		carpetaDestinoWorkspace VARCHAR(255)    NOT NULL," +
						"		carpetaDestinoParcial VARCHAR(255)      NOT NULL," +
						"		PRIMARY KEY(codigoProyecto,codigoUsuario)" + 
						");";
			      Tablas.append(sql);
	      
	      sql = "CREATE TABLE Proceso (" +
	      		"		codigoProyecto 		VARCHAR(50)  			NOT NULL," +
				"		numeroVersion 		VARCHAR(10)  			NOT NULL," +
				"		codigoProceso 		VARCHAR(100)			NOT NULL," +
				"		nombre		  		VARCHAR(100)            NOT NULL," +
				"		codigoDataSource 	VARCHAR(50)             NOT NULL," + 
				"		sufijo       		VARCHAR(50)             NOT NULL," + 
				"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoProceso)" + 
				");";
	      Tablas.append(sql);
	      
	      sql = "CREATE TABLE Tarea (" +
    		"		codigoProyecto 		VARCHAR(50)  			NOT NULL," +
			"		numeroVersion 		VARCHAR(10)  			NOT NULL," +
			"		codigoProceso 		VARCHAR(100)			NOT NULL," +
			"		codigoTarea 		VARCHAR(100)			NOT NULL," +
			"		nombre		  		VARCHAR(100)            NOT NULL," +
			"		codigoDataSource 	VARCHAR(50)             NOT NULL," + 
			"		vencimientoAmarillo INTEGER   						," + 
			"		vencimientoRojo 	INTEGER   						," + 
			"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoProceso,codigoTarea)" + 
			");";
	      Tablas.append(sql);
	     
	      
			      sql = "CREATE TABLE VersionUN (" +
					"		codigoProyecto VARCHAR(50)  		NOT NULL," +
					"		numeroVersion VARCHAR(10)    		NOT NULL," +
					"		codigoUnidadNegocio VARCHAR(50)    	NOT NULL," +
					"		estado CHARACTER(1)," +
					"		tiempoEstimado INTEGER," +
					"		fechaCreacion DATETIME," +
					"		fechaFinalizacion DATETIME," +
					"		PRIMARY KEY(codigoProyecto,numeroVersion,codigoUnidadNegocio)" +
					");";
	       Tablas.append(sql);
     */
	      stmt.executeUpdate(Tablas.toString());
	      stmt.close();
	      conexion.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Tablas Creada Corréctamente");
		
	}
	
	private static void borrarTablas(){
		
		Statement stmt = null;
	    try {
	      Connection conexion = getConexion();
	      stmt = conexion.createStatement();
	      
	      StringBuilder Tablas = new StringBuilder();

	      //Tablas.append("DROP TABLE Usuario ;");
	      //Tablas.append("DROP TABLE Proyecto ;");
	      //Tablas.append("DROP TABLE Clase ;");
	      // Tablas.append("DROP TABLE Configuracion ;");
	      // Tablas.append("DROP TABLE Mantenimiento ;");
	      // Tablas.append("DROP TABLE Rol ;");
	     Tablas.append("DROP TABLE Atributo ;");
		      
	      stmt.executeUpdate(Tablas.toString());
	      stmt.close();
	      conexion.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Tablas Borrada Corréctamente");
		
	}
		
	private static void registrarDatos(){
		
		Statement stmt = null;
	    try {
	      Connection conexion = getConexion();
	      stmt = conexion.createStatement();
	      
	      StringBuilder Tablas = new StringBuilder();

	      //Tablas.append("INSERT INTO Usuario (codigoUsuario,clave,nombre) VALUES ('admin2','"+MapeoUtil.getMD5("admin2")+"','Oscar Huertas');");
	      //Tablas.append("INSERT INTO Proyecto (codigoProyecto,nombre,descripcion,estado,fechaCreacion,fechaFinalizacion) VALUES ('BECartaFianza','BANCA EMPRESA - CARTA FIANZA',null,'1','2015-01-14 11:08:00','2015-01-14 11:09:00');");
	      //Tablas.append("INSERT INTO Proyecto (codigoProyecto,nombre,descripcion,estado,fechaCreacion,fechaFinalizacion) VALUES ('BEPropuestaCredito','BANCA EMPRESA - PROPUESTA DE CRÉDITO','PROCESO PROPUESTA DE CRÉDITO','0','2015-01-14 11:01:00','2015-01-14 11:00:20');");
	      Tablas.append("INSERT INTO Equipo (codigoProyecto,codigoUsuario,esResponsable) VALUES ('BECartaFianza','OSCHUP','1');");
	      Tablas.append("INSERT INTO Equipo (codigoProyecto,codigoUsuario,esResponsable) VALUES ('BECartaFianza','EDWREB','1');");
	      // yyyy-MM-dd HH:mm:ss
	      
	      stmt.executeUpdate(Tablas.toString());
	      stmt.close();
	      conexion.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Registros cargados Corréctamente");
		
	}
	
	private static List<Map<String, Object>> listarRegistros(){
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		
		Statement stmt = null;
		try {
	    	Connection conexion = getConexion();
	      	stmt = conexion.createStatement();

	      	ResultSet rs = stmt.executeQuery("SELECT * FROM BFP_CARTA_FIANZA.CONFIGURACION");
	      	ResultSetMetaData metaData = rs.getMetaData();
	      	int columnCount = metaData.getColumnCount();
	      	
			while(rs.next()){
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
			    for (int i = 1; i <= columnCount; i++) {
			    	System.out.print("|"+metaData.getColumnLabel(i)+"|"+rs.getObject(i)+"|"+metaData.getColumnTypeName(i)+"| \t");
			    	
			    	Object valor = new Object();
			    	if(metaData.getColumnTypeName(i).equals("CHARACTER")){
			    		if(rs.getObject(i).toString().equals("1")){
			    			valor = true;
			    		} else {
			    			valor = false;
			    		}
			    	} else {
			    		valor = rs.getObject(i);
			    	}
			    	
			    	columns.put(metaData.getColumnLabel(i), valor);
			    }
			    System.out.println("");
			    rows.add(columns);
/*
				
			   String codigoUsuario = rs.getString("codigoUsuario");
			   String clave = rs.getString("clave");
			   String nombre = rs.getString("nombre");
			   String descripcion = rs.getString("descripcion");
			   System.out.println("|"+codigoUsuario+"|"+clave+"|"+nombre+"|"+descripcion+"|");*/
			}
	      
	      stmt.close();
	      conexion.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Registros cargados Corréctamente");
		return rows;
	}
	
	private static int contarRegistro(){
		int total = 0;
		Statement stmt = null;
		try {
	    	Connection conexion = getConexion();
	      	stmt = conexion.createStatement();

	      	ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS COUNT FROM Usuario WHERE clave = '21232f297a57a5a743894a0e4a801fc3' AND codigoUsuario = 'admin'");
	      	
			while(rs.next()){
				total = rs.getInt("COUNT");
			}
	      
	      stmt.close();
	      conexion.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Registros cargados Corréctamente");
		return total;
	}
	
	private static void alterarTabla(){
		
		Statement stmt = null;
	    try {
	      Connection conexion = getConexion();
	      stmt = conexion.createStatement();
	      
	      StringBuilder Tablas = new StringBuilder();
	      String sql = "";
	      /*
		  sql = "ALTER TABLE MantenimientoAtributo ADD COLUMN descripcion VARCHAR(255);";
		  Tablas.append(sql);
		  
	      sql = "ALTER TABLE Mantenimiento ADD COLUMN codigoDataSource VARCHAR(50);";
		  Tablas.append(sql);
		  
		  sql = "UPDATE Mantenimiento SET codigoDataSource = 'jdbc/cartaFianza';";
		  Tablas.append(sql);
		  
	      sql = "ALTER TABLE Usuario ADD COLUMN perfil VARCHAR(50) NOT NULL DEFAULT '';";
	      Tablas.append(sql);
		  
	      sql = "ALTER TABLE Usuario ADD COLUMN carpetaDestinoWorkspace VARCHAR(255) NOT NULL DEFAULT '';";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Usuario ADD COLUMN carpetaDestinoParcial VARCHAR(255) NOT NULL DEFAULT '';";
		  Tablas.append(sql);
		 
		  sql = "UPDATE Usuario SET perfil = 'PRO_MAN';";
		  Tablas.append(sql);
		  
	      sql = "INSERT INTO Usuario (codigoUsuario,clave,nombre,perfil,carpetaDestinoWorkspace,carpetaDestinoParcial) VALUES ('OSCHUP','"+MapeoUtil.getMD5("OSCHUP")+"','Oscar Huertas','PRO_MAN','','');";
		  Tablas.append(sql);
		  sql = "INSERT INTO Usuario (codigoUsuario,clave,nombre,perfil,carpetaDestinoWorkspace,carpetaDestinoParcial) VALUES ('EDWREB','"+MapeoUtil.getMD5("EDWREB")+"','Edwin Rebasa','PRO_MAN','','');";
		  Tablas.append(sql);
		   
	      
	     
	      sql = "DELETE FROM Clase;";
	      Tablas.append(sql);
	      
	      
	    //Tablas.append("INSERT INTO Usuario (codigoUsuario,clave,nombre) VALUES ('admin2','"+MapeoUtil.getMD5("admin2")+"','Oscar Huertas');");
	      
	      
		  sql = "CREATE TABLE ConfiguracionB (" +
			"		codigoProyecto VARCHAR(50)  			NOT NULL," +
			"		empresa VARCHAR(100)     				NOT NULL," +
			"		unidadNegocio VARCHAR(50)       		NOT NULL," +
			"		unidadNegocioDescripcion VARCHAR(100) 	," +
			"		producto VARCHAR(50)       				NOT NULL," +
			"		productoDescripcion VARCHAR(100) 		," +
			"		urlPrefijoPaquete VARCHAR(50)           NOT NULL," +
			"		directorioLib VARCHAR(100)           	NOT NULL," +
			"		directorioEJBClient VARCHAR(100)        NOT NULL," +
			"		directorioEJBAuto VARCHAR(100)          NOT NULL," +
			"		directorioWebAuto VARCHAR(100)          NOT NULL," +
			"		PRIMARY KEY(codigoProyecto)" +
			");";
		  Tablas.append(sql);
		  
		  sql = "INSERT INTO ConfiguracionB SELECT codigoProyecto,empresa,unidadNegocio,unidadNegocioDescripcion,producto,productoDescripcion,urlPrefijoPaquete,directorioLib,directorioEJBClient,directorioEJBAuto,directorioWebAuto FROM Configuracion;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Configuracion RENAME TO ConfiguracionC;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE ConfiguracionB RENAME TO Configuracion;";
		  Tablas.append(sql);
		 
	      sql = "DROP TABLE ConfiguracionB;";
		  Tablas.append(sql);
		  
	      sql = "ALTER TABLE Equipo ADD COLUMN carpetaDestinoWorkspace VARCHAR(255) NOT NULL DEFAULT '';";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Equipo ADD COLUMN carpetaDestinoParcial VARCHAR(255) NOT NULL DEFAULT '';";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Usuario REMOVE COLUMN carpetaDestinoWorkspace;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Usuario REMOVE COLUMN carpetaDestinoParcial;";
		  Tablas.append(sql);
		   */
	      
	      /*
	      sql = "CREATE TABLE UsuarioB (" +
			"		codigoUsuario VARCHAR(50) PRIMARY KEY   NOT NULL," +
			"		clave		  VARCHAR(100)              NOT NULL," +
			"		nombre        VARCHAR(100)              NOT NULL," + 
			"		perfil        VARCHAR(50)             	NOT NULL," + 
			"		descripcion   VARCHAR(250) " + 
			");";
	      Tablas.append(sql);
		  
		  sql = "INSERT INTO UsuarioB SELECT codigoUsuario,clave,nombre,perfil,descripcion FROM Usuario;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Usuario RENAME TO UsuarioC;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE UsuarioB RENAME TO Usuario;";
		  Tablas.append(sql);
		 
	      sql = "DROP TABLE UsuarioC;";
		  Tablas.append(sql);
	      
	      sql = "ALTER TABLE Proceso ADD COLUMN esTerminado_verResumen CHARACTER(1);";
		  Tablas.append(sql);
	      
		  sql = "ALTER TABLE Proceso ADD COLUMN esTerminado_verDetalle CHARACTER(1);";
		  Tablas.append(sql);
	      
		  sql = "ALTER TABLE Proceso ADD COLUMN esTerminado_inicioProceso CHARACTER(1);";
		  Tablas.append(sql);
	     
		  
	      sql = "INSERT INTO VersionUN (codigoProyecto,numeroVersion,codigoUnidadNegocio,estado) VALUES ('CartaFianza','1','BE','1');";
	      Tablas.append(sql);
	      
	      sql = "CREATE TABLE RolB (" +
			"		codigoProyecto VARCHAR(50)  	NOT NULL," +
			"		codigoRol VARCHAR(100)    		NOT NULL," +
			"		descripcion VARCHAR(255)        NOT NULL," +
			"		estado CHARACTER(1)," +
			"		PRIMARY KEY(codigoProyecto,codigoRol)" +
			");";
	      	Tablas.append(sql);
	      	
      	 sql = "INSERT INTO RolB SELECT codigoProyecto,codigoRol,descripcion,estado FROM Rol;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Rol RENAME TO RolC;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE RolB RENAME TO Rol;";
		  Tablas.append(sql);
		 
	      sql = "DROP TABLE RolC;";
		  Tablas.append(sql);
		   
		  
		  sql = "CREATE TABLE ConfiguracionB (" +
			"		codigoProyecto VARCHAR(50)  			NOT NULL," +
			"		empresa VARCHAR(100)     				NOT NULL," +
			"		producto VARCHAR(50)       				NOT NULL," +
			"		productoDescripcion VARCHAR(100) 		," +
			"		urlPrefijoPaquete VARCHAR(50)           NOT NULL," +
			"		directorioLib VARCHAR(100)           	NOT NULL," +
			"		directorioEJBClient VARCHAR(100)        NOT NULL," +
			"		directorioEJBAuto VARCHAR(100)          NOT NULL," +
			"		directorioWebAuto VARCHAR(100)          NOT NULL," +
			"		PRIMARY KEY(codigoProyecto)" +
			");";
		  Tablas.append(sql); 
		  
		  sql = "INSERT INTO ConfiguracionB SELECT codigoProyecto,empresa,producto,productoDescripcion,urlPrefijoPaquete,directorioLib,directorioEJBClient,directorioEJBAuto,directorioWebAuto FROM Configuracion;";
		  Tablas.append(sql);
		  */
		  sql = "DROP TABLE ConfiguracionC;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE Configuracion RENAME TO ConfiguracionC;";
		  Tablas.append(sql);
		  
		  sql = "ALTER TABLE ConfiguracionB RENAME TO Configuracion;";
		  Tablas.append(sql);
		 
	      sql = "DROP TABLE ConfiguracionC;";
		  Tablas.append(sql);
		  
	      stmt.executeUpdate(Tablas.toString());
	      stmt.close();
	      conexion.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Tablas Alterada Corréctamente");
		
	}
	
	public static void main(String[] args) {
		//cargarTablas();
		//alterarTabla();
		//registrarDatos();
		
		//borrarTablas();
		//alterarTabla();
		//cargarTablas();
		//altrarTabla();
		//registrarDatos();
		//borrarTablas();
		
		listarRegistros();
		
		Gson json = new Gson();
		
		List<Map<String, Object>> registros = listarRegistros();
		
		System.out.println(json.toJson(registros));
		
		
		for (int i = 0; i < registros.size(); i++) {
			System.out.println("Registro Nro "+i);
			
			System.out.println("   "+registros.get(i).get("COD_PROYECTO")+" : "+registros.get(i).get("USUARIO"));
			/*
			for (Map.Entry<String, Object> entry : registros.get(i).entrySet()) {
				String key = entry.getKey();
		    	//String String = (java.lang.String) entry.getValue();
		    	System.out.println("   "+key+" : "+entry.getValue());
			}*/
		}
		
	}

}
