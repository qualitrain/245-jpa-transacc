package mx.com.qtx.jpa.dominio;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="VEN_VENDEDOR")
@NamedQuery(name="Vendedor.findAll", 
			query="SELECT vend FROM Vendedor vend ORDER BY vend.nombre")
public class Vendedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VEN_NUM")
	private long numVendedor;
	@Column(name="VEN_NOMBRE", length=50, nullable=false)
	private String nombre;
	@Column(name="VEN_NVTAS")
	private long nVtas;	
	
	@OneToMany(mappedBy="vendedor")
	private List<Venta> ventas;
	
	public Vendedor() {
	}
	
	public Vendedor(String nombre) {
		this.nombre = nombre;
		this.nVtas = 0L;
	}
	
	public long getNumVendedor() {
		return numVendedor;
	}
	
	public void setNumVendedor(long numVendedor) {
		this.numVendedor = numVendedor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public long getnVtas() {
		return nVtas;
	}
	
	public void setnVtas(long nVtas) {
		this.nVtas = nVtas;
	}
	
	public List<Venta> getVentas() {
		return ventas;
	}
	
	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

}
