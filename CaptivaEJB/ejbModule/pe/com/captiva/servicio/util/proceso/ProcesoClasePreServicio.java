package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TareaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class ProcesoClasePreServicio extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICE + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\servicio\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("Pre"+procesoBean.getClase()+"Servicio.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio;\r\n\r\n");

		buffer.append("import javax.annotation.Resource;\r\n");
		buffer.append("import javax.ejb.EJB;\r\n");
		buffer.append("import javax.ejb.SessionContext;\r\n\r\n");

		buffer.append("import pe.com.soul.core.modelo.Proceso;\r\n");
		buffer.append("import pe.com.soul.core.modelo.TareaPlantilla;\r\n");
		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n");
		buffer.append("import pe.com.soul.core.service.portal.ProcesoServiceLocal;\r\n");
		buffer.append("import pe.com.soul.core.servicio.BaseProcesoServicioLocal;\r\n");
		buffer.append("import pe.com.soul.core.servicio.impl.BaseProcesoServicioImpl;\r\n\r\n");

		buffer.append("public abstract class Pre"+procesoBean.getClase()+"Servicio extends BaseProcesoServicioImpl implements BaseProcesoServicioLocal{\r\n\r\n");

		buffer.append("\tpublic static final long   PROCESO_CODIGO_PLANTILLA_PROCESO = "+procesoBean.getCodigo()+";\r\n");
		buffer.append("\tpublic static final String PROCESO_NOMBRE = \""+procesoBean.getNombre()+"\";\r\n"); 
		buffer.append("\tpublic static final String PROCESO_ALEAS = \""+procesoBean.getClase()+"\";\r\n");
		buffer.append("\tpublic static final String PROCESO_VERSION = \"v1.0.0\";\r\n\r\n");
			
		buffer.append("\t@EJB\r\n");
		buffer.append("\tProcesoServiceLocal procesoServiceLocal;\r\n\r\n");
			
		buffer.append("\t@Resource\r\n");
		buffer.append("\tprivate SessionContext sessionContext;\r\n\r\n");

		buffer.append("\tpublic Proceso crearInstancia(UsuarioPortal usuarioPortal) throws Exception {\r\n");
		buffer.append("\t\tProceso proceso = new Proceso();\r\n");
		buffer.append("\t\tproceso.setCodigoProcesoPlantilla(PROCESO_CODIGO_PLANTILLA_PROCESO);\r\n");
		buffer.append("\t\tproceso.setNombre(PROCESO_NOMBRE);\r\n");
		buffer.append("\t\tproceso.setAleas(PROCESO_ALEAS);\r\n");
		buffer.append("\t\tproceso.setVersion(PROCESO_VERSION);\r\n");
		buffer.append("\t\tproceso.setCreador(usuarioPortal.getUsuario());\r\n");
		buffer.append("\t\tproceso = procesoServiceLocal.crearInstancia(proceso);\r\n");
		buffer.append("\t\treturn proceso;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\tpublic TareaPlantilla definirProximaTarea(Proceso proceso) throws Exception{\r\n");
		buffer.append("\t\tTareaPlantilla plantilla = new TareaPlantilla();\r\n");
		
		if(procesoBean.getTareas()!=null){
			TareaBean tareaBean = procesoBean.getTareas().get(0);
			buffer.append("\t\tplantilla.setCodigoTareaPlantilla("+tareaBean.getCodigo()+");\r\n");
			buffer.append("\t\tplantilla.setNombre(\""+tareaBean.getNombre()+"\");\r\n");
			buffer.append("\t\tplantilla.setAleas(\""+tareaBean.getClase()+"\");\r\n");
			buffer.append("\t\tplantilla.setEstado(1);\r\n");
			buffer.append("\t\tplantilla.setOrden(1);\r\n");
			buffer.append("\t\tplantilla.setPrioridad(1);\r\n");
			buffer.append("\t\tplantilla.setVersion(\"v1.0.0\");\r\n");
		}
		buffer.append("\t\treturn plantilla;\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic String definirProximoDueno(Proceso proceso) throws Exception {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
