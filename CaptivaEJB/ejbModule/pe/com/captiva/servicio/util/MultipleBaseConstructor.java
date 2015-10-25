package pe.com.captiva.servicio.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import pe.com.captiva.bean.ProyectoBean;

public abstract class MultipleBaseConstructor {

	public void construir(ProyectoBean proyectoBean) throws Exception{
		
		List<Componente> componentes = crear(proyectoBean);
		for (int x = 0; x < componentes.size(); x++) {
			Componente componente = componentes.get(x);
			File directorio = crearDirectorio(componente.getDirectorio());
			crearContenido(directorio, componente.getArchivo(), componente.getContenido());
		}
		
	}
	
	public File crearDirectorio(String directorio) throws Exception{
		File directorioPaquete = new File(directorio);
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
	
	public abstract List<Componente> crear(ProyectoBean proyectoBean) throws Exception;
	
	public abstract boolean reemplazarArchivoCuandoExiste();
}
