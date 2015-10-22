package com.carga.portal.ejecucion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.carga.mapeo.dao.ConexionBD;
import com.carga.mapeo.dao.impl.ConsultaDaoImpl;
import com.carga.mapeo.dao.impl.ProcesoDaoImpl;
import com.carga.mapeo.dao.impl.ProyectoDaoImpl;
import com.carga.mapeo.dao.impl.TablaDaoImpl;
import com.carga.mapeo.dao.impl.TareaDaoImpl;
import com.carga.portal.modelo.Configuracion;
import com.carga.portal.modelo.Proyecto;
import com.carga.portal.servicio.crear.CrearSQL;
import com.carga.portal.servicio.crear.CrearBean;
import com.carga.portal.servicio.crear.CrearProceso;
import com.carga.portal.servicio.crear.CrearTarea;
import com.carga.portal.servicio.crear.CrearWebValidacion;

public class Generar3 {
	
	public static Proyecto proyecto;
	
	public static void main(String[] args) { 
		
		String codigoProyecto = args[0];
		String usuario = args[1];
		System.out.println(codigoProyecto+" - "+usuario);
		Connection connection = null;
		
		if(codigoProyecto!=null && usuario!=null){
			try {
				connection = ConexionBD.getConexion();
				
				ProyectoDaoImpl proyectoDaoImpl = new ProyectoDaoImpl();
				System.out.println(new Date()+"...recuperando la información del proyecto");
				proyecto = proyectoDaoImpl.obtenerProyecto(codigoProyecto, connection);
				System.out.println(new Date()+"...recuperando la informaci�n de la configuración");
				Configuracion configuracion = proyectoDaoImpl.obtenerConfiguracion(proyecto, usuario, connection);
				System.out.println(new Date()+"...cargando objetos");
				
				TablaDaoImpl tablaDaoImpl = new TablaDaoImpl();
				ConsultaDaoImpl consultaDaoImpl = new ConsultaDaoImpl();
				ProcesoDaoImpl procesoDaoImpl = new ProcesoDaoImpl();
				TareaDaoImpl tareaDaoImpl = new TareaDaoImpl();
				
				System.out.println(new Date()+"...recuperando definición de tareas");
				proyecto.setTareas(tareaDaoImpl.obtenerTareas(proyecto, connection));
				
				System.out.println(new Date()+"...recuperando definición de procesos");
				proyecto.setProcesos(procesoDaoImpl.obtenerProcesos(proyecto, connection));
				
				System.out.println(new Date()+"...recuperando definición de consultas");
				proyecto.setConsultas(consultaDaoImpl.obtenerConsultas(proyecto, connection));
				
				System.out.println(new Date()+"...recuperando definición de tablas");
				proyecto.setTablas(tablaDaoImpl.obtenerTablas(proyecto, connection));
				
				System.out.println(new Date()+"...recuperando definición de unidades de negocio");
				proyecto.setUnidadesNegocio(proyectoDaoImpl.obtenerUnidadesNegocio(connection));
				
				System.out.println(new Date()+"...creando los objetos bean's");
				new CrearBean().crear(proyecto, configuracion, connection);
				
				System.out.println(new Date()+"...creando los objetos sql");
				new CrearSQL().crear(proyecto, configuracion, connection);
				
				System.out.println(new Date()+"...creando los procesos");
				new CrearProceso().crear(proyecto, configuracion, connection);
				
				System.out.println(new Date()+"...creando las tareas");
				new CrearTarea().crear(proyecto, configuracion, connection);
				
				System.out.println(new Date()+"...creando las validaciones web");
				new CrearWebValidacion().crear(proyecto, configuracion, connection);
				/*
				System.out.println(new Date()+"...creando xml process");
				new CrearAS400Xml().crear(proyecto, configuracion, connection);
				System.out.println(new Date()+"...creando map process");
				new CrearAS400Map().crear(proyecto, configuracion, connection);
				*/
				
				new CrearSQL().integrarArchivos(configuracion);
				
				
				System.out.println(new Date()+"...termino!");
				
			} catch (SQLException e){
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				try {
					if (connection != null){
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}