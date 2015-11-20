package pe.com.mapeo.dao;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Tabla {
	
	private static String Tipo_Where = "W";
	//private static String Tipo_WhereLike = "L";
	private static String Tipo_WhereIN = "I";
	
	private String nombreTabla;
	private LinkedHashMap<String,String> data = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,Object> dataTipo = new LinkedHashMap<String,Object>();
	private LinkedHashMap<String,Object> dataMultiple = new LinkedHashMap<String,Object>();
	private ConexionBD conexionBD = null;
	
	private String dondePersonalizado = "";
	private String ordenadoPor = "";
	private int limite = 0;
	
	public Tabla(Connection c,String nombreTabla,Object data,Object dataMultiple,Object dataTipo) {
		conexionBD = new ConexionBD(c);
		this.nombreTabla = nombreTabla;
		if(data!=null){
			this.data = (LinkedHashMap<String, String>) data;
		}
		if(dataTipo!=null){
			this.dataTipo = (LinkedHashMap<String, Object>) dataTipo;
		}
		if(dataMultiple!=null){
			this.dataMultiple = (LinkedHashMap<String, Object>) dataMultiple;
		}
	}
	
	public Object editar() throws Exception{
		String query = "UPDATE "+nombreTabla+" SET "+getValuesUpdate()+" "+getWheres();
		return conexionBD.dml(query);
	}
	
	public Object eliminar() throws Exception{
		String query = "DELETE FROM "+nombreTabla+" "+getWheres();
		return conexionBD.dml(query);
	}
	
	
	public Object registrarMultiple() throws Exception{
		String[] querys = new String[dataMultiple.size()];
		int contador = 0;
		
		for (Entry<String, Object> entry : dataMultiple.entrySet()) {
			querys[contador] = "INSERT INTO "+nombreTabla+" "+getValuesInsert((LinkedHashMap<String, String>) entry.getValue());
	    	System.out.println("QUERY MULTIPLE : "+querys[contador]);
	    	contador++;
		}
		return conexionBD.dml(querys);
	}
	
	public Object registrar() throws Exception{
		String query = "INSERT INTO "+nombreTabla+" "+getValuesInsert();
		return conexionBD.dml(query);
	}
	
	public Object obtener(String valores) throws Exception{
		String query = "SELECT "+valores+" FROM "+nombreTabla+" "+ getWheres() +" "+ getOrden();
		return conexionBD.obtener(query);
	}
	
	public Tabla donde(String where) throws Exception{
		this.dondePersonalizado = where;
		return this;
	}
	
	public Tabla ordenadoPor(String ordenadoPor) throws Exception{
		this.ordenadoPor = ordenadoPor;
		return this;
	}
	
	public Tabla limite(int limite) throws Exception{
		this.limite = limite;
		return this;
	}
	
	public Object seleccionar(String valores) throws Exception{
		String query = "SELECT "+valores+" FROM "+nombreTabla+" "+getWheres() +" "+ getOrden()+" "+ getLimite();
		return conexionBD.seleccionar(query);
	}
	
	public int contar() throws Exception{
		String query = "SELECT COUNT(*) AS COUNT FROM "+nombreTabla+" "+getWheres();
		return conexionBD.contarRegistro(query);
	}
	
	private String getValuesUpdate(){
		if(data.size()>0){
			
			StringBuilder campos = new StringBuilder("");
			
			for (Map.Entry<String, String> entry : data.entrySet()) {
				String campo = entry.getKey();
		    	String valor = entry.getValue();
		    	if(valor.equals("IS_NULL")){
		    		campos.append(campo+"= NULL, ");
		    	} else {
		    		campos.append(campo+"='"+valor+"', ");
		    	}
			}
			
			String camposString = campos.toString();
			
			return camposString.substring(0,camposString.length()-2);
		} else {
			return "";
		}
	}
	
	private String getValuesInsert(){
		return getValuesInsert(data);
	}
	
	private String getValuesInsert(LinkedHashMap<String,String> data){
		if(data.size()>0){
			StringBuilder campos = new StringBuilder("");
			StringBuilder valores = new StringBuilder("");
			
			for (Entry<String, String> entry : data.entrySet()) {
				String campo = entry.getKey();
		    	String valor = entry.getValue();
		    	campos.append(campo+",");
		    	valores.append("'"+valor+"',");
			}
			String camposString = campos.toString();
			String valorString = valores.toString();
			camposString = "("+camposString.substring(0,camposString.length()-1)+")";
			valorString = "("+valorString.substring(0,valorString.length()-1)+")";
			return camposString+" VALUES "+valorString;
		} else {
			return "";
		}
	}
	
	private String getOrden(){
		if(ordenadoPor.length()>0){
			return "ORDER BY "+ordenadoPor;
		} else {
			return "";
		}
	}
	
	private String getLimite(){
		if(limite>0){
			return "LIMIT "+limite;
		} else {
			return "";
		}
	}
	
	private String getFormatoCS(String valor){
		String resultado = "";
		String[] valores = valor.split(",");
		if(valores.length>0){
			for(int i = 0;i <valores.length; i++){
				resultado += "'"+valores[i]+"',";
			}
			resultado = resultado.substring(0,resultado.length()-1);
		}
		return resultado;
	}
		
	private String getWheres(){
		String resultado = "";
		
		Object tipoWheres = dataTipo.get(Tipo_Where);
		Object tipoWheresIn = dataTipo.get(Tipo_WhereIN);
		
		if(tipoWheres!=null || tipoWheresIn!=null){
			resultado += "WHERE ";
		}
			
		if(tipoWheres!=null){
			StringBuilder where = new StringBuilder("");
			Map<String,String> wheres = (Map<String, String>) tipoWheres;
			for (Map.Entry<String, String> entry : wheres.entrySet()) {
				String campo = entry.getKey();
		    	String valor = entry.getValue();
		    	where.append(campo+" = '"+valor+"' AND ");
			}
			String whereStr = where.toString();
			resultado += whereStr.substring(0,whereStr.length()-5);
		}
		
		if(tipoWheresIn!=null){
			StringBuilder where = new StringBuilder("");
			Map<String,String> wheres = (Map<String, String>) tipoWheresIn;
			for (Map.Entry<String, String> entry : wheres.entrySet()) {
				String campo = entry.getKey();
		    	String valor = entry.getValue();
		    	where.append(campo+" IN ("+getFormatoCS(valor)+") AND ");
			}
			String whereStr = where.toString();
			resultado += whereStr.substring(0,whereStr.length()-5);
		}
		
		if(dondePersonalizado.length()>0){
			if(resultado.length()>0){
				resultado += dondePersonalizado;
			} else {
				resultado += "WHERE "+dondePersonalizado;;
			}
		}
		
		return resultado;
	}
	
	public String getData(String campo){
		if(data.get(campo)!=null){
			return data.get(campo);
		}
		return "";
	}
	
	public void setData(String campo,String valor){
		data.put(campo, valor);
	}

	public void removeData(String campo){
		data.remove(campo);
	}
	
	public String getDataWhere(String campo){
		Object tipoWheres = dataTipo.get(Tipo_Where);
		if(tipoWheres!=null){
			Map<String,String> wheres = (Map<String, String>) tipoWheres;
			if(wheres.get(campo)!=null){
				return wheres.get(campo);
			}
		}
		return "";
	}
	
	public void setDataWhere(String campo,String valor){
		Object tipoWheres = dataTipo.get(Tipo_Where);
		if(tipoWheres==null){
			dataTipo.put(Tipo_Where, new LinkedHashMap<String,Object>());
			tipoWheres = dataTipo.get(Tipo_Where);
		}
		if(tipoWheres!=null){
			Map<String,String> wheres = (Map<String, String>) tipoWheres;
			wheres.put(campo, valor);
			dataTipo.put("W", wheres);
		}
	}
	
	public void removeDataWhere(String campo){
		Object tipoWheres = dataTipo.get(Tipo_Where);
		if(tipoWheres!=null){
			Map<String,String> wheres = (Map<String, String>) tipoWheres;
			wheres.remove(campo);
			dataTipo.put("W", wheres);
		}
	}
	
}