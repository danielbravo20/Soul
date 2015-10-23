package pe.com.captiva.servicio.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;

import pe.com.captiva.bean.ProyectoBean;

public abstract class BaseFabrica {

	public void construir(ProyectoBean proyectoBean) throws Exception{
		
		File directorio = crearDirectorio(proyectoBean);
		
		Map<String, StringBuffer> map = crear(proyectoBean);
		Iterator<String> iteratorKey = map.keySet().iterator();
		while (iteratorKey.hasNext()) {
			String nombreArchivo = (String) iteratorKey.next();
			crearContenido(directorio, nombreArchivo, map.get(nombreArchivo));
		}
	}
	
	public File crearDirectorio(ProyectoBean proyectoBean) throws Exception{
		File directorioPaquete = new File(definirDirectorio(proyectoBean));
		if(directorioPaquete.exists()){
			if(directorioPaquete.isDirectory()==false){
				throw new Exception("La ruta "+directorioPaquete.getAbsolutePath()+", no es un directorio...");
			}
		}else{
			directorioPaquete.mkdirs();
		}
		return directorioPaquete;
	}
	
	public void crearContenido(File directorio, String nombreArchivo, StringBuffer stringBuffer) throws Exception{
		File archivo = new File(directorio, nombreArchivo);
		
		//si el archivo existe y se indica que puede reemplazarse, se elimina el archivo
		if(archivo.exists() && reemplazarArchivoCuandoExiste()){
			archivo.delete();
		}
		
		//Si el archivo no existe se crea
		if(archivo.exists()==false){
			archivo.createNewFile();
			
			BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo,true), "ISO-8859-1"));
			bufferedWriter.write(stringBuffer.toString());
			bufferedWriter.close();
		}
	}
	
	public abstract Map<String, StringBuffer> crear(ProyectoBean proyectoBean) throws Exception;
	
	public abstract String definirDirectorio(ProyectoBean proyectoBean) throws Exception;
	
	public abstract boolean reemplazarArchivoCuandoExiste();
}
