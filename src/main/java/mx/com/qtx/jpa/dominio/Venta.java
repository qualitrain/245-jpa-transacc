package mx.com.qtx.jpa.dominio;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="VTA_VENTA")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VTA_NUM_VENTA")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numVenta;

	@Temporal(TemporalType.DATE)
	@Column(name="VTA_FECHA", nullable=false)
	private Date fechaVenta;

	//bi-directional many-to-one association to DetalleVenta
	@OneToMany(mappedBy="venta",cascade= {CascadeType.ALL})
	private List<DetalleVenta> detalleVentas;

	//bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name="VTA_VEN_NUM", nullable=true, referencedColumnName="VEN_NUM")
	private Vendedor vendedor;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="VTA_CTE_NUM", nullable=true, referencedColumnName="CTE_NUM")
	private Cliente cliente;

	public Venta() {
	}

	public int getNumVenta() {
		return numVenta;
	}

	public void setNumVenta(int numVenta) {
		this.numVenta = numVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleVenta> getDetalleVentas() {
		return detalleVentas;
	}

	public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}
	
	public double getTotal() {
		double total = 0;
		for(DetalleVenta detalleI : this.detalleVentas) {
			total += detalleI.getTotal();
		}
		return total;
	}
}