package pe.com.captiva.servicio.util.general;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.RolBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class GeneralSQLInsertSoul extends MultipleBaseConstructor{

	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}
	
	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ClaseBean> clases = proyectoBean.getClases();
		for (int x = 0; x < clases.size(); x++) {
			Componente componente = new Componente();
			componente.setDirectorio(proyectoBean.getEquipoBean().getDirectorioParcial() + File.separatorChar + ProyectoBean.SUFIJO_SQL + File.separatorChar );
			componente.setArchivo("insert_soul_"+proyectoBean.getProyecto().trim().toLowerCase()+".sql");
			componente.setContenido(contenido(proyectoBean));
			componentes.add(componente);
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean){
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\r");
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			buffer.append(insertProcesoPlantilla(procesoBean));
		}
		
		buffer.append("\n\r");
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			buffer.append(insertPotencialIniciador(procesoBean));
		}
		
		buffer.append("\n\r");
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			buffer.append(insertTareaPlantilla(procesoBean));
		}
		
		buffer.append("\n\r");
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			buffer.append(insertAdministradorTarea(procesoBean));
		}
		
		buffer.append("\n\r");
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			buffer.append(insertDuenoPotencialTarea(procesoBean));
		}
		
		return buffer;
	}
	
	public String insertProcesoPlantilla(ProcesoBean procesoBean){
		return "insert into proceso.proceso_plantilla (codigo_proceso_plantilla, estado_proceso, nombre_proceso, aleas_proceso, version_proceso, fecha_validez_proceso, flag_todos_inician) values ( "+procesoBean.getCodigo()+", '1', '"+procesoBean.getNombre()+"', '"+procesoBean.getClase().toLowerCase()+"', 'v1.0.0', '2010-01-01 00:00:00', 'f');\r\n";
	}
	
	public String insertPotencialIniciador(ProcesoBean procesoBean){
		StringBuffer stringBuffer = new StringBuffer();
		List<RolBean> roles = procesoBean.getRolPotencial();
		for (int x = 0; x < roles.size(); x++) {
			RolBean rolBean = roles.get(x);
			stringBuffer.append("insert into proceso.potencial_iniciador(codigo_proceso_plantilla, rol_codigo_rol) values ("+procesoBean.getCodigo()+", '"+rolBean.getRol()+"');\r\n");
		}
		return stringBuffer.toString();
	}
	
	public String insertTareaPlantilla(ProcesoBean procesoBean){
		StringBuffer stringBuffer = new StringBuffer();
		List<TareaBean> tareas = procesoBean.getTareas();
		for (int x = 0; x < tareas.size(); x++) {
			TareaBean tareaBean = tareas.get(x);
			stringBuffer.append("insert into proceso.tarea_plantilla(codigo_tarea_plantilla, codigo_proceso_plantilla, estado_tarea, nombre_tarea, aleas_tarea, version_tarea, prioridad_tarea, orden_tarea) values ("+tareaBean.getCodigo()+", "+procesoBean.getCodigo()+", '1', '"+tareaBean.getNombre()+"', '"+tareaBean.getClase()+"', 1, 1);\r\n");
		}
		return stringBuffer.toString();
	}
	
	public String insertAdministradorTarea(ProcesoBean procesoBean){
		StringBuffer stringBuffer = new StringBuffer();
		List<TareaBean> tareas = procesoBean.getTareas();
		for (int x = 0; x < tareas.size(); x++) {
			TareaBean tareaBean = tareas.get(x);
			List<RolBean> rolesBean = tareaBean.getRolesAdministrador();
			for (int y = 0; y < rolesBean.size(); y++) {
				RolBean rolBean = rolesBean.get(y);
				stringBuffer.append("insert into proceso.administrador_tarea(codigo_tarea_plantilla, codigo_rol) values ("+tareaBean.getCodigo()+", '"+rolBean.getRol()+"');\r\n");
			}
			
		}
		return stringBuffer.toString();
	}
	
	public String insertDuenoPotencialTarea(ProcesoBean procesoBean){
		StringBuffer stringBuffer = new StringBuffer();
		List<TareaBean> tareas = procesoBean.getTareas();
		for (int x = 0; x < tareas.size(); x++) {
			TareaBean tareaBean = tareas.get(x);
			List<RolBean> rolesBean = tareaBean.getRolesPotencial();
			for (int y = 0; y < rolesBean.size(); y++) {
				RolBean rolBean = rolesBean.get(y);
				stringBuffer.append("insert into proceso.potencial_dueno(codigo_tarea_plantilla, codigo_rol) values ("+tareaBean.getCodigo()+", '"+rolBean.getRol()+"');\r\n");
			}
			
		}
		return stringBuffer.toString();
	}

	@Override
	public List<Componente> crear(Jpo jpo, ProyectoBean proyectoBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
