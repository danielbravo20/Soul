package pe.com.captiva.servicio.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.captiva.bean.AtributoProceso;
import pe.com.captiva.bean.CampoSQLBean;
import pe.com.captiva.bean.TablaBean;

public class GeneradorUtil {
	
	public static String escribirAsignacionCampo(String nombreClase, AtributoProceso atributo){
		StringBuffer buffer = new StringBuffer();
				
		if(atributo.isJavaTieneValorOmision()==false){
			if(atributo.getTipo().equals("String")){
				buffer.append("\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(request.getParameter(\""+atributo.getWebNombre()+"\"));\r\n");
			}else if(atributo.getTipo().equals("int") || atributo.getTipo().equals("Integer")){
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && request.getParameter(\""+atributo.getWebNombre()+"\").trim().length()>0){\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(Integer.parseInt(request.getParameter(\""+atributo.getWebNombre()+"\")));\r\n");
				buffer.append("\t\t}\r\n");
			}else if(atributo.getTipo().equals("boolean")){
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && ( request.getParameter(\""+atributo.getWebNombre()+"\").equals(1) || request.getParameter(\""+atributo.getWebNombre()+"\").equalsIgnoreCase(true))) {\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(true);}\r\n");
				buffer.append("\t\telse{\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(false);\r\n");
				buffer.append("\t\t}\r\n");
			}else if(atributo.getTipo().equals("java.sql.Date")){
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && request.getParameter(\""+atributo.getWebNombre()+"\").length() > 0 ){\r\n");
				buffer.append("\t\t\ttry{\r\n");
				buffer.append("\t\t\t\tjava.text.SimpleDateFormat "+atributo.getNombre().toLowerCase()+"SDF = new java.text.SimpleDateFormat(dd/MM/yyyy);\r\n");
				buffer.append("\t\t\t\tjava.util.Date date"+nombreVariable(atributo.getNombre())+"= "+atributo.getNombre().toLowerCase()+"SDF.parse(request.getParameter(\""+atributo.getWebNombre()+"\"));\r\n");
				buffer.append("\t\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(new java.sql.Date(date"+nombreVariable(atributo.getNombre())+ ".getTime()));\r\n");
				buffer.append("\t\t\t}catch (java.text.ParseException pe) { \r\n");
				buffer.append("\t\t\t\tpe.printStackTrace();\r\n");
				buffer.append("\t\t\t}\r\n");
				buffer.append("\t\t}\r\n");
			}else if(atributo.getTipo().equals("java.math.BigDecimal")){
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && request.getParameter(\""+atributo.getWebNombre()+"\").trim().length()>0){\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(new java.math.BigDecimal(request.getParameter(\""+atributo.getWebNombre()+"\").trim()));\r\n");
				buffer.append("\t\t}\r\n");
			}else if(atributo.getTipo().equalsIgnoreCase("long")){
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && request.getParameter(\""+atributo.getWebNombre()+"\").trim().length()>0){\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(Long.parseLong(request.getParameter(\""+atributo.getWebNombre()+"\")));\r\n");
				buffer.append("\t\t}\r\n");
			}
		}else{
			buffer.append("\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"("+atributo.getWebValorOmision()+");\r\n");
		}
		return buffer.toString();
	}
	
	public static String nombreVariable(String cadena){
		if(cadena!=null){
			return cadena.substring(0, 1).toUpperCase() + cadena.substring(1);
		}else{
			return null;
		}
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
		
		buffer.append("ALTER TABLE "+tabla.getEsquema()+"."+tabla.getNombre()+" ADD CONSTRAINT PK_BFP_"+tabla.getNombre()+" PRIMARY KEY (");
		
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
	
	public static String crearDLLFK(TablaBean tabla){
		
		StringBuffer buffer = new StringBuffer();
		System.out.println(":: "+tabla.getNombre()+" - "+tabla.getCamposFK().size());
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
	
}
