package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import util.Conexion;

public class PedidoDAO {
	public static Pedido verPedidos(Pedido pedido) {
		 Pedido pedidoNuevo = new Pedido();
		 LocalDate ls= LocalDate.now();
		 ls.getMonth();
		 try(Connection con = Conexion.abreConexion())		 
		 {
		 	PreparedStatement stmt = con.prepareStatement("select * \r\n"
		 			+"from pedido \r\n "
		 			+ "inner join pedidoproducto p on pedido.idpedido = p.idpedido" 
		 			+ " where month(fecha) = month(curdate()); "
		 			+ "order by (desc)" );
		 	ResultSet rs = stmt.executeQuery();
		 	if(rs.next())
		 	{    
		 		pedidoNuevo=new Pedido(rs.getInt("idPedido"),new Cliente(),rs.getDouble("precioTotal"),rs.getString("direccionEnvio"),rs.getDate("fecha"));
		 	}else {
				return null;
			}
		 	rs.close();
		 }catch (Exception ex){
		 	ex.printStackTrace();
		 }
		 finally {
			Conexion.cierraConexion();
		}
		return pedidoNuevo;
	}
	public static Pedido buscarCliente(Pedido pedido){
		 Pedido pedidoNuevo = new Pedido();
		 try(Connection con = Conexion.abreConexion())
		 {
		 	PreparedStatement stmt = con.prepareStatement("select pedidoproducto.unidades\r\n"
		 			+"from pedido \r\n " 
		 			+"inner join pedidoproducto on pedidoproducto.idpedido = pedidos.idpedido  \r\n "
		 			+"where idcliente=? " );
		 	stmt.setInt(1, pedido.getCliente().getCodigo());
		 	ResultSet rs = stmt.executeQuery();
		 	if(rs.next())
		 	{    
		 		pedidoNuevo=new Pedido(rs.getInt("idPedido"),new Cliente(),rs.getDouble("precioTotal"),rs.getString("direccionEnvio"),rs.getDate("fecha"));
		 	}else {
				return null;
			}
		 	rs.close();
		 }catch (Exception ex){
		 	ex.printStackTrace();
		 }
		 finally {
			Conexion.cierraConexion();
		}
		return pedidoNuevo;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Pedido> buscarClienteId(int idCliente){
		 List<Pedido> lista = new ArrayList<Pedido>();
		 try(Connection con = Conexion.abreConexion())
		 {
		 	PreparedStatement stmt = con.prepareStatement("select * \r\n"
		 			+"from clientes \r\n " 
		 			+ "inner join pedidoproducto p on pedidos.idpedido = p.idpedido \r\n" 
		 			+ "inner join productos on p.idproducto = productos.idproducto \r\n"
		 			+ "inner join clientes c on pedidos.idcliente = c.idcliente"
		 			+"where clientes.idCliente=?  \r\n " );
		 	stmt.setInt(1, idCliente);
		 	ResultSet rs = stmt.executeQuery();
		 	while(rs.next())
		 	{    
		 		lista.add(new Pedido(rs.getInt("idPedido"),new Cliente(rs.getInt("idCliente"),
		 				rs.getString("nombre"),rs.getString("direccion"),rs.getInt("codigo")),rs.getDouble("precioTotal"),rs.getString("direccionEnvio"),
		 				rs.getDate("fecha")));
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
