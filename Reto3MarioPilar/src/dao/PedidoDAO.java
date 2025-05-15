package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import clases.Cliente;
import clases.Pedido;
import util.Conexion;
import util.Funciones;

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
		 	PreparedStatement stmt = con.prepareStatement("select pedidos.fecha,pedidos.precioTotal,pedidos.direccionEnvio,productos.nombre as 'nombreProductos',p.unidades \r\n"
		 			+"from clientes \r\n " 
		 			+"inner join pedidos on clientes.idcliente = pedidos.idcliente \r\n"
		 			+ "inner join pedidoproducto p on pedidos.idpedido = p.idpedido \r\n" 
		 			+ "inner join productos on p.idproducto = productos.idproducto \r\n"
		 			+ "inner join clientes c on pedidos.idcliente = c.idcliente"
		 			+"where clientes.idCliente=?  \r\n " );
		 	stmt.setInt(1, idCliente);
		 	ResultSet rs = stmt.executeQuery();
		 	while(rs.next())
		 	{    
		 		lista.add(new Pedido(rs.getInt(0),new Cliente(rs.getInt(0),
		 				rs.getString(""),rs.getString(""),rs.getInt(0)),rs.getDouble("precioTotal"),rs.getString("direccionEnvio"),
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

	public static void insertarPedido(Pedido pedido) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO pedidos(idCliente, precioTotal, direccionEnvio, fecha) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, pedido.getCliente().getIdCliente()); 
			pst.setDouble(2, pedido.getPrecioTotal()); 
			pst.setString(3, pedido.getDireccionEnvio()); 
			pst.setDate(4, Funciones.convierteFecha(pedido.getFecha())); 
			pst.execute();
			ResultSet rs = pst.getGeneratedKeys();
			//recorrer el ResultSet
			if (rs.next()) {
				pedido.setIdpedido(rs.getInt(1));
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
	}

}
