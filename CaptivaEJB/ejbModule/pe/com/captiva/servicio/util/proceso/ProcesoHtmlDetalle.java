package pe.com.captiva.servicio.util.proceso;

import java.util.ArrayList;
import java.util.List;

import pe.com.captiva.bean.ProcesoBean;
import pe.com.captiva.bean.ProcesoDetalleBean;
import pe.com.captiva.bean.ProcesoDetalleSeccionBean;
import pe.com.captiva.bean.ProcesoDetalleSubSeccionBean;
import pe.com.captiva.bean.ProyectoBean;
import pe.com.captiva.servicio.util.Componente;
import pe.com.captiva.servicio.util.MultipleBaseConstructor;
import pe.com.mapeo.dao.Jpo;

public class ProcesoHtmlDetalle extends MultipleBaseConstructor{

	public List<Componente> crear(Jpo jpo,ProyectoBean proyectoBean) throws Exception{
		List<Componente> componentes = new ArrayList<Componente>();
		
		List<ProcesoBean> procesos = proyectoBean.getProcesos();
		for (int x = 0; x < procesos.size(); x++) {
			ProcesoBean procesoBean = procesos.get(x);
			if(procesoBean!=null){
				String directorio = proyectoBean.getEquipoBean().getDirectorioWorkspace() + "\\" + proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB + "\\WebContent\\portal\\procesos\\" + procesoBean.getAleas() + "\\proceso\\vista\\" ;
				
				Componente componente = new Componente();
				componente.setDirectorio(directorio);
				componente.setArchivo("detalleproceso.html");
				componente.setContenido(contenidoDetalleproceso(proyectoBean, procesoBean));
				
				componentes.add(componente);
			}
		}
		
		return componentes;
	}
	
	
	private StringBuffer contenidoDetalleproceso(ProyectoBean proyectoBean, ProcesoBean procesoBean){
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/preControlador/pre_detalleproceso.js\"></script>\r\n");
		buffer.append("<script type=\"text/javascript\" src=\"/"+proyectoBean.getProyecto() + ProyectoBean.SUFIJO_PROYECTO_WEB+"/portal/procesos/" + procesoBean.getAleas() + "/proceso/controlador/detalleproceso.js\"></script>\r\n");
		buffer.append("<div ng-controller=\"pre_detalleproceso\" class=\"panel panel-default\">\r\n");
		buffer.append("	<div ng-controller=\"detalleproceso\">\r\n");
		
		for (ProcesoDetalleBean procesoDetalleBean : procesoBean.getProcesoDetallesBean()) {
			/*
			procesoDetalleBean.getCodigoProceso();
			procesoDetalleBean.getCodSeccion();
			procesoDetalleBean.getCodSubSeccion();
			procesoDetalleBean.getCodProcesoDetalle();
			procesoDetalleBean.getCodAtributo();
			procesoDetalleBean.getWebEtiqueta();
			procesoDetalleBean.getWebNombre();
			*/
			System.out.println(procesoDetalleBean.getCodigoProceso()+"|"+procesoDetalleBean.getCodSeccion()+"|"+procesoDetalleBean.getCodSubSeccion());
			System.out.println(procesoDetalleBean.getCodProcesoDetalle()+"|"+procesoDetalleBean.getCodAtributo()+"|"+procesoDetalleBean.getWebEtiqueta()+"|"+procesoDetalleBean.getWebNombre());
			System.out.println(" | ----------------------------------");
			
		}
		System.out.println(procesoBean.getProcesoDetallesSeccionBean().size());
		for (ProcesoDetalleSeccionBean procesoDetalleSeccionBean : procesoBean.getProcesoDetallesSeccionBean()) {
			/*
			procesoDetalleBean.getCodigoProceso();
			procesoDetalleBean.getCodSeccion();
			procesoDetalleBean.getCodSubSeccion();
			procesoDetalleBean.getCodProcesoDetalle();
			procesoDetalleBean.getCodAtributo();
			procesoDetalleBean.getWebEtiqueta();
			procesoDetalleBean.getWebNombre();
			*/
			System.out.println(procesoDetalleSeccionBean.getCodigoProceso()+"|"+procesoDetalleSeccionBean.getCodSeccion()+"|"+procesoDetalleSeccionBean.getTipoSeccion());
			System.out.println(procesoDetalleSeccionBean.getTipoWidget()+"|"+procesoDetalleSeccionBean.getNombre()+"|"+procesoDetalleSeccionBean.getCodSeccionPadre());
			System.out.println(" || ----------------------------------");
			
		}
		
		for (ProcesoDetalleSubSeccionBean procesoDetalleSubSeccionBean : procesoBean.getProcesoDetallesSubSeccionBean()) {
			/*
			procesoDetalleBean.getCodigoProceso();
			procesoDetalleBean.getCodSeccion();
			procesoDetalleBean.getCodSubSeccion();
			procesoDetalleBean.getCodProcesoDetalle();
			procesoDetalleBean.getCodAtributo();
			procesoDetalleBean.getWebEtiqueta();
			procesoDetalleBean.getWebNombre();
			*/
			System.out.println(procesoDetalleSubSeccionBean.getCodigoProceso()+"|"+procesoDetalleSubSeccionBean.getCodSeccion()+"|"+procesoDetalleSubSeccionBean.getCodSubSeccion());
			System.out.println(procesoDetalleSubSeccionBean.getNombre());
			System.out.println(" ||| ----------------------------------");
			
		}
		
		buffer.append("	</div>\r\n");
		buffer.append("</div>");
		
		return buffer;
		
	}

	@Override
	public boolean reemplazarArchivoCuandoExiste() {
		return true;
	}


	@Override
	public List<Componente> crear(ProyectoBean proyectoBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}