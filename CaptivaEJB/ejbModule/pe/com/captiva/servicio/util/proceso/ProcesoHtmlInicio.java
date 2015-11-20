package pe.com.captiva.servicio.util.proceso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.bean.RolBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;

public class ProcesoHtmlInicio extends MultipleBaseConstructor{

	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\" + procesoBean.getAleas() + "\\proceso\\vista\\" ;
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("iniciarproceso.html");
				componente.setContenido(contenido(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenido(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/preControlador/pre_iniciarproceso.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/controlador/iniciarproceso.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_iniciarproceso\" class=\"panel panel-default\">\r\n");
		buffer.append("\t<div ng-controller=\"iniciarproceso\">\r\n");
		buffer.append("\t\t<div class=\"panel-heading\"><strong>Datos Principales</strong></div>\r\n");
		buffer.append("\t\t\t<table class=\"table table-striped\">\r\n");
		buffer.append("\t\t\t\t<tbody>\r\n");
		buffer.append("\t\t\t\t\t<tr>\r\n");
		buffer.append("\t\t\t\t\t\t<th><span class=\"frm_requerido\">(*)</span> Tipo de Fianza :</th>\r\n");
		buffer.append("\t\t\t\t\t\t\t<td class=\"form-group\" show-errors='{showSuccess: true}'>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t<select name=\"tipoBusqueda\" ng-model=\"baseIPConfig.data.tipoFianza\" class=\"form-control input-sm\" required>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t\t<option value=\"LIC\">LICITACION</option>\r\n");
		buffer.append("\t\t\t\t\t\t\t\t\t<option value=\"REQ\">REQUERIMIENTO</option>\r\n");
		/*
						
						
						
					</select>\r\n");
					<p class="help-block" ng-if="frm_iniciarproceso.tipoBusqueda.$error.required">Seleccione un valor correcto</p>\r\n");
				</td>\r\n");
			  </tr>\r\n");
			  <tr>\r\n");
				<th><span class="frm_requerido">(*)</span> Monto :</th>\r\n");
				<td class="form-inline">\r\n");
					<span class="form-group" show-errors='{showSuccess: true}'>\r\n");
						<select name="monedaFianza" ng-model="baseIPConfig.data.monedaFianza" class="form-control input-sm" required>\r\n");
							<option value="USD">DÓLARES AMERICANOS</option>\r\n");
							<option value="PEN">NUEVOS SOLES</option>\r\n");
						</select>\r\n");
						<p class="help-block" ng-if="frm_iniciarproceso.monedaFianza.$error.required">Seleccione un valor correcto</p>\r\n");
					</span>\r\n");
					<span class="form-group" show-errors='{showSuccess: true}'>\r\n");
						<input type="number" name="montoFianza" ng-model="baseIPConfig.data.montoFianza" class="form-control input-sm" required>\r\n");
						<p class="help-block" ng-if="frm_iniciarproceso.montoFianza.$error.required">Ingrese un valor correcto</p>\r\n");
					</span>\r\n");
				</td>\r\n");
			  </tr>\r\n");
		    </tbody>\r\n");
		</table>\r\n");
	</div>\r\n");
</div>\r\n");
		*/
		 
		String clasePadre = proyectoBean.getClasePadre().getNombre();
		
		buffer.append("package "+proyectoBean.getPaquete()+"."+procesoBean.getClase().toLowerCase()+".servicio;\r\n\r\n");

		buffer.append("import javax.ejb.LocalBean;\r\n");
		buffer.append("import javax.ejb.Stateless;\r\n\r\n");

		buffer.append("import javax.annotation.security.RolesAllowed;\r\n");
		
		buffer.append("import pe.com.soul.core.modelo.Proceso;\r\n");
		buffer.append("import pe.com.soul.core.modelo.UsuarioPortal;\r\n\r\n");
		
		buffer.append("import "+proyectoBean.getPaquete()+".bean."+clasePadre+";\r\n\r\n");
		
		buffer.append("@Stateless\r\n");
		buffer.append("@LocalBean\r\n");
		buffer.append("public class "+procesoBean.getClase()+"Servicio extends Pre"+procesoBean.getClase()+"Servicio implements "+procesoBean.getClase()+"ServicioLocal {\r\n\r\n");
		
		StringBuffer rol = new StringBuffer();
		List<RolBean> roles = procesoBean.getRolPotencial();
		for (int x = 0; x < roles.size(); x++) {
			if(x!=0){
				rol.append(" ,");
			}
			rol.append("\""+roles.get(x).getRol()+"\"");
		}
		
		buffer.append("\t@RolesAllowed("+rol.toString()+")\r\n");
		buffer.append("\tpublic Proceso accionCrearInstancia(UsuarioPortal usuarioPortal, Object objeto) throws Exception {\r\n");
		buffer.append("\t\treturn super.accionCrearInstancia(usuarioPortal, objeto);\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic "+clasePadre+" accionVerResumen(UsuarioPortal usuarioPortal, "+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\treturn super.accionVerResumen(usuarioPortal, "+clasePadre.toLowerCase()+");\r\n");
		buffer.append("\t}\r\n\r\n");

		buffer.append("\t@Override\r\n");
		buffer.append("\tpublic "+clasePadre+" accionVerDetalle(UsuarioPortal usuarioPortal, "+clasePadre+" "+clasePadre.toLowerCase()+") throws Exception {\r\n");
		buffer.append("\t\treturn super.accionVerDetalle(usuarioPortal, "+clasePadre.toLowerCase()+");\r\n");
		buffer.append("\t}\r\n\r\n");
		
		buffer.append("}");
		
		return buffer;
	}


	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}

}
