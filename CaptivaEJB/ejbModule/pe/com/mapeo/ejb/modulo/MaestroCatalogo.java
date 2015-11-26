package pe.com.mapeo.ejb.modulo;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.mapeo.dao.Jpo;
import pe.com.mapeo.ejb.controller.GestionBase;

@Stateless
public class MaestroCatalogo extends GestionBase implements MaestroCatalogoLocal {

	public Object listar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> listados = new HashMap<String,Object>();
		listados.put("MAE_CATALOGO", jpo.tabla("mae_catalogo","CAT").ordenadoPor("cod_catalogo ASC").seleccionar("*"));
		listados.put("MAE_CATALOGO_PADRE", jpo.tabla("mae_catalogo","CAT").donde(" AND cabecera = '1'").ordenadoPor("cod_catalogo ASC").seleccionar("*"));
		return listados;
	}
	
	public Object eliminar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("mae_catalogo","CAT").eliminar();
		jpo.commitear();
		return true;
	}

	public Object editar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("mae_catalogo","CAT").editar();
		jpo.commitear();
		return true;
	}
	
	public Object registrar(Jpo jpo, HttpServletRequest request,HttpServletResponse response) throws Exception {
		jpo.tabla("mae_catalogo","CAT").registrar();
		jpo.commitear();
		return true;
	}
	
}