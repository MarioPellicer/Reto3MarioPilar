package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import clases.Producto;
import util.Conexion;

public class ProductoDAO {

	/*pide por consola los datos de un producto, muestras las categorías con su id,
	que el cliente elija una e inserta el producto en la BD.*/
	public static void insertarProducto(Producto producto) {
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//creo insert
			PreparedStatement pst = con.prepareStatement("INSERT INTO producto(idCategoria, nombre, precio, descripcion, color, talla, stock) VALUES (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, producto.getCategoria().getIdCategoria()); 
			pst.setString(2, producto.getNombre()); 
			pst.setDouble(3, producto.getPrecio()); 
			pst.setString(4, producto.getDescripcion());
			pst.setString(5, producto.getColor());
			pst.setString(6, producto.getTalla());
			pst.setInt(7, producto.getStock()); 
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			//recorrer el ResultSet
			if (rs.next()) {
				producto.setIdproducto(rs.getInt(1));
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
