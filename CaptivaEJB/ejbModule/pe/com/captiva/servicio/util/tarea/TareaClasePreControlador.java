package pe.com.captiva.servicio.util.tarea;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class TareaClasePreControlador extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\tarea\\controlador\\");
				
				List<TareaBean> tareas = procesoBean.getTareas();
				for (int y = 0; y < tareas.size(); y++) {
					TareaBean tareaBean = tareas.get(y);
					Componente componente = new Componente();
					componente.setDirectorio(directorio);
					componente.setArchivo("Pre"+tareaBean.getClase()+"Controlador.java");
					componente.setContenido(contenido(proyectoBean, procesoBean, tareaBean));
					
					componentes.add(componente);
				}
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean, TareaBean tareaBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".tarea.controlador;\r\n\r\n");
		
		buffer.append("import javax.ejb.EJB;\r\n");
		buffer.append("import javax.servlet.http.HttpServletRequest;\r\n");
		buffer.append("import javax.servlet.http.HttpServletResponse;\r\n\r\n");

		buffer.append("import "+proyectoBean.getPaquete()+ClaseBean.SUFIJO_PAQUETE+".*;\r\n");
		buffer.append("import "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".tarea.servicio."+tareaBean.getClase()+"ServicioLocal;\r\n");
		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n");
		buffer.append("import pe.com.soul.core.servicio.BaseTareaServicioLocal;\r\n");
		buffer.append("import pe.com.soul.core.web.bean.Respuesta;\r\n");
		buffer.append("import pe.com.soul.core.web.controller.BaseTareaController;\r\n");
		buffer.append("import pe.com.soul.core.web.util.TareaUtil;\r\n\r\n");

		buffer.append("public abstract class Pre"+tareaBean.getClase()+"Controlador extends BaseTareaController{\r\n\r\n");

		buffer.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
			
		buffer.append("\t@EJB\r\n");
		buffer.append("\t"+tareaBean.getClase()+"ServicioLocal "+tareaBean.getClase().toLowerCase()+"ServicioLocal;\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic TareaUtil getTareaUtil() {\r\n");
		buffer.append("\t\treturn new "+tareaBean.getClase()+"Util();\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic BaseTareaServicioLocal getBaseTareaServicioLocal() {\r\n");
		buffer.append("\t\treturn this."+tareaBean.getClase().toLowerCase()+"ServicioLocal;\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("}");

		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
