package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Categoria;
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
	
	/*mostraremos las categorías por consola, mostrando id y nombre, y le
	pediremos al usuario que elija una. Mostraremos los productos de esa categoría.*/
	public static List<Producto> selectCategoria(int idCategoria) {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idProducto, idCategoria, nombre, precio, descripcion, "
					+ "color, talla, stock FROM producto WHERE idCategoria = ?");
			ResultSet rs = pst.executeQuery();
			pst.setInt(1, idCategoria);
			while(rs.next()) {
				lista.add(new Producto(rs.getInt("idproducto"), new Categoria(rs.getInt("idCategoria"), null), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
						rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return lista;
	}
	
	/*pediremos por consola un nombre, una talla y un color. El usuario puede no
	introducir nada en alguna de esas preguntas (pulsa intro sin escribir nada). Buscaremos los productos
	que cumplan el filtro introducido y los mostraremos por consola. Ejemplo: si introduce sólo nombre,
	buscaremos los que tengan ese nombre contenido, no tiene que ser igual (usamos % en el valor del
	argumento que pasamos, no en el ?). Si introducen sólo en talla y color, los que tengan esa talla y ese
	color.*/
	public static List<Producto> buscar (Producto producto) {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//creo insert
			CallableStatement cs = con.prepareCall("CALL buscarProducto(?,?,?)");
			cs.setString(1, producto.getNombre()); 
			cs.setString(2, producto.getTalla()); 
			cs.setString(3, producto.getColor()); 
			cs.registerOutParameter(4, java.sql.Types.INTEGER); 
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				lista.add(new Producto(rs.getInt("idproducto"), new Categoria(rs.getInt("idCategoria"), null), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
						rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return lista;
	}
}
