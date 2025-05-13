package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import clases.PedidoProducto;
import clases.Producto;
import util.Conexion;

public class PedidoProductoDAO {

	/*mostraremos el producto del que se hayan comprado más unidades. Si hay
	empate se mostrarán todos los empatados.*/
	public static List<PedidoProducto> masUnidades() {
		List<PedidoProducto> lista = new ArrayList<PedidoProducto>();
		try {
			Connection con = Conexion.abreConexion();
			PreparedStatement pst = con.prepareStatement("SELECT idproducto, nombre, unidades FROM pedidoproducto\r\n"
					+ "group by idProducto having unidades = (SELECT max(unidades) FROM pedidoproducto group by idProducto)");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				lista.add(new PedidoProducto(0, null, new Producto(rs.getInt("idProducto"), null, rs.getString("nombre"), 0, null, null, null, 0), rs.getInt("unidades"), 0));
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return lista;
	}
	
	
	
}
