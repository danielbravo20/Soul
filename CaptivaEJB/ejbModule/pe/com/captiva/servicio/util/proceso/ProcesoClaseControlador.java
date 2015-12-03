package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class ProcesoClaseControlador extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\" + "src" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\controlador\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo(procesoBean.getClase()+"Controlador.java");
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

		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n");
		buffer.append("import pe.com.soul.core.web.bean.Respuesta;\r\n\r\n");
		
		buffer.append("import javax.servlet.annotation.WebServlet;\r\n\r\n");

		buffer.append("@WebServlet(\"/portal/"+procesoBean.getClase().toLowerCase()+"\")\r\n");
		buffer.append("public class "+procesoBean.getClase()+"Controlador extends Pre"+procesoBean.getClase()+"Controlador{\r\n\r\n");

		buffer.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
		
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Respuesta accionCrear(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {\r\n");
		buffer.append("\t\treturn super.accionCrear(request, response, usuarioPortal);\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Respuesta accionResumen(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {\r\n");
		buffer.append("\t\treturn super.accionResumen(request, response, usuarioPortal);\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Respuesta accionDetalle(HttpServletRequest request, HttpServletResponse response, UsuarioPortal usuarioPortal) throws Exception {\r\n");
		buffer.append("\t\treturn super.accionDetalle(request, response, usuarioPortal);\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}


	@Override
	public List<Componente> crear(Jpo jpo, ProyectoBean proyectoBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
