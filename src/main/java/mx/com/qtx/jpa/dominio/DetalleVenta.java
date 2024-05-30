package mx.com.qtx.jpa.dominio;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the detalle_venta database table.
 * 
 */
@Entity
@Table(name="DEV_DETALLE_VTA", uniqueConstraints= {
		@UniqueConstraint(columnNames= {"DEV_NUM_VTA", "DEV_NUM_DETALLE"})
})
public class DetalleVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEV_ID")
	private long idDetalle;

	@Column(name="DEV_CANTIDAD", nullable=false)
	private int cantidad;

	@Column(name="DEV_PRECIO_UNITARIO", nullable=false)
	private float precioUnitario;
	
	@Column(name="DEV_NUM_DETALLE")
	private int numDetalleVta;

	@ManyToOne()
	@JoinColumn(name="DEV_NUM_VTA", referencedColumnName="VTA_NUM_VENTA", nullable=false, updatable=false)
	private Venta venta;
	
	@ManyToOne()
	@JoinColumn(name="DEV_ART_CVE", referencedColumnName="ART_CVE")
	private Articulo articulo;

	public DetalleVenta() {
	}

	public DetalleVenta(int cantidad, float precioUnitario, int numDetalleVta, Venta venta, Articulo articulo) {
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.numDetalleVta = numDetalleVta;
		this.venta = venta;
		this.articulo = articulo;
	}

	public long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getNumDetalleVta() {
		return numDetalleVta;
	}

	public void setNumDetalleVta(int numDetalleVta) {
		this.numDetalleVta = numDetalleVta;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public double getTotal() {
		return this.getCantidad() * this.precioUnitario;
	}
}