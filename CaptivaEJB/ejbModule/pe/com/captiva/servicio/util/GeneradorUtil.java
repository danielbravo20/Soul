package pe.com.captiva.servicio.util;

import pe.com.captiva.bean.AtributoProceso;

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
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && ( request.getParameter(\""+atributo.getWebNombre()+"\").equals(\"1\") || request.getParameter(\""+atributo.getWebNombre()+"\").equalsIgnoreCase(\"true\"))) {\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(true);}\r\n");
				buffer.append("\t\telse{\r\n");
				buffer.append("\t\t\t"+nombreClase+".set"+nombreVariable(atributo.getNombre())+"(false);\r\n");
				buffer.append("\t\t}\r\n");
			}else if(atributo.getTipo().equals("java.sql.Date")){
				buffer.append("\t\tif(request.getParameter(\""+atributo.getWebNombre()+"\")!=null && request.getParameter(\""+atributo.getWebNombre()+"\").length() > 0 ){\r\n");
				buffer.append("\t\t\ttry{\r\n");
				buffer.append("\t\t\t\tjava.text.SimpleDateFormat "+atributo.getNombre().toLowerCase()+"SDF = new java.text.SimpleDateFormat(\"dd/MM/yyyy\");\r\n");
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
}
