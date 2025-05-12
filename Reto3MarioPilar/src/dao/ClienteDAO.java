package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import clases.Cliente;
import util.Conexion;

public class ClienteDAO {
	public static void AltaDeNuevos(Cliente cliente) {
		 try(Connection con = Conexion.abreConexion())
		 {
			PreparedStatement st = con.prepareStatement("insert into categorias(nombre,direccion,codigo) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
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
	}

