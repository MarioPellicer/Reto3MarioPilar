package clases;

import java.util.Date;
/**
 * @author pilarmario 
 */
public class Pedido {
private int idpedido;
private Cliente cliente;
private double precioTotal;
private String direccionEnvio;
private Date fecha;
/**
 * constructor vacio
 */
public Pedido() {
	super();
}
/**
 * Constructor completo de la clase Pedido con Idpedido,cliente,precioTotal,direccionEnvio,fecha
 * @param idpedido int id del pedido
 * @param cliente Cliente clase cliente 
 * @param precioTotal double precio Total del pedido
 * @param direccionEnvio String direccion de envio del pedido
 * @param fecha Date fecha del pedido
 */
public Pedido(int idpedido, Cliente cliente, double precioTotal, String direccionEnvio, Date fecha) {
	super();
	this.idpedido = idpedido;
	this.cliente = cliente;
	this.precioTotal = precioTotal;
	this.direccionEnvio = direccionEnvio;
	this.fecha = fecha;
}
/**
 * devuelve el id del pedido
 * @return int devuelve el id del pedido
 */
public int getIdpedido() {
	return idpedido;
}
/**
 * cambia el id del pedido
 * @param idpedido int cambia el id del pedido
 */
public void setIdpedido(int idpedido) {
	this.idpedido = idpedido;
}
/**
 * devuelve un cliente
 * @return Cliente devuelve un cliente
 */
public Cliente getCliente() {
	return cliente;
}
/**
 * cambia un cliente
 * @param cliente Cliente cambia un cliente
 */
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
/**
 * Devuelve el precioTotal del pedido
 * @return double Devuelve el precio total 
 */
public double getPrecioTotal() {
	return precioTotal;
}
/**
 * Cambia el precio total del pedido
 * @param precioTotal double cambia el precio total del pedido
 */
public void setPrecioTotal(double precioTotal) {
	this.precioTotal = precioTotal;
}
/**
 * Devuelve la direccion de envio del pedido
 * @return String direccion de envio del pedido
 */
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
