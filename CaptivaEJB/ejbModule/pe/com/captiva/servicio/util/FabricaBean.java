package pe.com.captiva.servicio.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.AtributoBean;
import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProyectoBean;

public class FabricaBean extends BaseFabrica{

	public Map<String, StringBuffer> crear(ProyectoBean proyectoBean) throws Exception{
		Map<String, StringBuffer> contenidos = new HashMap<String, StringBuffer>();
		
		List<ClaseBean> clases = proyectoBean.getClases();
		for (int x = 0; x < clases.size(); x++) {
			ClaseBean claseBean = clases.get(x);
			StringBuffer contenido = contenido(proyectoBean, claseBean);
			contenidos.put(claseBean.getNombre()+".java", contenido);
		}
		
		return contenidos;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ClaseBean clase){
		StringBuffer buffer = new StringBuffer();
		buffer.append("package "+proyectoBean.getPaquete()+ClaseBean.SUFIJO_PAQUETE+";\r\n");
		buffer.append("\r\n");
		
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean atributo = clase.getAtributos().get(i);
			if(atributo.isFlgLista()){
				buffer.append("import java.util.List;\r\n\r\n");
				break;
			}
		}
		buffer.append("import java.io.Serializable;\r\n\r\n");
		
		buffer.append("public class "+clase.getNombre()+" implements Serializable{\r\n \r\n");
		
		buffer.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
		
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean campo = clase.getAtributos().get(i);
			if(campo.isFlgLista()){
				buffer.append("\tprivate List<"+campo.getTipo()+"> "+campo.getNombre()+";\r\n");
			}else{
				buffer.append("\tprivate "+campo.getTipo()+" "+campo.getNombre()+";\r\n");
			}
		}
		
		buffer.append("\r\n");
		for (int i = 0; i < clase.getAtributos().size(); i++) {
			AtributoBean campo = clase.getAtributos().get(i);
			if(campo.isFlgLista()){
				if(campo.getTipo().equalsIgnoreCase("boolean")==false){
					buffer.append("\tpublic List<"+campo.getTipo()+"> get"+(campo.getNombre().substring(0, 1)).toUpperCase() + campo.getNombre().substring(1) +"(){\r\n");
					buffer.append("\t\treturn "+campo.getNombre()+";\r\n");
					buffer.append("\t}\r\n\r\n");
				}else{
					buffer.append("\tpublic List<"+campo.getTipo()+">() is"+(campo.getNombre().substring(0, 1)).toUpperCase() + campo.getNombre().substring(1) +"(){\r\n");
					buffer.append("\t\treturn "+campo.getNombre()+";\r\n");
					buffer.append("\t}\r\n\r\n");
				}
				buffer.append("\tpublic void set"+(campo.getNombre().substring(0, 1)).toUpperCase() + campo.getNombre().substring(1)+"(List<"+campo.getTipo()+"> "+campo.getNombre()+") {\r\n");
				buffer.append("\t\tthis."+campo.getNombre()+" = "+campo.getNombre()+";\r\n");
				buffer.append("\t}\r\n\r\n");
			}else{
				if(campo.getTipo().equalsIgnoreCase("boolean")==false){
					buffer.append("\tpublic "+campo.getTipo()+" get"+(campo.getNombre().substring(0, 1)).toUpperCase() + campo.getNombre().substring(1) +"(){\r\n");
					buffer.append("\t\treturn "+campo.getNombre()+";\r\n");
					buffer.append("\t}\r\n\r\n");
				}else{
					buffer.append("\tpublic "+campo.getTipo()+" is"+(campo.getNombre().substring(0, 1)).toUpperCase() + campo.getNombre().substring(1) +"(){\r\n");
					buffer.append("\t\treturn "+campo.getNombre()+";\r\n");
					buffer.append("\t}\r\n\r\n");
				}
				buffer.append("\tpublic void set"+(campo.getNombre().substring(0, 1)).toUpperCase() + campo.getNombre().substring(1)+"("+campo.getTipo()+" "+campo.getNombre()+") {\r\n");
				buffer.append("\t\tthis."+campo.getNombre()+" = "+campo.getNombre()+";\r\n");
				buffer.append("\t}\r\n\r\n");
			
			}
			
		}
		
		buffer.append("}");
		return buffer;
	}


	@Override
	public String definirDirectorio(ProyectoBean proyectoBean) throws Exception {
		return proyectoBean.getEquipoBean().getDirectorioWorkspace() + File.separatorChar + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_SERVICELIB + File.separatorChar + "src" + File.separatorChar + ( (proyectoBean.getPaquete()+ClaseBean.SUFIJO_PAQUETE).replace('.', File.separatorChar) );
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}
}
