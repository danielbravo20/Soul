package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class ProcesoClasePreUtil extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\controlador\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("Pre"+procesoBean.getClase()+"Util.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".controlador;\r\n\r\n");

		buffer.append("import javax.servlet.http.HttpServletRequest;\r\n");
		buffer.append("import javax.servlet.http.HttpServletResponse;\r\n\r\n");

		buffer.append("import pe.com.soul.core.modelo.MensajeValidacion;\r\n");
		buffer.append("import pe.com.soul.core.web.util.ProcesoUtil;\r\n\r\n");

		buffer.append("public class Pre"+procesoBean.getClase()+"Util implements ProcesoUtil{\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {\r\n");
		buffer.append("\t\tMensajeValidacion mensajeValidacion = new MensajeValidacion();\r\n");
		
		//TODO incluir la validacion de los campos
		List<AtributoProceso> atributos = procesoBean.getAtributosEntrada();
		for (int i = 0; i < atributos.size(); i++) {
			AtributoProceso atributoProceso = atributos.get(i);
			if(atributoProceso.isWebFlgValidacion()){
				///
			}
		}
		
		
		buffer.append("\t\tmensajeValidacion.setConforme(true);\r\n");
		buffer.append("\t\treturn mensajeValidacion;\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {\r\n");
		buffer.append("\t\treturn null;\r\n");
		buffer.append("\t}\r\n");
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
