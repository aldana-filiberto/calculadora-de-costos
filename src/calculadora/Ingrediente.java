package calculadora;

public class Ingrediente {

	private String producto;
	private double cantidadTotal;
	private double cantidadUsada;
	private double precioPaquete;
	private double precio;

	public Ingrediente(String producto, double precioPaquete, double cantidadTotal, double cantidadUsada) {

		this.producto = producto;
		this.cantidadTotal = cantidadTotal;
		this.cantidadUsada = cantidadUsada;
		this.precioPaquete = precioPaquete;

	}

	public String getProducto() {
		return producto;
	}

	double getCantidadTotal() {
		return cantidadTotal;
	}

	double getCantidadUsada() {
		return cantidadUsada;
	}

	double getPrecioPaquete() {
		return precioPaquete;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setCantidadTotal(double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public void setCantidadUsada(double cantidadUsada) {
		this.cantidadUsada = cantidadUsada;
	}

	public void setPrecioPaquete(double precioPaquete) {
		this.precioPaquete = precioPaquete;
	}

	public double getPrecio() {

		precio = (getPrecioPaquete() / getCantidadTotal()) * getCantidadUsada();

		precio = Math.round(precio * 100) / 100d;
		return precio;

	}

}
