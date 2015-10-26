package pe.com.captiva.servicio.util.tarea;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class TareaClasePreServicio extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICE + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\tarea\\servicio\\");
				
				List<TareaBean> tareas = procesoBean.getTareas();
				for (int y = 0; y < tareas.size(); y++) {
					TareaBean tareaBean = tareas.get(y);
					Componente componente = new Componente();
					componente.setDirectorio(directorio);
					componente.setArchivo("Pre"+tareaBean.getClase()+"Servicio.java");
					componente.setContenido(contenido(proyectoBean, procesoBean, tareaBean));
					
					componentes.add(componente);
				}
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean, TareaBean tareaBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".tarea.servicio;\r\n\r\n");

		buffer.append("import pe.com.soul.core.modelo.Tarea;\r\n");
		buffer.append("import pe.com.soul.core.modelo.TareaPlantilla;\r\n");
		buffer.append("import pe.com.soul.core.servicio.BaseTareaServicioLocal;\r\n");
		buffer.append("import pe.com.soul.core.servicio.impl.BaseTareaServicioImpl;\r\n\r\n");

		buffer.append("public abstract class Pre"+tareaBean.getClase()+"Servicio extends BaseTareaServicioImpl implements BaseTareaServicioLocal{\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object trabajar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object completar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic TareaPlantilla proximaTareaCompletar(Tarea tarea) throws Exception {\r\n");
		if(tareaBean.getTareaSiguiente()!=null){
			TareaBean siguiente = tareaBean.getTareaSiguiente();
			buffer.append("\t\tTareaPlantilla tareaPlantilla = new TareaPlantilla();\r\n");
			buffer.append("\t\ttareaPlantilla.setCodigoTareaPlantilla("+siguiente.getCodigo()+");\r\n");
			buffer.append("\t\ttareaPlantilla.setAleas(\""+siguiente.getClase()+"\");\r\n");
			buffer.append("\t\ttareaPlantilla.setEstado(1);\r\n");
			buffer.append("\t\ttareaPlantilla.setNombre(\""+siguiente.getNombre()+"\");\r\n");
			buffer.append("\t\ttareaPlantilla.setOrden(2);\r\n");
			buffer.append("\t\ttareaPlantilla.setPrioridad(1);\r\n");
			buffer.append("\t\ttareaPlantilla.setVersion(\"v1.0.0\");\r\n");
			buffer.append("\t\treturn tareaPlantilla;\r\n");
		}else{
			buffer.append("\t\treturn null;\r\n");
		}
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic String proximoDuenoCompletar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object cancelar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object rechazar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object observar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic TareaPlantilla proximaTareaObservar(Tarea tarea) throws Exception {\r\n");
		if(tareaBean.getTareaObservado()!=null){
			TareaBean observado = tareaBean.getTareaObservado();
			buffer.append("\t\tTareaPlantilla tareaPlantilla = new TareaPlantilla();\r\n");
			buffer.append("\t\ttareaPlantilla.setCodigoTareaPlantilla("+observado.getCodigo()+");\r\n");
			buffer.append("\t\ttareaPlantilla.setAleas(\""+observado.getClase()+"\");\r\n");
			buffer.append("\t\ttareaPlantilla.setEstado(1);\r\n");
			buffer.append("\t\ttareaPlantilla.setNombre(\""+observado.getNombre()+"\");\r\n");
			buffer.append("\t\ttareaPlantilla.setOrden(2);\r\n");
			buffer.append("\t\ttareaPlantilla.setPrioridad(1);\r\n");
			buffer.append("\t\ttareaPlantilla.setVersion(\"v1.0.0\");\r\n");
			buffer.append("\t\treturn tareaPlantilla;\r\n");
		}else{
			buffer.append("\t\treturn null;\r\n");
		}
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic String proximoDuenoObservar(Tarea tarea) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object transferir(Tarea tarea, String nuevoUsuario) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("}\r\n\r\n");

		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
