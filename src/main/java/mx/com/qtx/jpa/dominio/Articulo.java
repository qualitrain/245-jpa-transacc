package mx.com.qtx.jpa.dominio;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="ART_ARTICULO")
@NamedQuery(name="Articulo.findAll", 
	query="SELECT art FROM Articulo art ORDER BY art.descripcion, art.cveArticulo")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ART_CVE", unique=true, nullable=false, length=20)
	private String cveArticulo;

	@Column(name="ART_COSTO_PROV_1")
	private float costoProv1;

	@Column(name="ART_DESCRIPCION", nullable=false, length=40)
	private String descripcion;

	@Column(name="ART_PRECIO_LISTA")
	private float precioLista;
	
	@Column(name="ART_EXISTENCIA", nullable=false)
	private int existencia;

	public Articulo() {
	}

	public String getCveArticulo() {
		return this.cveArticulo;
	}

	public void setCveArticulo(String cveArticulo) {
		this.cveArticulo = cveArticulo;
	}

	public float getCostoProv1() {
		return this.costoProv1;
	}

	public void setCostoProv1(float costoProv1) {
		this.costoProv1 = costoProv1;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioLista() {
		return this.precioLista;
	}

	public void setPrecioLista(float precioLista) {
		this.precioLista = precioLista;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public String getDescripcionExtendida() {
		return String.format("%-37s %-15s %8.2f %3d", this.descripcion, this.cveArticulo, this.precioLista, this.existencia);
	}
}