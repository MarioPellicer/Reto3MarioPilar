package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.Categoria;
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;
import util.Conexion;

public class PedidoProductoDAO {

	/*mostraremos el producto del que se hayan comprado más unidades. Si hay
	empate se mostrarán todos los empatados.*/
	public static List<PedidoProducto> masUnidades() {
		List<PedidoProducto> lista = new ArrayList<PedidoProducto>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idproducto, sum(unidades) FROM pedidoproducto group by idProducto having sum(unidades) = (\r\n"
					+ "SELECT sum(unidades) FROM pedidoproducto group by idProducto order by sum(unidades) desc limit 1)");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				lista.add(new PedidoProducto(0, null, new Producto(rs.getInt("idProducto"), null, null, 0, null, null, null, 0), rs.getInt("sum(unidades)"), 0));
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return lista;
	}

	public static List<PedidoProducto> productos(Pedido pedido) {
		 List<PedidoProducto> lista = new ArrayList<PedidoProducto>();
		 LocalDate ls= LocalDate.now();
		 ls.getMonth();
		 try(Connection con = Conexion.abreConexion())		 
		 {
		 	PreparedStatement stmt = con.prepareStatement("select categorias.nombre as 'nombreCategoria',productos.nombre as 'nombreProductos' , p.unidades \r\n"
		 			+ "from pedidos  \r\n"
		 			+ "inner join pedidoproducto p on pedidos.idpedido = p.idpedido  \r\n"
		 			+ "inner join productos on p.idproducto = productos.idproducto \r\n"
		 			+ "inner join categorias on productos.idcategoria = categorias.idcategoria\r\n"
		 			+ "where pedidos.idpedido = ?;" );
		 	stmt.setInt(1, pedido.getIdpedido());
		 	ResultSet rs = stmt.executeQuery();
		 	while(rs.next())
		 	{    
		 		lista.add(new PedidoProducto(0,new Pedido(),new Producto(0,new Categoria(0,rs.getString("nombreCategoria")),rs.getString("nombreProductos"),0,"","","",0),
		 				rs.getInt("p.unidades"),0));
		 	}
		 	rs.close();
		 }catch (Exception ex){
		 	ex.printStackTrace();
		 }
		 finally {
			Conexion.cierraConexion();
		}
		return lista;
	}
	public static List<PedidoProducto> buscarClienteId(int idCliente){
		 List<PedidoProducto> lista = new ArrayList<PedidoProducto>();
		 try(Connection con = Conexion.abreConexion())
		 {
		 	PreparedStatement stmt = con.prepareStatement("select pedidos.fecha,pedidos.precioTotal,pedidos.direccionEnvio,productos.nombre as 'nombreProductos',p.unidades,categorias.nombre as 'nombreCategoria' \r\n"
		 			+"from clientes \r\n " 
		 			+"inner join pedidos on clientes.idcliente = pedidos.idcliente \r\n"
		 			+ "inner join pedidoproducto p on pedidos.idpedido = p.idpedido \r\n" 
		 			+ "inner join productos on p.idproducto = productos.idproducto \r\n"
		 			+ "inner join categorias on productos.idcategoria = categorias.idcategoria \r\n"
		 			+"where clientes.idCliente=?  \r\n " );
		 	stmt.setInt(1, idCliente);
		 	ResultSet rs = stmt.executeQuery();
		 	while(rs.next())
		 	{    
		 		lista.add(new PedidoProducto(0,new Pedido(0,new Cliente(0,
		 				"","",0),rs.getDouble("pedidos.precioTotal"),rs.getString("pedidos.direccionEnvio"),
		 				rs.getDate("pedidos.fecha")),new Producto(0,new Categoria(0,rs.getString("nombreCategoria")),rs.getString("nombreProductos"),0,"","","",0),
		 				rs.getInt("p.unidades"),rs.getDouble("pedidos.precioTotal")));
		 	}
		 	rs.close();
		 }catch (Exception ex){
		 	ex.printStackTrace();
		 }
		 finally {
			Conexion.cierraConexion();
		}
		return lista;
	 }
}
