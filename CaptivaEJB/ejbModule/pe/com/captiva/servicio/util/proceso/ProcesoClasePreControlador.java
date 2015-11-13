package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class ProcesoClasePreControlador extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\controlador\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("Pre"+procesoBean.getClase()+"Controlador.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		ClaseBean clasePadre = proyectoBean.getClasePadre();
		String nombreClase = clasePadre.getNombre();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".controlador;\r\n\r\n");
		
		buffer.append("import javax.ejb.EJB;\r\n");
		buffer.append("import javax.servlet.http.HttpServletRequest;\r\n");
		buffer.append("import javax.servlet.http.HttpServletResponse;\r\n\r\n");

		buffer.append("import "+proyectoBean.getPaquete()+ClaseBean.SUFIJO_PAQUETE+".*;\r\n");
		buffer.append("import "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio."+procesoBean.getClase()+"ServicioLocal;\r\n");
		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n");
		buffer.append("import pe.com.soul.core.modelo.MensajeValidacion;\r\n");
		buffer.append("import pe.com.soul.core.servicio.BaseProcesoServicioLocal;\r\n");
		buffer.append("import pe.com.soul.core.web.bean.Respuesta;\r\n");
		buffer.append("import pe.com.soul.core.web.controller.BaseProcesoController;\r\n");
		buffer.append("import pe.com.soul.core.web.util.ProcesoUtil;\r\n\r\n");

		buffer.append("public abstract class Pre"+procesoBean.getClase()+"Controlador extends BaseProcesoController{\r\n\r\n");

		buffer.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
			
		buffer.append("\t@EJB\r\n");
		buffer.append("\t"+procesoBean.getClase()+"ServicioLocal "+procesoBean.getClase().toLowerCase()+"ServicioLocal;\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic ProcesoUtil getProcesoUtil() {\r\n");
		buffer.append("\t\treturn new "+procesoBean.getClase()+"Util();\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic BaseProcesoServicioLocal getBaseProcesoServicioLocal() {\r\n");
		buffer.append("\t\treturn this."+procesoBean.getClase().toLowerCase()+"ServicioLocal;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {\r\n");
		buffer.append("\t\tRespuesta respuesta = new Respuesta();\r\n");
		buffer.append("\t\tMensajeValidacion mensajeValidacion =  getProcesoUtil().validacionCamposVerResumen(request, response);\r\n");
		buffer.append("\t\tif(mensajeValidacion.isConforme()){\r\n");
		buffer.append("\t\t\trespuesta.setResultado(true);\r\n");
		buffer.append("\t\t\trespuesta.setRespuesta("+procesoBean.getClase().toLowerCase()+"ServicioLocal.accionVerResumen(usuarioPortal, ("+nombreClase+")getProcesoUtil().poblarObjetosVerResumen(request, response)));\r\n");
		buffer.append("\t\t}else{\r\n");
		buffer.append("\t\t\trespuesta.setResultado(false);\r\n");
		buffer.append("\t\t\trespuesta.setRespuesta(mensajeValidacion);\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn respuesta;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {\r\n");
		buffer.append("\t\tRespuesta respuesta = new Respuesta();\r\n\r\n");
		
		buffer.append("\t\tMensajeValidacion mensajeValidacion =  getProcesoUtil().validacionCamposVerDetalle(request, response);\r\n");
		buffer.append("\t\tif(mensajeValidacion.isConforme()){\r\n");
		buffer.append("\t\t\trespuesta.setResultado(true);\r\n");
		buffer.append("\t\t\trespuesta.setRespuesta("+procesoBean.getClase().toLowerCase()+"ServicioLocal.accionVerDetalle(usuarioPortal, ("+nombreClase+")getProcesoUtil().poblarObjetosVerDetalle(request, response)));\r\n");
		buffer.append("\t\t}else{\r\n");
		buffer.append("\t\t\trespuesta.setResultado(false);\r\n");
		buffer.append("\t\t\trespuesta.setRespuesta(mensajeValidacion);\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn respuesta;\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("\tpublic Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuario) throws Exception {\r\n");
		buffer.append("\t\tRespuesta respuesta = new Respuesta();\r\n");
		buffer.append("\t\tMensajeValidacion mensajeValidacion =  getProcesoUtil().validacionCampos(request, response);\r\n");
		buffer.append("\t\tif(mensajeValidacion.isConforme()){\r\n");
		buffer.append("\t\t\trespuesta.setResultado(true);\r\n");
		buffer.append("\t\t\trespuesta.setRespuesta(getBaseProcesoServicioLocal().accionCrearInstancia(usuario, getProcesoUtil().poblarObjetos(request, response)));\r\n");
		buffer.append("\t\t}else{\r\n");
		buffer.append("\t\t\trespuesta.setResultado(false);\r\n");
		buffer.append("\t\t\trespuesta.setRespuesta(mensajeValidacion);\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn respuesta;\r\n");
		buffer.append("\t}\r\n\r\n");
		
		
		buffer.append("}");

		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
