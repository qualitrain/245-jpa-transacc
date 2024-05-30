package mx.com.qtx.web;

public class DetalleCarritoCompra {
	private String cveArticulo;
	private String descripcion;
	private int cantidad;
	private float precio;
	private String precioFormateado;
	private float importeItem;
	private String importeItemFormateado;
	
	public DetalleCarritoCompra() {
	}

	public String getCveArticulo() {
		return cveArticulo;
	}

	public void setCveArticulo(String cveArticulo) {
		this.cveArticulo = cveArticulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.setImporteItem(this.cantidad * this.precio);
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
		this.precioFormateado = "$ " + String.format("%8.2f", this.precio);
		this.setImporteItem(this.cantidad * this.precio);
	}

	public String getPrecioFormateado() {
		return precioFormateado;
	}

	public float getImporteItem() {
		return importeItem;
	}

	public void setImporteItem(float importeItem) {
		this.importeItem = importeItem;
		this.importeItemFormateado = "$ " + String.format("%8.2f", importeItem);
	}

	public String getImporteItemFormateado() {
		return importeItemFormateado;
	}

	@Override
	public String toString() {
		return "DetalleCarritoCompra [cveArticulo=" + cveArticulo + ", descripcion=" + descripcion + ", cantidad="
				+ cantidad + ", precio=" + precio + ", precioFormateado=" + precioFormateado + ", importeItem="
				+ importeItem + ", importeItemFormateado=" + importeItemFormateado + "]";
	}
}
