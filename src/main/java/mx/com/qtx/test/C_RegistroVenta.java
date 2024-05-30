package mx.com.qtx.test;

import java.util.ArrayList;
import java.util.Date;

import mx.com.qtx.gestorDatos.ServicioDatos;
import mx.com.qtx.jpa.dominio.Articulo;
import mx.com.qtx.jpa.dominio.Cliente;
import mx.com.qtx.jpa.dominio.DetalleVenta;
import mx.com.qtx.jpa.dominio.Vendedor;
import mx.com.qtx.jpa.dominio.Venta;

public class C_RegistroVenta {

	public static void main(String[] args) {
		Venta nuevaVenta = getVenta();
		ServicioDatos sd = new ServicioDatos();
		sd.registrarVenta(nuevaVenta);
		sd.cerrar();
	}

	private static Venta getVenta() {
		ServicioDatos sd = new ServicioDatos();
		Articulo art1 = sd.getArticuloXID("CHX-WW-002");
		Articulo art2 = sd.getArticuloXID("CHX-WW-003");
		Articulo art3 = sd.getArticuloXID("PCS-JN-00130");
		Cliente cte = sd.getClienteXID(1L);
		Vendedor vendedor = sd.getVendedorXID(1L);
		sd.cerrar();
				
		Venta unaVenta = new Venta();
		
		unaVenta.setFechaVenta(new Date());
		unaVenta.setCliente(cte);
		unaVenta.setVendedor(vendedor);
		
		
		ArrayList<DetalleVenta> detallesVta = new ArrayList<>();
		DetalleVenta detalle1 = new DetalleVenta(2, art1.getPrecioLista(), 1, unaVenta, art1);
		detallesVta.add(detalle1);
		DetalleVenta detalle2 = new DetalleVenta(1, art2.getPrecioLista(), 2, unaVenta, art2);
		detallesVta.add(detalle2);
		DetalleVenta detalle3 = new DetalleVenta(2, art3.getPrecioLista(), 3, unaVenta, art3);
		detallesVta.add(detalle3);
		unaVenta.setDetalleVentas(detallesVta);
		
		return unaVenta;
	}
}

