package clases;

public class Cliente {
private int idcliente;
private String nombre;
private String direccion;
private int codigo;
public Cliente() {
	super();
}
public Cliente(int idCliente, String nombre, String direccion, int codigo) {
	super();
	this.idcliente = idCliente;
	this.nombre = nombre;
	this.direccion = direccion;
	this.codigo = codigo;
}
public int getIdCliente() {
	return idcliente;
}
public void setIdCliente(int idCliente) {
	this.idcliente = idCliente;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public int getCodigo() {
	return codigo;
}
public void setCodigo(int codigo) {
	this.codigo = codigo;
}
@Override
public String toString() {
	return "Cliente [idCliente=" + idcliente + ", nombre=" + nombre + ", direccion=" + direccion + ", codigo=" + codigo
			+ "]";
}

}
