package mx.com.qtx.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.com.qtx.gestorDatos.EstatusOperBD;
import mx.com.qtx.gestorDatos.ServicioDatos;
import mx.com.qtx.jpa.dominio.Articulo;
import mx.com.qtx.jpa.dominio.Cliente;
import mx.com.qtx.jpa.dominio.DetalleVenta;
import mx.com.qtx.jpa.dominio.Vendedor;
import mx.com.qtx.jpa.dominio.Venta;
import mx.com.qtx.web.DetalleCarritoCompra;

public class GestorVentaEnLinea implements AutoCloseable{
	private ServicioDatos sd;

	public GestorVentaEnLinea() {
		this.sd = new ServicioDatos();
	}
	
	public List<Cliente>  getListaClientes()  {
    	List<Cliente> clientes;
    	clientes = sd.getAllClientes();
		return clientes;
	}
	
	public List<Vendedor> getListaVendedores() {
    	List<Vendedor> vendedores;
		vendedores = sd.getAllVendedores();
		return vendedores;
	}
	
	public Map<String,String> getMapaArticulos(){
     	Map<String,String> mapArticulos;
    	mapArticulos = sd.getAllDescArticulos();
		return mapArticulos;
	}
	
	public Articulo getArticulo(String llave) {
		Articulo articulo;
		articulo = sd.getArticuloXID(llave);
		return articulo;
	}
	
	public Cliente getCliente(long llave) {
		Cliente cte;
		cte = sd.getClienteXID(llave);
		return cte;
	}
	
	public Vendedor getVendedor(long llave){
		Vendedor vendedor;
		vendedor = sd.getVendedorXID(llave);
		return vendedor;
	}
	
	public Venta registrarVenta(String cadIdCte, String cadIdVendedor, 
			List<DetalleCarritoCompra> carritoCompra) throws Exception{
		Venta vta = new Venta();
		ArrayList<DetalleVenta> detallesVta = new ArrayList<>();
		
		long idCte = Long.parseLong(cadIdCte);
		long idVendedor = Long.parseLong(cadIdVendedor);
		
		int nDetVtaI = 1;
		for(DetalleCarritoCompra detCarritoI : carritoCompra) {
			DetalleVenta detVtaI = new DetalleVenta();
			
			detVtaI.setNumDetalleVta(nDetVtaI);
			nDetVtaI++;
			detVtaI.setCantidad(detCarritoI.getCantidad());
			Articulo articuloI = this.getArticulo(detCarritoI.getCveArticulo());
			detVtaI.setArticulo(articuloI);
			detVtaI.setPrecioUnitario(articuloI.getPrecioLista());
			detVtaI.setVenta(vta);
			detallesVta.add(detVtaI);
		}
		
		Cliente cte = this.getCliente(idCte);
		Vendedor vendedor = this.getVendedor(idVendedor);
		
		vta.setCliente(cte);
		vta.setVendedor(vendedor);
		vta.setDetalleVentas(detallesVta);
		vta.setFechaVenta(new Date());
		
		EstatusOperBD statusVta;
		statusVta =  sd.registrarVenta(vta);
		
		if(statusVta != EstatusOperBD.OK) {
			vta.setNumVenta(0);
			throw new VtaEnLineaException(statusVta.getMensaje() + " al intentar registrar la venta");
		}
		return vta;
	}

	@Override
	public void close() throws Exception {
		this.sd.cerrar();
	}
}
