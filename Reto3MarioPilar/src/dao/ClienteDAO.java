package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import clases.Cliente;
import util.Conexion;

public class ClienteDAO {
	public static void altaDeNuevos(Cliente cliente) {
		 try(Connection con = Conexion.abreConexion())
		 {
			PreparedStatement st = con.prepareStatement("insert into clientes(nombre,direccion,codigo) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
		 	st.setString(1,cliente.getNombre());
		 	st.setString(2,cliente.getDireccion());
		 	st.setInt(3,cliente.getCodigo());
			st.executeUpdate();
		 	ResultSet rs = st.getGeneratedKeys();
		 	if (rs.next()) {
		 		cliente.setIdCliente(rs.getInt(1));
		 	}
		 	rs.close();
		 }catch (Exception ex){
		 	ex.printStackTrace();
		 }
		 finally {
			 
			Conexion.cierraConexion();
		}
	 }
	public static Cliente buscarCliente(Cliente cliente){
		 Cliente clienteNuevo = new Cliente();
		 try(Connection con = Conexion.abreConexion())
		 {
		 	PreparedStatement stmt = con.prepareStatement("select * \r\n"
		 			+"from clientes \r\n " 
		 			+"where codigo=?  \r\n " );
		 	stmt.setInt(1, cliente.getCodigo());
		 	ResultSet rs = stmt.executeQuery();
		 	if(rs.next())
		 	{    
		 		clienteNuevo=new Cliente(rs.getInt("idCliente"),rs.getString("nombre"),rs.getString("direccion"),rs.getInt("codigo"));
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
		return clienteNuevo;
	 }
	public static void actualizaCliente(Cliente cliente)
	{
		try {
			Connection con = Conexion.abreConexion();	
			PreparedStatement pst = con.prepareStatement("update clientes set nombre=?, direccion=?,codigo=? where idCliente=?");
			pst.setString(1, cliente.getNombre());
			pst.setString(2, cliente.getDireccion());
			pst.setInt(3, cliente.getCodigo());
			pst.setInt(4, cliente.getIdCliente());
			pst.execute();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}

	}
	}
	

