package pe.com.captiva.servicio.util.tarea;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class TareaClaseUtil extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\" + "src" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\tarea\\controlador\\");
				
				List<TareaBean> tareas = procesoBean.getTareas();
				for (int y = 0; y < tareas.size(); y++) {
					TareaBean tareaBean = tareas.get(y);
					Componente componente = new Componente();
					componente.setDirectorio(directorio);
					componente.setArchivo(tareaBean.getClase()+"Util.java");
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
		
		buffer.append("import pe.com.soul.core.web.util.TareaUtil;\r\n\r\n");

		buffer.append("public class "+tareaBean.getClase()+"Util extends Pre"+tareaBean.getClase()+"Util implements TareaUtil{\r\n\r\n");
		
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
