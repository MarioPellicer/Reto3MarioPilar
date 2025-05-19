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
	public static List<Pedido> verPedidos() {
		 List<Pedido> lista = new ArrayList<Pedido>();
		 LocalDate ls= LocalDate.now();
		 ls.getMonth();
		 try(Connection con = Conexion.abreConexion())		 
		 {
		 	PreparedStatement stmt = con.prepareStatement("SELECT idPedido, fecha, clientes.nombre, precioTotal, direccionEnvio \r\n"
		 			+ "FROM pedidos\r\n"
		 			+ "INNER JOIN clientes ON pedidos.idcliente = clientes.idcliente\r\n"
		 			+ "where month(fecha) = month(curdate()) \r\n"
		 			+ "order by pedidos.fecha desc;");
		 	ResultSet rs = stmt.executeQuery();
		 	while(rs.next())
		 	{    
		 		lista.add(new Pedido(rs.getInt("idPedido"),new Cliente(0, rs.getString("clientes.nombre"), null, 0),rs.getDouble("precioTotal"),rs.getString("direccionEnvio"),rs.getDate("fecha")));
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
