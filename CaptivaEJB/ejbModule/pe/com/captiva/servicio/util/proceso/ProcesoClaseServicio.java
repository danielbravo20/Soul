package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.RolBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class ProcesoClaseServicio extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICE + "\\" + "ejbModule" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\servicio\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo(procesoBean.getClase()+"Servicio.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		String clasePadre = proyectoBean.getClasePadre().getNombre();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio;\r\n\r\n");

		buffer.append("import javax.ejb.LocalBean;\r\n");
		buffer.append("import javax.ejb.Stateless;\r\n\r\n");

		buffer.append("import javax.annotation.security.RolesAllowed;\r\n");
		
		buffer.append("import pe.com.soul.core.modelo.Proceso;\r\n");
		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n\r\n");
		
		buffer.append("import "+proyectoBean.getPaquete()+".bean."+clasePadre+";\r\n\r\n");
		
		buffer.append("@Stateless\r\n");
		buffer.append("@LocalBean\r\n");
		buffer.append("public class "+procesoBean.getClase()+"Servicio extends Pre"+procesoBean.getClase()+"Servicio implements "+procesoBean.getClase()+"ServicioLocal {\r\n\r\n");
		
		StringBuffer rol = new StringBuffer();
		List<RolBean> roles = procesoBean.getRolPotencial();
		for (int x = 0; x < roles.size(); x++) {
			if(x!=0){
				rol.append(" ,");
			}
			rol.append("\""+roles.get(x).getRol()+"\"");
		}
		
		buffer.append("\t@RolesAllowed("+rol.toString()+")\r\n");
		buffer.append("\tpublic Proceso accionCrearInstancia(UsuarioPortal usuarioPortal, Object objeto) throws Exception {\r\n");
		buffer.append("\t\treturn super.accionCrearInstancia(usuarioPortal, objeto);\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic "+clasePadre+" accionVerResumen(UsuarioPortal usuarioPortal, "+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\treturn super.accionVerResumen(usuarioPortal, "+clasePadre.toLowerCase()+");\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic "+clasePadre+" accionVerDetalle(UsuarioPortal usuarioPortal, "+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\treturn super.accionVerDetalle(usuarioPortal, "+clasePadre.toLowerCase()+");\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
