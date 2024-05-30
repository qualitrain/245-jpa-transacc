package mx.com.qtx.web;

import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import mx.com.qtx.jpa.dominio.Cliente;
import mx.com.qtx.jpa.dominio.Vendedor;
import mx.com.qtx.servicios.GestorVentaEnLinea;

/**
 * Application Lifecycle Listener implementation class ArranqueListener
 *
 */
@WebListener
public class ArranqueListener implements ServletContextListener {
	private GestorVentaEnLinea gVtas = null;

    /**
     * Default constructor. 
     */
    public ArranqueListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
    	if(this.gVtas != null)
			try {
				this.gVtas.close();
			} 
    	    catch (Exception e) {
    	    	System.err.println(this.getClass().getName() + ".contextDestroyed(..)");
				e.printStackTrace();
			}
     }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	this.gVtas = new GestorVentaEnLinea();
    	List<Cliente> clientes = this.gVtas.getListaClientes();
    	List<Vendedor> vendedores = this.gVtas.getListaVendedores();
    	Map<String,String> mapArticulos = this.gVtas.getMapaArticulos();
    	arg0.getServletContext().setAttribute("clientes", clientes);
    	arg0.getServletContext().setAttribute("vendedores", vendedores);
    	arg0.getServletContext().setAttribute("mapArticulos", mapArticulos);
    	arg0.getServletContext().setAttribute("gestorVtas", this.gVtas);
    }
	
}
