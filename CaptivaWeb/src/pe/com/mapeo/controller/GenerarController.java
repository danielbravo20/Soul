package pe.com.mapeo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenerarController
 */
@WebServlet("/generarController")
public class GenerarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		crear(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		crear(request, response);
	}

	protected void crear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context jndiContext = null;
        Queue destino = null;
        QueueConnection  conexion = null;
        QueueSession  sesionActual = null;
        QueueSender  enviadorMensajes = null;
        TextMessage mensaje = null;
        
        String colaDestino = "jms/CaptivaGeneradorService";
        
        try {
            jndiContext = getInitialContext();
        }catch (NamingException e) {
            System.out.println("Imposible crear el contexto  " + "contexto: " + e.toString());
            System.exit(1);
        }
        
        try {
            // creamos el objeto para construir la conexion
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)jndiContext.lookup("jms/CaptivaGeneradorService");
 
            // localizamos la cola destino
            destino = (Queue) jndiContext.lookup(colaDestino);
 
            // creamos una conexión
            conexion = queueConnectionFactory.createQueueConnection();
 
            sesionActual = conexion.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
 
            // Creamos una factoria de mensaje y un mensaje
            enviadorMensajes = sesionActual.createSender(destino);
 
            mensaje = sesionActual.createTextMessage();
            mensaje.setStringProperty("TipoMensaje", "1");
 
 
           long inicio = System.currentTimeMillis();
 
           mensaje.setText("Nuevo mensaje " + new Date() );
           enviadorMensajes.send(mensaje);
           //}
           enviadorMensajes.close();
            //productorMensajes.send(sesionActual.createMessage());
 
 
           long fin = System.currentTimeMillis() - inicio;
           System.out.println("El tiempo transcurrido es " + fin + " milisegundos");
 
 
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conexion != null) {
                try {
                    conexion.close();
                }
                catch (JMSException e){
                	e.printStackTrace();
                }
            }
        }
    
	}
	
	private static InitialContext getInitialContext() throws NamingException{
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.enterprise.naming.SerialInitContextFactory");
        env.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        env.put(Context.PROVIDER_URL, "iiop://127.0.0.1:1050");
        //env.put("org.omg.CORBA.ORBInitialHost","127.0.0.1");
 
        return new InitialContext(env);
    }
}
