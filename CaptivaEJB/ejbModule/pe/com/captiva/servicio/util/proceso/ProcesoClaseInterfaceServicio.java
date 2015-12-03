package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class ProcesoClaseInterfaceServicio extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICELIB + "\\" + "ejbModule" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\servicio\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo(procesoBean.getClase()+"ServicioLocal.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		
		String clasePadre = proyectoBean.getClasePadre().getNombre();
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio;\r\n\r\n");

		buffer.append("import pe.com.soul.core.modelo.Proceso;\r\n");
		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n");
		buffer.append("import javax.ejb.Local;\r\n\r\n");
		
		buffer.append("import "+proyectoBean.getPaquete()+".bean."+clasePadre+";\r\n\r\n");

		buffer.append("import pe.com.soul.core.servicio.BaseProcesoServicioLocal;\r\n\r\n");

		buffer.append("@Local\r\n");
		buffer.append("public interface "+procesoBean.getClase()+"ServicioLocal extends BaseProcesoServicioLocal{\r\n\r\n");
		buffer.append("\tProceso accionCrearInstancia(UsuarioPortal usuarioPortal, Object objeto) throws Exception;\r\n\r\n");
		buffer.append("\t"+clasePadre+" accionVerResumen(UsuarioPortal usuarioPortal, "+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception;\r\n\r\n");
		buffer.append("\t"+clasePadre+" accionVerDetalle(UsuarioPortal usuarioPortal, "+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception;\r\n\r\n");
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
