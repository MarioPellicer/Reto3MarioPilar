package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clases.Categoria;
import clases.PedidoProducto;
import clases.Producto;
import util.Conexion;

public class ProductoDAO {

	/*pide por consola los datos de un producto, muestras las categorías con su id,
	que el cliente elija una e inserta el producto en la BD.*/
	public static void insertarProducto(Producto producto) {
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("INSERT INTO productos(idCategoria, nombre, precio, descripcion, color, talla, stock) VALUES (?,?,?,?,?,?,?)",
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
					+ "color, talla, stock FROM productos WHERE idCategoria = ?");
			pst.setInt(1, idCategoria);
			ResultSet rs = pst.executeQuery();
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
	/**
	 * pidiendo nombres de productos. 
	 * Buscamos en la base de datos si hay algun producto con ese nombre y si existe, pediremos 
	 * cuantas unidades queremos de ese producto.
	 * @param nombre String 
	 * @return nuevoProducto 
	 */
	public static Producto selectNombre(String nombre) {
		Producto nuevoProducto=null;
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idProducto, idCategoria, nombre, precio, descripcion, "
					+ "color, talla, stock FROM productos WHERE nombre = ?");
			pst.setString(1, nombre);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				nuevoProducto=new Producto(rs.getInt("idProducto"),new Categoria(),rs.getString("nombre"),rs.getDouble("precio"),rs.getString("descripcion"),rs.getString("color"),rs.getString("talla"),rs.getInt("stock"));
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return nuevoProducto;
	}
	/**
	 * pediremos por consola un nombre, una talla y un color. El usuario puede no
	* introducir nada en alguna de esas preguntas (pulsa intro sin escribir nada). Buscaremos los productos
	* que cumplan el filtro introducido y los mostraremos por consola. Ejemplo: si introduce solo el nombre,
	* buscaremos los que tengan ese nombre contenido, no tiene que ser igual (usamos % en el valor del
	* argumento que pasamos, no en el ?). Si introducen solo en talla y color, los que tengan esa talla y ese
	* color.
	 * @param producto Producto 
	 * @return lista de productos 
	 */
	/**/
	public static List<Producto> buscar (Producto producto) {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			Connection con = Conexion.abreConexion();
			CallableStatement cs = con.prepareCall("CALL buscarProducto(?,?,?)");
			cs.setString(1, producto.getNombre()); 
			cs.setString(2, producto.getTalla()); 
			cs.setString(3, producto.getColor()); 
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
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
	/**
	 * se mostraran los productos cuyo stock sea menor de 5. Se pedira por consola en cuantas
	 * unidades queremos aumentarlo y actualizaremos el stock en la BD. Pero solo se aumentaran los que
	 * tengan stock menor de 5, no todos. Validar que la cantidad de stock a aumentar es mayor o igual de 0. Si es
	 * cero no actualizar nada.
	 * @return lista de productos con bajo stock
	 */
	public static List<Producto> bajoStock() {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idProducto, idCategoria, nombre, precio, descripcion, "
					+ "color, talla, stock FROM productos WHERE stock <  5");
			ResultSet rs = pst.executeQuery();
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
	
	public static void subirStock(int num, Producto prod) {
		if (num > 0) {
			try {
				Connection con = Conexion.abreConexion();
				PreparedStatement pst = con.prepareStatement("UPDATE productos set stock = stock + ? WHERE idProducto = ?");
				pst.setInt(1, num); 
				pst.setInt(2, prod.getIdproducto()); 
				pst.executeUpdate();
							
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				Conexion.cierraConexion();
			}
		}
	}
	
	public static void bajarStock(PedidoProducto pProd) {
		if (pProd.getUnidades() > 0) {
			try {
				Connection con = Conexion.abreConexion();
				PreparedStatement pst = con.prepareStatement("UPDATE productos set stock = stock - ? WHERE idProducto = ?");
				pst.setInt(1, pProd.getUnidades()); 
				pst.setInt(2, pProd.getProducto().getIdproducto()); 
				pst.executeUpdate();
							
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				Conexion.cierraConexion();
			}
		}
	}
	
	
	public static String dimeString(String orden, Scanner sc)
	{
		String s="";
			System.out.println(orden);
			s = sc.nextLine();

		return s;
	}
	
	
	
	
	
	
	
}
