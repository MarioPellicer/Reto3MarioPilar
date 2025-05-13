package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import clases.Cliente;
import clases.Pedido;
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
		 			+ " where month(fecha) = month(curdate()); " );
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
}
