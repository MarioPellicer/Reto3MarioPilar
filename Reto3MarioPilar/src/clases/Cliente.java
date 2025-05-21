package clases;

public class Cliente {

	private int idcliente;
	private String nombre;
	private String direccion;
	private int codigo;
	/**
	 * constructor vacio 
	 */
	public Cliente() {
		super();
	}
	/**
	 * Constructor de la clase Cliente con nombre,direccion y codigo
	 * @param nombre String el nombre del cliente
	 * @param direccion String la direccion del cliente 
	 * @param codigo Int el codigo del cliente
	 */
	public Cliente(String nombre, String direccion, int codigo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigo = codigo;
	}
	/**
	 * Constructor completo de la clase Cliente con idCliente,nombre,direccion y codigo
	 * @param idCliente Int el id del cliente
	 * @param nombre String el nombre del cliente
	 * @param direccion string la direccion del cliente
	 * @param codigo Int el codigo del cliente
	 */
	public Cliente(int idCliente, String nombre, String direccion, int codigo) {
		super();
		this.idcliente = idCliente;
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigo = codigo;
	}
	/**
	 * Devuelve el idCliente
	 * @return int devuelve el idCliente
	 */
	public int getIdCliente() {
		return idcliente;
	}
	/**
	 * Cambia el idCliente
	 * @param idCliente int cambia el idCliente
	 */
	public void setIdCliente(int idCliente) {
		this.idcliente = idCliente;
	}
	/**
	 * Devuelve el nombre del cliente
	 * @return String devuelve el nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Cambia el nombre del cliente
	 * @param String cambia el nombre del cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Devuelve la direccion del cliente
	 * @return String devuelve la direccion del cliente
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Cambia la direccion del cliente
	 * @param direccion Sting cambia la direccion del cliente
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Devuelve el codigo del cliente
	 * @return int devuelve el codigo del cliente
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Cambia el codigo del cliente 
	 * @param codigo int cambia el codigo del cliente
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "cod " + codigo + ", id " + idcliente + ", " + nombre + ", " + direccion;
	}



}
