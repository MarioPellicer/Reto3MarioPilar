package clases;

public class PedidoProducto {

	private int idPedidoProducto;
	private Pedido pedido;
	private Producto producto;
	private int unidades;
	private double precio;
	/**
	 * constructor vacio
	 */
	public PedidoProducto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor de la clase PedidoProducto con idPedidoProducto,pedido,producto,unidades,precio.
	 * @param idPedidoProducto int
	 * @param pedido Pedido
	 * @param producto Producto
	 * @param unidades int
	 * @param precio double
	 */
	public PedidoProducto(int idPedidoProducto, Pedido pedido, Producto producto, int unidades, double precio) {
		super();
		this.idPedidoProducto = idPedidoProducto;
		this.pedido = pedido;
		this.producto = producto;
		this.unidades = unidades;
		this.precio = precio;
	}
	public int getIdPedidoProducto() {
		return idPedidoProducto;
	}
	public void setIdPedidoProducto(int idPedidoProducto) {
		this.idPedidoProducto = idPedidoProducto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return producto.getNombre()	+ ", " + unidades + " unidades";
	}
	
	public void calcularPrecio() {
		this.precio = producto.getPrecio()*unidades;
	}
}
