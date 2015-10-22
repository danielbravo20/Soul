package pe.com.mapeo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MapeoUtil {
	
	public static String getMD5(String original){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static File crearFolder(String directorio) throws Exception{
		//System.out.print("Directorio : "+directorio+" | ");
		File directorioWorkspaceFile = new File(directorio);
		if(!directorioWorkspaceFile.exists()){
			System.out.print("No existe, lo creamos");
			directorioWorkspaceFile.mkdirs();
		} else {
			if(!directorioWorkspaceFile.isDirectory()){
				//throw new Exception("El archivo: "+directorio+", no es un directorio...");
				directorioWorkspaceFile.delete();
				directorioWorkspaceFile.mkdirs();
				System.out.println("Borramos y creamos el directorio : "+directorio);
			} else {
				System.out.print("es un folder lo dejamos como esta");
			}
		}
		//System.out.print("\n");
		return directorioWorkspaceFile;
	}
	
	public static File crearArchivo(String directorio,String nombre) throws Exception{
		File archivoProcesoController = new File(directorio+"\\"+nombre);
		if(archivoProcesoController.exists()){
			archivoProcesoController.delete();
		}
		archivoProcesoController.createNewFile();
		return archivoProcesoController;
	}
	
	public static File crearArchivo(String directorio,String nombre,String contenido, String tipoCodificacion) throws Exception{
		//System.out.println("Directorio : "+directorio+" | Archivo : "+nombre);
		File archivoProcesoController = new File(directorio+"\\"+nombre);
		if(archivoProcesoController.exists()){
			archivoProcesoController.delete();
		}
		archivoProcesoController.createNewFile();
		BufferedWriter bwProcesoController =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivoProcesoController,true), tipoCodificacion));
		bwProcesoController.write(contenido);
		bwProcesoController.close();
		return archivoProcesoController;
	}
	
	public static File crearArchivo(String directorio,String nombre,String contenido) throws Exception{
		return crearArchivo(directorio,nombre,contenido, "ISO-8859-1");
	}
	
	public static String izquierda(String tipo,String valor){
		if(valor.length()>getLongitud(tipo)){
			return valor;
		} else {
			return getVacios(getLongitud(tipo)-valor.length())+valor;
		}
	}
	
	public static String derecha(String tipo,String valor){
		if(valor.length()>getLongitud(tipo)){
			return valor;
		} else {
			return valor+getVacios(getLongitud(tipo)-valor.length());
		}
	}
	
		private static int getLongitud(String tipo){
			int cantidad = 50;
			if(tipo.equals("BD")){
				cantidad = 30;
			}
			return cantidad;
		}
		
		private static String getVacios(int longitud){
			String resultadoFinal = "";
			for(int i=0;i<longitud;i++){
				resultadoFinal+=" ";
			}
			return resultadoFinal;
		}
	
	public static String cortarDerecha(String cadena,int longitudCorte){
		String texto = "";
		if(cadena.length()>=longitudCorte){
			texto = cadena.toString().substring(0,cadena.length()-longitudCorte);
		}
		return texto;
	}
}