package pe.com.captiva.servicio.util.general;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import pe.com.captiva.bean.AtributoBean;
import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.GeneradorUtil;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class GeneralClaseEntity extends MultipleBaseConstructor{

	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}
	
	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ClaseBean> clases = proyectoBean.getClases();
		for (int x = 0; x < clases.size(); x++) {
			ClaseBean claseBean = clases.get(x);
			if(claseBean.isTablaBean()){
				Componente componente = new Componente();
				componente.setDirectorio(proyectoBean.getEquipoBean().getDirectorioWorkspace() + File.separatorChar + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_DAO + File.separatorChar + "src" + File.separatorChar + ( proyectoBean.getPaquete().replace('.', File.separatorChar) ) + File.separatorChar + "dao" + File.separatorChar + "entity" );
				componente.setArchivo(claseBean.getNombre()+"Entity.java");
				componente.setContenido(contenido(proyectoBean, claseBean));
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ClaseBean clase){
		StringBuffer buffer = new StringBuffer();
		buffer.append("package "+proyectoBean.getPaquete()+".dao.entity;\r\n");
		buffer.append("\r\n");
		
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean atributo = clase.getAtributos().get(i);
			if(atributo.isFlgLista()){
				buffer.append("import java.util.List;\r\n\r\n");
				break;
			}
		}
		buffer.append("import java.io.Serializable;\r\n\r\n");
		buffer.append("import javax.persistence.Column;\r\n");
		buffer.append("import javax.persistence.Entity;\r\n");
		buffer.append("import javax.persistence.Id;\r\n");
		
		boolean noExistePK = true;
		boolean noExisteDate = true;
		
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean atributoBean = clase.getAtributos().get(i);
			if(atributoBean.isCampoSQLBean()){
				CampoSQLBean campoSQLBean = atributoBean.getCampoSQLBean(); 
				if(campoSQLBean.isFlgPK() && campoSQLBean.isSequence() && noExistePK){
					buffer.append("import javax.persistence.GeneratedValue;\r\n");
					buffer.append("import javax.persistence.SequenceGenerator;\r\n");
					noExistePK = false;
				}
				
				if(campoSQLBean.getTipo().equalsIgnoreCase("DATE") && noExisteDate){
					buffer.append("import javax.persistence.Temporal;\r\n");
					buffer.append("import javax.persistence.TemporalType;\r\n");
					noExisteDate = false;
				}
			}
		}
		
		buffer.append("import javax.persistence.Table;\r\n\r\n");
		
		buffer.append("@Entity\r\n");
		buffer.append("@Table(name = \""+clase.getTablaBean().getNombre()+"\", schema = \""+clase.getTablaBean().getEsquema()+"\")\r\n");
		buffer.append("public class "+clase.getNombre()+"Entity implements Serializable{\r\n\r\n");
		
		buffer.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
		
		if(clase.getNivel()==1){
			buffer.append("\tprivate Long codigoProceso;\r\n");
		}
		
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean campo = clase.getAtributos().get(i);
			if(campo.isCampoSQLBean()){
				buffer.append("\tprivate "+campo.getTipo()+" "+campo.getNombre()+";\r\n");
			}
		}
		
		buffer.append("\r\n");
		
		if(clase.getNivel()==1){
			buffer.append("\tpublic Long getCodigoProceso() {\r\n");
			buffer.append("\t\treturn codigoProceso;\r\n");
			buffer.append("\t}\r\n");
			buffer.append("\t@Column(name = \"codigo_proceso\" ,unique = true ,nullable = false )\r\n");
			buffer.append("\tpublic void setCodigoProceso(Long codigoProceso) {\r\n");
			buffer.append("\t\tthis.codigoProceso = codigoProceso;\r\n");
			buffer.append("\t}\r\n");
		}
		
		buffer.append("\r\n");
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean atributoBean = clase.getAtributos().get(i);
			if(atributoBean.isCampoSQLBean()){
				buffer.append(GeneradorUtil.escribeAnotacionJPA(atributoBean));
				if(atributoBean.getTipo().equalsIgnoreCase("boolean")==false){
					buffer.append("\tpublic "+atributoBean.getTipo()+" get"+(atributoBean.getNombre().substring(0, 1)).toUpperCase() + atributoBean.getNombre().substring(1) +"(){\r\n");
					buffer.append("\t\treturn "+atributoBean.getNombre()+";\r\n");
					buffer.append("\t}\r\n\r\n");
				}else{
					buffer.append("\tpublic "+atributoBean.getTipo()+" is"+(atributoBean.getNombre().substring(0, 1)).toUpperCase() + atributoBean.getNombre().substring(1) +"(){\r\n");
					buffer.append("\t\treturn "+atributoBean.getNombre()+";\r\n");
					buffer.append("\t}\r\n\r\n");
				}
				buffer.append("\tpublic void set"+(atributoBean.getNombre().substring(0, 1)).toUpperCase() + atributoBean.getNombre().substring(1)+"("+atributoBean.getTipo()+" "+atributoBean.getNombre()+") {\r\n");
				buffer.append("\t\tthis."+atributoBean.getNombre()+" = "+atributoBean.getNombre()+";\r\n");
				buffer.append("\t}\r\n\r\n");
			
			}
			
		}
		
		buffer.append("}");
		return buffer;
	}

}
