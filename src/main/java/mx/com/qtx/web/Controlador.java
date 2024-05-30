package mx.com.qtx.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.com.qtx.jpa.dominio.Articulo;
import mx.com.qtx.jpa.dominio.Venta;
import mx.com.qtx.servicios.GestorVentaEnLinea;


/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestorVentaEnLinea gestorVtas = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();   
    	this.gestorVtas = (GestorVentaEnLinea) this.getServletContext()
    			                                   .getAttribute("gestorVtas");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		sesion.removeAttribute("mensaje");
		sesion.removeAttribute("ex");
		
		String vista = request.getParameter("vista");
		String accion = request.getParameter("accion");
		switch (vista) {
		case "index.jsp":
			response.sendRedirect("getVenta.jsp");
			return;
		case "getVenta.jsp":
			switch (accion) {
				case "agregarArt" : this.agregarArticuloAcarrito(request, response, sesion);
									response.sendRedirect("getVenta.jsp");
									return;
				case "registrarVta" : 
									boolean operacionExitosa = this.registrarVenta(request,response, sesion);
									if(operacionExitosa == false) {
										response.sendRedirect("getVenta.jsp");
										return;
									}
								    response.sendRedirect("ventaRegistrada.jsp");
								    return;
			}
		case "ventaRegistrada.jsp":
			sesion.invalidate();
			this.getServletContext().setAttribute("mapArticulos", gestorVtas.getMapaArticulos());
			response.sendRedirect("getVenta.jsp");
			return;
		}
	}

	private boolean registrarVenta(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		String cadIdCte = request.getParameter("idCte");
		sesion.setAttribute("idCteSel", cadIdCte);
		String cadIdVendedor = request.getParameter("idVendedor");
		sesion.setAttribute("idVendedorSel", cadIdVendedor);
		
		List<DetalleCarritoCompra> carritoCompra = this.getCarritoCompra(sesion);
		if(carritoCompra.size() == 0) {
			sesion.setAttribute("mensaje", "La venta no tiene art&iacute;culos a&uacute;n");
			return false;
		}
		Venta venta;
		try {
			venta = gestorVtas.registrarVenta(cadIdCte,cadIdVendedor,carritoCompra);
		}
		catch(Exception ex) {
			sesion.setAttribute("mensaje", "Se present&oacute; un error al registrar la venta: " + ex.getMessage());
			sesion.setAttribute("ex", ex);
			return false;
		}
		sesion.setAttribute("venta", venta);
		return true;	
	}

	private void agregarArticuloAcarrito(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
		String cadIdCte = request.getParameter("idCte");
		sesion.setAttribute("idCteSel", cadIdCte);
		
		String cadIdVendedor = request.getParameter("idVendedor");
		sesion.setAttribute("idVendedorSel", cadIdVendedor);
		
		List<DetalleCarritoCompra> carritoCompra = getCarritoCompra(sesion);
		
		String idArticulo = request.getParameter("idArt");
		
		ArrayList<String> mensajesError = new ArrayList<>();
		
		int cantArt  = this.getCantidadArt(request, mensajesError);
		if(mensajesError.size() > 0) {
			sesion.setAttribute("mensaje", mensajesError.get(0));
			return;
		}
		
		Articulo artI = gestorVtas.getArticulo(idArticulo);
		
		DetalleCarritoCompra detalleCarrito = new DetalleCarritoCompra();
		detalleCarrito.setCveArticulo(idArticulo);
		detalleCarrito.setPrecio(artI.getPrecioLista());
		detalleCarrito.setCantidad(cantArt);
		detalleCarrito.setDescripcion(artI.getDescripcion());
		
		carritoCompra.add(detalleCarrito);
		
		float importeVenta = this.getImporteVenta(carritoCompra);
		sesion.setAttribute("importeVenta", "$ " + String.format("%10.2f", importeVenta));
	}

	private List<DetalleCarritoCompra> getCarritoCompra(HttpSession sesion) {
		@SuppressWarnings("unchecked")
		List<DetalleCarritoCompra> carritoCompra = 
				(List<DetalleCarritoCompra>) sesion.getAttribute("carritoCompra");
		if(carritoCompra == null) {
			carritoCompra = new ArrayList<>();
			sesion.setAttribute("carritoCompra", carritoCompra);
		}
		return carritoCompra;
	}

	private float getImporteVenta(List<DetalleCarritoCompra> carritoCompra) {
		float importeVenta = 0F;
		for(DetalleCarritoCompra detI : carritoCompra)
			importeVenta += detI.getImporteItem();
		return importeVenta;
	}

	private int getCantidadArt(HttpServletRequest request, ArrayList<String> mensajesError) {
		String cadCantArt = request.getParameter("cantArt");
		if(cadCantArt == null) {
			mensajesError.add("Debe especificarse la cantidad del art&iacute;culo");
			return 0;
		}
		if(cadCantArt.trim().isEmpty()) {
			mensajesError.add("Debe especificarse la cantidad del art&iacute;culo");
			return 0;			
		}
		int cantArt = 0;
		try {
			cantArt = Integer.parseInt(cadCantArt);
		}
		catch(NumberFormatException nfex) {
			mensajesError.add("La cantidad del art&iacute;culo debe ser num&eacute;rica");
			return 0;						
		}
		if(cantArt < 0) {
			mensajesError.add("La cantidad del art&iacute;culo no puede ser negativa");
			return 0;						
		}
		return cantArt;
	}
}
