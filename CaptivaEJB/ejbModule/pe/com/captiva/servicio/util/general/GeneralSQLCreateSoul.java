package pe.com.captiva.servicio.util.general;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TablaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.captiva.servicio.util.GeneradorUtil;

public class GeneralSQLCreateSoul extends MultipleBaseConstructor{

	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}
	
	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<TablaBean> tablas = proyectoBean.getTablas();
		
		Componente componente = new Componente();
		componente.setDirectorio(proyectoBean.getEquipoBean().getDirectorioParcial() + File.separatorChar + ProyectoBean.SUFIJO_SQL + File.separatorChar );
		componente.setArchivo("create_soul_"+proyectoBean.getProyecto().trim().toLowerCase()+".sql");
		componente.setContenido(contenido(proyectoBean, tablas));
		componentes.add(componente);
		
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, List<TablaBean> tablasBean){
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\r");
		for (TablaBean tablaBean : tablasBean) {
			buffer.append(GeneradorUtil.crearDLLTabla(tablaBean));
		}
		
		for (TablaBean tablaBean : tablasBean) {
			buffer.append(GeneradorUtil.crearDLLFK(tablaBean));
		}
		
		return buffer;
	}
	
	
}
