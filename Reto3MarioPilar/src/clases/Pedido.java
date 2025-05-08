package clases;

import java.util.Date;

public class Pedido {
private int idpedido;
private Cliente cliente;
private double precioTotal;
private String direccionEnvio;
private Date fecha;
public Pedido() {
	super();
}
public Pedido(int idpedido, Cliente cliente, double precioTotal, String direccionEnvio, Date fecha) {
	super();
	this.idpedido = idpedido;
	this.cliente = cliente;
	this.precioTotal = precioTotal;
	this.direccionEnvio = direccionEnvio;
	this.fecha = fecha;
}
public int getIdpedido() {
	return idpedido;
}
public void setIdpedido(int idpedido) {
	this.idpedido = idpedido;
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public double getPrecioTotal() {
	return precioTotal;
}
public void setPrecioTotal(double precioTotal) {
	this.precioTotal = precioTotal;
}
public String getDireccionEnvio() {
	return direccionEnvio;
}
public void setDireccionEnvio(String direccionEnvio) {
	this.direccionEnvio = direccionEnvio;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
@Override
public String toString() {
	return "Pedido [idpedido=" + idpedido + ", cliente=" + cliente + ", precioTotal=" + precioTotal
			+ ", direccionEnvio=" + direccionEnvio + ", fecha=" + fecha + "]";
}

}
