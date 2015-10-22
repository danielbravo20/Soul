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

public class Generar {
	
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
				
				Thread hiloTablaDao = new Thread( new TablaDaoImpl(),"hiloTablaDao" );
				Thread hiloConsultaDao = new Thread( new ConsultaDaoImpl(),"hiloConsultaDao" );
				Thread hiloProcesoDao = new Thread( new ProcesoDaoImpl(),"hiloProcesoDao" );
				Thread hiloTareaDao = new Thread( new TareaDaoImpl(),"hiloTareaDao" );
				
				System.out.println(new Date()+"...recuperando definición de tareas");
				hiloTareaDao.start();
				
				System.out.println(new Date()+"...recuperando definición de procesos");
				hiloProcesoDao.start();
				
				System.out.println(new Date()+"...recuperando definición de consultas");
				hiloConsultaDao.start();
				
				System.out.println(new Date()+"...recuperando definición de tablas");
				hiloTablaDao.start();
				
				int i = 0;
				while(i!=4){
					i=0;
					Thread.currentThread();
					Thread.sleep( new Long(1000 * 5) );
					
					if(hiloTablaDao.isAlive()==false){						
						i++;
					}
					
					if(hiloConsultaDao.isAlive()==false){
						i++;
					}
					
					if(hiloProcesoDao.isAlive()==false){
						i++;
					}
					
					if(hiloTareaDao.isAlive()==false){
						i++;
					}
					
				}
				
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