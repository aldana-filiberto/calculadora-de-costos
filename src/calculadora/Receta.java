package calculadora;

import java.util.ArrayList;
import java.util.List;

public class Receta {

	private List<Ingrediente> listaIngrediente;

	private double costoProductos;
	private int porcentaje;
	private double costoGastosOperacionales;
	private int costoManoObra;

	private double precioCostos;
	private double precioFinal;

	private double cantidadVendida;
	private double cantidadObtenida;
	private double precioUnitario;

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Receta() {
		this.listaIngrediente = new ArrayList<Ingrediente>();
		this.costoProductos = 0;
		this.porcentaje = 0;
		this.costoGastosOperacionales = 0;
		this.costoManoObra = 0;
		this.precioCostos = 0;
		this.precioUnitario = 0;
		this.precioFinal = 0;
		this.cantidadVendida = 1;
		this.cantidadObtenida = 1;

	}

	public void agregarIngrediente(Ingrediente in) {

		listaIngrediente.add(in);

	}

	public boolean eliminarIngrediente(String s) {

		for (int i = 0; i < listaIngrediente.size(); i++) {

			if (listaIngrediente.get(i).getProducto().equals(s)) {

				listaIngrediente.remove(i);
				return true;
			}
		}

		return false;
	}

	public void calcularCostos() {

		setearCostos();

		for (int i = 0; i < listaIngrediente.size(); i++) {

			costoProductos += listaIngrediente.get(i).getPrecio();

		}
		costoProductos = Math.round(costoProductos * 100) / 100d;

		costoGastosOperacionales = calcularPorcentaje(porcentaje);
		costoGastosOperacionales = Math.round(costoGastosOperacionales * 100) / 100d;

		precioCostos = ((costoProductos + costoGastosOperacionales) * costoManoObra);
		precioCostos = Math.round(precioCostos * 100) / 100d;

		calcularPrecioFinal();

	}

	public double calcularPrecioFinal() {
		precioUnitario = Math.round((precioCostos / cantidadObtenida) * 100) / 100d;
		precioFinal = Math.round((precioUnitario * cantidadVendida) * 100) / 100d;

		return precioFinal;

	}

	private int calcularPorcentaje(int x) {
		double y = (double) x / 100;
		return (int) (y * costoProductos);

	}

	public String getInfo() {
		String s = "";
		for (int i = 0; i < listaIngrediente.size(); i++) {

			s += listaIngrediente.get(i).getProducto().toUpperCase() + ": $" + listaIngrediente.get(i).getPrecioPaquete() + " / "
					+ listaIngrediente.get(i).getCantidadTotal() + "gr * " + listaIngrediente.get(i).getCantidadUsada() + "gr = $" + listaIngrediente.get(i).getPrecio()
					+ "<p>" + "\n";
		}

		return s;
	}

	public void setearCostos() {

		this.costoProductos = 0;

	}

	public List<Ingrediente> getListaIngrediente() {
		return listaIngrediente;
	}

	public double getCostoProductos() {
		return costoProductos;
	}

	public void setCostoProductos(double costoProductos) {
		this.costoProductos = costoProductos;
	}

	public int getPorcentajeGastosOperacionales() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentajeGastosOperacionales) {
		this.porcentaje = porcentajeGastosOperacionales;
	}

	public double getCostoGastosOperacionales() {
		return costoGastosOperacionales;
	}

	public int getCostoManoObra() {
		return costoManoObra;
	}

	public void setCostoManoObra(int costoManoObra) {
		this.costoManoObra = costoManoObra;
	}

	public double getPrecioCostos() {
		return precioCostos;
	}

	public void setPrecioCostos(double precioVenta) {
		this.precioCostos = precioVenta;
	}

	public double getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(double cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public double getCantidadObtenida() {
		return cantidadObtenida;
	}

	public void setCantidadObtenida(double cantidadObtenida) {
		this.cantidadObtenida = cantidadObtenida;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

}
