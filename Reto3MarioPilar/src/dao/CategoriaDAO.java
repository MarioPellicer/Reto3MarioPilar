package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import clases.Categoria;
import util.Conexion;

public class CategoriaDAO {
	public static void gestionCategorias(Categoria categoria) {
		 try(Connection con = Conexion.abreConexion())
		 {
			PreparedStatement st = con.prepareStatement("insert into categorias(nombre) values(?)",Statement.RETURN_GENERATED_KEYS);
		 	st.setString(1,categoria.getNombre());
			st.executeUpdate();
		 	ResultSet rs = st.getGeneratedKeys();
		 	if (rs.next()) {
		 		categoria.setIdCategoria(rs.getInt(1));
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

