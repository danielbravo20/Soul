package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.ClaseBean;
import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.captiva.servicio.util.GeneradorUtil;

public class ProcesoClasePreUtil extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\" + "generado" + "\\" + (proyectoBean.getPaquete().replace('.', File.separatorChar) + "\\" + procesoBean.getClase().toLowerCase() + "\\controlador\\");
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("Pre"+procesoBean.getClase()+"Util.java");
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

		buffer.append("import pe.com.soul.core.modelo.MensajeValidacion;\r\n");
		buffer.append("import pe.com.soul.core.web.util.ValidacionUtil;\r\n");
		buffer.append("import pe.com.soul.core.web.util.ProcesoUtil;\r\n\r\n");
		
		buffer.append("import "+proyectoBean.getPaquete()+ClaseBean.SUFIJO_PAQUETE+".*;\r\n\r\n");
		
		buffer.append("public class Pre"+procesoBean.getClase()+"Util implements ProcesoUtil{\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic MensajeValidacion validacionCampos(HttpServletRequest request, HttpServletResponse response) {\r\n");
		buffer.append("\t\tMensajeValidacion mensajeValidacion = new MensajeValidacion();\r\n");
		
		//TODO incluir la validacion de los campos
		List<AtributoProceso> atributos = procesoBean.getAtributosEntrada();
		for (int i = 0; i < atributos.size(); i++) {
			AtributoProceso atributoProceso = atributos.get(i);
			String tab = "\t\t";
			if(atributoProceso.isWebFlgRequerido()){
				String metodoValidacion = "";
				if ("string".equals(atributoProceso.getTipo().toLowerCase())){
					metodoValidacion = "ValidacionUtil.cadenaNoValidaRequestParameter";
				}
				else if ("long".equals(atributoProceso.getTipo().toLowerCase())){
					metodoValidacion = "ValidacionUtil.longNoValidoRequestParameter";
				}
				else if ("java.util.Date".equals(atributoProceso.getTipo().toLowerCase())){
					metodoValidacion = "ValidacionUtil.dateNoValidoRequestParameter";
				}
				else if ("java.math.bigdecimal".equals(atributoProceso.getTipo().toLowerCase())){
					metodoValidacion = "ValidacionUtil.decimalNoValidoRequestParameter";
				}
				else if ("java.sql.timestamp".equals(atributoProceso.getTipo().toLowerCase())){
					metodoValidacion = "ValidacionUtil.timestampNoValidoRequestParameter";
				}
				if (metodoValidacion.length()>0){
					buffer.append(tab+"if ("+metodoValidacion+"(request.getParameter(\""+atributoProceso.getWebNombre()+"\"))){\n");
					buffer.append(tab+"\tmensajeValidacion.setConforme(false);\n");
					buffer.append(tab+"\tmensajeValidacion.setMensaje(\""+atributoProceso.getNombre()+" es invalido"+"\");\n");
					buffer.append(tab+"\treturn mensajeValidacion;\n");
					buffer.append(tab+"}\n\n");
				}
			}
		}
		
		buffer.append("\t\tmensajeValidacion.setConforme(true);\r\n");
		buffer.append("\t\treturn mensajeValidacion;\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object poblarObjetos(HttpServletRequest request, HttpServletResponse response) {\r\n");
		ClaseBean clasePadre = proyectoBean.getClasePadre();
		if(clasePadre!=null){
			String nombreClase = clasePadre.getNombre();
			String nombreObjeto = nombreClase.toLowerCase();
			
			List<AtributoProceso> atributosProceso = procesoBean.getAtributosEntrada();
			
			//agrupo las clases por su clase
			Map<Integer, List<AtributoProceso>> atributosXClaseMap = new HashMap<Integer, List<AtributoProceso>>();
			Map<Integer, ClaseBean> claseMap = new HashMap<Integer, ClaseBean>();
			for (AtributoProceso atributoProceso : atributosProceso) {
				if(atributosXClaseMap.containsKey(atributoProceso.getClase().getCodigoClase())){
					atributosXClaseMap.get(atributoProceso.getClase().getCodigoClase()).add(atributoProceso);
				}else{
					List<AtributoProceso> atributosNuevaClase = new ArrayList<AtributoProceso>();
					atributosNuevaClase.add(atributoProceso);
					atributosXClaseMap.put(atributoProceso.getClase().getCodigoClase(), atributosNuevaClase);
					claseMap.put(atributoProceso.getClase().getCodigoClase(), atributoProceso.getClase());
				}
			}
			
			buffer.append("\t\t"+nombreClase+" "+nombreObjeto+" = new "+nombreClase+"();\r\n");

			//se empieza a escribir por la clase padre
			if(atributosXClaseMap.containsKey(clasePadre.getCodigoClase())){
				List<AtributoProceso> atributosClasePadre = atributosXClaseMap.get(clasePadre.getCodigoClase());
				for (AtributoProceso atributoProceso : atributosClasePadre) {
					buffer.append(GeneradorUtil.escribirAsignacionCampo(nombreObjeto, atributoProceso));
				}
			}
			
			Set<Integer> codigoClaseSet = atributosXClaseMap.keySet();
			Iterator<Integer> codigoClaseIterator = codigoClaseSet.iterator();
			while (codigoClaseIterator.hasNext()) {
				Integer codigoClase = (Integer) codigoClaseIterator.next();
				if(codigoClase!=clasePadre.getCodigoClase()){
					ClaseBean claseHija = claseMap.get(codigoClase);
					String nombreClaseHija = claseHija.getNombre();
					String objetoClaseHija = nombreClaseHija.toLowerCase();
					buffer.append("\t\t"+nombreClaseHija+" "+nombreClaseHija.toLowerCase()+" = new "+nombreClaseHija+"();\r\n");
					List<AtributoProceso> atributosClaseHija = atributosXClaseMap.get(codigoClase);
					for (AtributoProceso atributoProceso : atributosClaseHija) {
						buffer.append(GeneradorUtil.escribirAsignacionCampo(objetoClaseHija, atributoProceso));
					}
					buffer.append("\t\t"+nombreObjeto+".set"+nombreClaseHija+"("+nombreClaseHija.toLowerCase()+");\r\n");
				}
			}
			
			buffer.append("\t\treturn "+nombreClase.toLowerCase()+";\r\n");
		}else{
			buffer.append("\t\treturn null;\r\n");
		}
		buffer.append("\t}\r\n");
		
		
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic MensajeValidacion validacionCamposVerResumen(HttpServletRequest request, HttpServletResponse response) {\r\n");
		buffer.append("\t\tMensajeValidacion mensajeValidacion = new MensajeValidacion();\r\n");
		buffer.append("\t\tif (ValidacionUtil.longNoValidoRequestParameter(request.getParameter(\"codigoProceso\"))){\r\n");
		buffer.append("\t\t\tmensajeValidacion.setConforme(false);\r\n");
		buffer.append("\t\t\tmensajeValidacion.setMensaje(\"ingrese el codigo de proceso\");\r\n");
		buffer.append("\t\t\treturn mensajeValidacion;\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\tmensajeValidacion.setConforme(true);\r\n");
		buffer.append("\t\treturn mensajeValidacion;\r\n");
		buffer.append("\t}\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object poblarObjetosVerResumen(HttpServletRequest request, HttpServletResponse response) {\r\n");
		if(clasePadre!=null){
			String nombreClase = clasePadre.getNombre();
			String nombreObjeto = nombreClase.toLowerCase();
			buffer.append("\t\t"+nombreClase+" "+nombreObjeto+" = new "+nombreClase+"();\r\n");
			buffer.append("\t\t"+nombreObjeto+".setCodigoProceso(new Long(request.getParameter(\"codigoProceso\").trim()));\r\n");
			buffer.append("\t\treturn "+nombreObjeto+";\r\n");
		}else{
			buffer.append("\t\treturn null;\r\n");
		}
		buffer.append("\t}\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic MensajeValidacion validacionCamposVerDetalle(HttpServletRequest request, HttpServletResponse response) {\r\n");
		buffer.append("\t\tMensajeValidacion mensajeValidacion = new MensajeValidacion();\r\n");
		buffer.append("\t\tif (ValidacionUtil.longNoValidoRequestParameter(request.getParameter(\"codigoProceso\"))){\r\n");
		buffer.append("\t\t\tmensajeValidacion.setConforme(false);\r\n");
		buffer.append("\t\t\tmensajeValidacion.setMensaje(\"ingrese el codigo de proceso\");\r\n");
		buffer.append("\t\t\treturn mensajeValidacion;\r\n");
		buffer.append("\t\t}\r\n");
		buffer.append("\t\tmensajeValidacion.setConforme(true);\r\n");
		buffer.append("\t\treturn mensajeValidacion;\r\n");
		buffer.append("\t}\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic Object poblarObjetosVerDetalle(HttpServletRequest request, HttpServletResponse response) {\r\n");
		if(clasePadre!=null){
			String nombreClase = clasePadre.getNombre();
			String nombreObjeto = nombreClase.toLowerCase();
			buffer.append("\t\t"+nombreClase+" "+nombreObjeto+" = new "+nombreClase+"();\r\n");
			buffer.append("\t\t"+nombreObjeto+".setCodigoProceso(new Long(request.getParameter(\"codigoProceso\").trim()));\r\n");
			buffer.append("\t\treturn "+nombreObjeto+";\r\n");
		}else{
			buffer.append("\t\treturn null;\r\n");
		}
		
		buffer.append("\t}\r\n");
		
		
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
