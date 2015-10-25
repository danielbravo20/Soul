package pe.com.captiva.servicio.util.tarea;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class TareaClaseControlador extends MultipleBaseConstructor{

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
					componente.setArchivo(tareaBean.getClase()+"Controlador.java");
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

		buffer.append("import javax.servlet.annotation.WebServlet;\r\n\r\n");

		buffer.append("@WebServlet(\"/portal/"+procesoBean.getClase().toLowerCase()+"/"+tareaBean.getClase().toLowerCase()+"\")\r\n");
		buffer.append("public class "+tareaBean.getClase()+"Controlador extends Pre"+tareaBean.getClase()+"Controlador{\r\n\r\n");

		buffer.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
		
		buffer.append("}");

		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
