package pe.com.captiva.servicio.util.general;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.TablaBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

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
		buffer.append(crearSequencial(tablasBean));
		buffer.append("\n\r");
		
		for (TablaBean tablaBean : tablasBean) {
			buffer.append(crearDLLTabla(tablaBean));
		}
		
		for (TablaBean tablaBean : tablasBean) {
			buffer.append(crearDLLFK(tablaBean));
		}
		
		return buffer;
	}
	
	public static String crearDLLFK(TablaBean tabla){
		
		StringBuffer buffer = new StringBuffer();
		
		List<CampoSQLBean> camposFK = tabla.getCamposFK();
		if(camposFK!=null && camposFK.size()>0){
			Map<Integer, TablaBean> tablasFK = new HashMap<Integer, TablaBean>();
			List<Integer> nombreTablas = new ArrayList<Integer>();
			for (int i = 0; i < camposFK.size(); i++) {
				CampoSQLBean campoFK = camposFK.get(i);
				if(tablasFK.containsKey(campoFK.getFk().getTabla().getCodigo())==false){
					tablasFK.put(campoFK.getFk().getTabla().getCodigo(), campoFK.getFk().getTabla());
					nombreTablas.add(campoFK.getFk().getTabla().getCodigo());
				}
			}
			
			
			for (int i = 0; i < nombreTablas.size(); i++) {
				TablaBean tablaFK = tablasFK.get(nombreTablas.get(i));
				buffer.append("ALTER TABLE "+tabla.getEsquema()+"."+tabla.getNombre()+" ADD CONSTRAINT FK_"+tabla.getEsquema()+"_"+tabla.getNombre()+" FOREIGN KEY ");
				StringBuffer camposA = new StringBuffer();
				StringBuffer camposB = new StringBuffer();
				for (int j = 0; j < camposFK.size(); j++) {
					CampoSQLBean campoSQLFK = camposFK.get(j);
					if(tablaFK.getCodigo()==campoSQLFK.getFk().getTabla().getCodigo()){
						if(j==0){
							camposA.append(campoSQLFK.getNombre());
							camposB.append(campoSQLFK.getFk().getNombre());
						}else{
							camposA.append(","+campoSQLFK.getNombre());
							camposB.append(","+campoSQLFK.getFk().getNombre());
						}
					}
				}
				
				buffer.append("("+camposA.toString()+") REFERENCES "+tablaFK.getEsquema()+"."+tablaFK.getNombre()+" ("+camposB.toString()+");\r\n\r\n");
			}
		}
		
		return buffer.toString();
	}

	public static String crearDLLTabla(TablaBean tabla){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("CREATE TABLE "+tabla.getEsquema()+"."+tabla.getNombre()+"(\r\n");
		
		//se ponen los campos PK
		for (int i = 0; i < tabla.getCamposPK().size(); i++) {
			CampoSQLBean campoSQL = tabla.getCamposPK().get(i);
			if(campoSQL.isTieneFuncion()==false && campoSQL.isFlgPK()==true){				
				if(campoSQL.getTipo().equalsIgnoreCase("VARCHAR") || campoSQL.getTipo().equalsIgnoreCase("CHAR") || campoSQL.getTipo().equalsIgnoreCase("CHARACTER")){
					buffer.append("\t\t"+campoSQL.getNombre()+" "+campoSQL.getTipo()+"("+campoSQL.getLongitud()+")");
				}else if(campoSQL.getTipo().equalsIgnoreCase("DECIMAL")){
					buffer.append("\t\t"+campoSQL.getNombre()+" "+campoSQL.getTipo()+"("+campoSQL.getLongitud()+", "+campoSQL.getPrecision()+")");
				}else{
					buffer.append("\t\t"+campoSQL.getNombre()+" "+campoSQL.getTipo());
				}
				buffer.append(" NOT NULL,\r\n");
			}
		}
		
		//Si la tabla esta asociado a la clase padre, debe tener el campo COD_PROCESO
		if(tabla.getClaseBean()!=null && tabla.getClaseBean().getNivel()==1){
			buffer.append("\t\tcodigo_proceso bigint NOT NULL,\r\n");
		}
		
		for (int i = 0; i < tabla.getCamposSQL().size(); i++) {
			CampoSQLBean campoSQL = tabla.getCamposSQL().get(i);
			if(campoSQL.isTieneFuncion()==false && campoSQL.isFlgPK()==false){
				if(campoSQL.getTipo().equalsIgnoreCase("VARCHAR") || campoSQL.getTipo().equalsIgnoreCase("CHAR") || campoSQL.getTipo().equalsIgnoreCase("CHARACTER")){
					buffer.append("\t\t"+campoSQL.getNombre()+" "+campoSQL.getTipo()+"("+campoSQL.getLongitud()+")");
				}else if(campoSQL.getTipo().equalsIgnoreCase("DECIMAL")){
					buffer.append("\t\t"+campoSQL.getNombre()+" "+campoSQL.getTipo()+"("+campoSQL.getLongitud()+", "+campoSQL.getPrecision()+")");
				}else{
					buffer.append("\t\t"+campoSQL.getNombre()+" "+campoSQL.getTipo());
				}
				if(campoSQL.isFlgObligatorio() || campoSQL.isFlgPK()){
					buffer.append(" NOT NULL");
				}
				buffer.append(",\r\n");
			}
		}
		
		buffer = new StringBuffer(buffer.substring(0, buffer.length()-3));
		buffer.append(");\r\n\r\n");
		
		buffer.append("ALTER TABLE "+tabla.getEsquema()+"."+tabla.getNombre()+" ADD CONSTRAINT pk_"+tabla.getNombre()+" PRIMARY KEY (");
		
		List<CampoSQLBean> camposPK = tabla.getCamposPK();
		for (int i = 0; i < camposPK.size(); i++) {
			CampoSQLBean campoSQL = camposPK.get(i);
			if(i==0){
				buffer.append(""+campoSQL.getNombre()+" ");
			}else{
				buffer.append(", "+campoSQL.getNombre()+" ");
			}
		}
		
		
		buffer.append(");\r\n\r\n");
		
		return buffer.toString();
		
	}
	
	public static String crearSequencial(List<TablaBean> tablasBean){
		
		Map<String, String> sequencialesMap = new HashMap<String, String>();
		List<String> sequencialesList = new ArrayList<String>();
		
		for (TablaBean tablaBean : tablasBean) {
			List<CampoSQLBean> campos = tablaBean.getCamposPK();
			for (CampoSQLBean campoSQLBean : campos) {
				String sequencial = campoSQLBean.getSequence();
				if(sequencialesMap.containsKey(sequencial)==false){
					sequencialesMap.put(sequencial, sequencial);
					sequencialesList.add(sequencial);
				}
			}
		}
		
		StringBuffer buffer = new StringBuffer();
		
		if(sequencialesList.size()>0){
			for (String sequencial : sequencialesList) {
				buffer.append("CREATE SEQUENCE "+sequencial+" INCREMENT 1 MINVALUE 0 MAXVALUE 9223372036854775807 START 1 CACHE 1;\r\n");
			}
		}
		
		return buffer.toString();
	}

	@Override
	public List<Componente> crear(Jpo jpo, ProyectoBean proyectoBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
