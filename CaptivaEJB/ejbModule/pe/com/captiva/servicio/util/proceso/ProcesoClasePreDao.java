package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.AtributoBean;
import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.GeneradorUtil;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class ProcesoClasePreDao extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICE + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\servicio\\dao\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("Pre"+procesoBean.getClase()+"Dao.java");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		String clasePadre = proyectoBean.getClasePadre().getNombre();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio.dao;\r\n\r\n");

		buffer.append("import java.util.List;\r\n\r\n");
		
		buffer.append("import "+proyectoBean.getPaquete()+".bean.*;\r\n");
		buffer.append("import "+proyectoBean.getPaquete()+".dao.entity.*;\r\n\r\n");

		buffer.append("public abstract class Pre"+procesoBean.getClase()+"Dao extends BaseDao<"+clasePadre+"Entity>{\r\n\r\n");

		buffer.append("\tpublic PreEmisionDao() {\r\n");
		buffer.append("\t\tsuper(SolicitudEntity.class);\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\tpublic "+clasePadre+" registrar("+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\treturn parseRegistrar"+clasePadre+"(guardarEntity(parseRegistrar"+clasePadre+"Entity("+clasePadre.toLowerCase()+")));\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\tpublic "+clasePadre+" verResumen("+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\tif("+clasePadre.toLowerCase()+"!=null){\r\n");
		buffer.append("\t\t\tString consulta = \"select a from "+clasePadre+"Entity a where a.codigoProceso =:parametro \";\r\n");
		buffer.append("\t\t\tList<"+clasePadre+"Entity> "+clasePadre.toLowerCase()+"Entitys = buscarRegistros(consulta, \"parametro\", "+clasePadre.toLowerCase()+".getCodigoProceso());\r\n");	
		buffer.append("\t\t\tif("+clasePadre.toLowerCase()+"Entitys!=null){\r\n");
		buffer.append("\t\t\t\t"+clasePadre+"Entity "+clasePadre.toLowerCase()+"Entity = "+clasePadre.toLowerCase()+"Entitys.get(0);\r\n");
		
		List<AtributoBean> atributosResumenBean = procesoBean.getConsultaResumen().getAtributosBean();
		for (AtributoBean atributoBean : atributosResumenBean) {
			if(atributoBean.isCampoSQLBean()){
				buffer.append("\t\t\t\t"+clasePadre.toLowerCase()+".set"+GeneradorUtil.nombreVariable(atributoBean.getNombre())+"("+clasePadre.toLowerCase()+"Entity.get"+GeneradorUtil.nombreVariable(atributoBean.getNombre())+"());\r\n");
			}
		}
		
		buffer.append("\t\t\t}\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn "+clasePadre.toLowerCase()+";\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\tpublic "+clasePadre+" verDetalle("+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\tif("+clasePadre.toLowerCase()+"!=null){\r\n");
		buffer.append("\t\t\tString consulta = \"select a from "+clasePadre+"Entity a where a.codigoProceso =:parametro \";\r\n");
		buffer.append("\t\t\tList<"+clasePadre+"Entity> "+clasePadre.toLowerCase()+"Entitys = buscarRegistros(consulta, \"parametro\", "+clasePadre.toLowerCase()+".getCodigoProceso());\r\n");	
		buffer.append("\t\t\tif("+clasePadre.toLowerCase()+"Entitys!=null){\r\n");
		buffer.append("\t\t\t\t"+clasePadre+"Entity "+clasePadre.toLowerCase()+"Entity = "+clasePadre.toLowerCase()+"Entitys.get(0);\r\n");
		
		List<AtributoBean> atributosDetalleBean = procesoBean.getConsultaDetalle().getAtributosBean();
		for (AtributoBean atributoBean : atributosDetalleBean) {
			if(atributoBean.isCampoSQLBean()){
				String tipoLlamada = atributoBean.getTipo().equals("boolean")?"is":"get";
				buffer.append("\t\t\t\t"+clasePadre.toLowerCase()+".set"+GeneradorUtil.nombreVariable(atributoBean.getNombre())+"("+clasePadre.toLowerCase()+"Entity."+tipoLlamada+GeneradorUtil.nombreVariable(atributoBean.getNombre())+"());\r\n");
			}
		}
		
		buffer.append("\t\t\t}\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn "+clasePadre.toLowerCase()+";\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\tprivate "+clasePadre+"Entity parseRegistrar"+clasePadre+"Entity("+clasePadre+" "+clasePadre.toLowerCase()+"){\r\n");
		buffer.append("\t\t"+clasePadre+"Entity "+clasePadre.toLowerCase()+"Entity = null;\r\n");
		buffer.append("\t\tif("+clasePadre.toLowerCase()+"!=null){\r\n");
		buffer.append("\t\t\t"+clasePadre.toLowerCase()+"Entity = new "+clasePadre+"Entity();\r\n");
		
		//pone el codigo de proceso
		buffer.append("\t\t\t"+clasePadre.toLowerCase()+"Entity.setCodigoProceso("+clasePadre.toLowerCase()+".getCodigoProceso());\r\n");
		
		List<AtributoProceso> atributoProcesos = procesoBean.getAtributosEntrada();
		for (AtributoProceso atributoProceso : atributoProcesos) {
			if(atributoProceso.isCampoSQLBean()){
				String tipoLlamada = atributoProceso.getTipo().equals("boolean")?"is":"get";
				buffer.append("\t\t\t"+clasePadre.toLowerCase()+"Entity.set"+GeneradorUtil.nombreVariable(atributoProceso.getNombre())+"("+clasePadre.toLowerCase()+"."+tipoLlamada+GeneradorUtil.nombreVariable(atributoProceso.getNombre())+"());\r\n");
			}			
		}
		
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn "+clasePadre.toLowerCase()+"Entity;\r\n");
		buffer.append("\t}\r\n\r\n");
			
		buffer.append("\tprivate "+clasePadre+" parseRegistrar"+clasePadre+"("+clasePadre+"Entity "+clasePadre.toLowerCase()+"Entity){\r\n");
		buffer.append("\t\t"+clasePadre+" "+clasePadre.toLowerCase()+" = null;\r\n");
		buffer.append("\t\tif("+clasePadre.toLowerCase()+"Entity!=null){\r\n");
		buffer.append("\t\t\t"+clasePadre.toLowerCase()+" = new "+clasePadre+"();\r\n");
		
		//pone el codigo de proceso en la clase padre
		buffer.append("\t\t\t"+clasePadre.toLowerCase()+".setCodigoProceso("+clasePadre.toLowerCase()+"Entity.getCodigoProceso());\r\n");
		
		for (AtributoProceso atributoProceso : atributoProcesos) {
			if(atributoProceso.isCampoSQLBean()){
				String tipoLlamada = atributoProceso.getTipo().equals("boolean")?"is":"get";
				buffer.append("\t\t\t"+clasePadre.toLowerCase()+".set"+GeneradorUtil.nombreVariable(atributoProceso.getNombre())+"("+clasePadre.toLowerCase()+"Entity."+tipoLlamada+GeneradorUtil.nombreVariable(atributoProceso.getNombre())+"());\r\n");
			}			
		}
		
		buffer.append("\t\t}\r\n");
		buffer.append("\t\treturn "+clasePadre.toLowerCase()+";\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("}\r\n");
			
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
