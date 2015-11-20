package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class ProcesoClaseDao extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICE + "\\" + "ejbModule" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\servicio\\dao\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo(procesoBean.getClase()+"Dao.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio.dao;\r\n\r\n");

		buffer.append("import javax.ejb.LocalBean;\r\n");
		buffer.append("import javax.ejb.Stateless;\r\n\r\n");

		buffer.append("@Stateless\r\n");
		buffer.append("@LocalBean\r\n");
		buffer.append("public class "+procesoBean.getClase()+"Dao extends Pre"+procesoBean.getClase()+"Dao implements "+procesoBean.getClase()+"DaoLocal {\r\n\r\n");
		buffer.append("}\r\n");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
