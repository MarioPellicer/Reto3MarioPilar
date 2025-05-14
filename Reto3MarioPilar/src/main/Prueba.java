package main;

import java.util.Scanner;

import clases.Cliente;
import clases.PedidoProducto;
import clases.Producto;
import dao.ClienteDAO;
import dao.ProductoDAO;
import util.Funciones;

public class Prueba {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Cliente nuevoCliente=null;
		Producto nuevoProducto=null;
		
		do {
			try {
				int codigo = Funciones.dimeEntero("introduce un codigo de un cliente", sc);
				nuevoCliente = ClienteDAO.buscarCliente(codigo);
				System.out.println(nuevoCliente.getNombre() + ", " + nuevoCliente.getCodigo());
			} catch (Exception e) {
				System.out.println("no existe ese cliente ");
			}
		} while (!(nuevoCliente!=null));
		
		PedidoProducto nuevoPedidoProducto = new PedidoProducto();
		
		String nombre = "";
		do {
			nombre = Funciones.dimeString("introduce un nombre de un producto ('fin' para terminar)", sc);
			nuevoProducto = ProductoDAO.selectNombre(nombre);
				if (nuevoProducto!=null) {
					//añado producto
					nuevoPedidoProducto.setProducto(nuevoProducto);
					int unidades = Funciones.dimeEntero("¿Cuantas unidades quieres?", sc);
					if (unidades <= nuevoProducto.getStock()) {
						//añado unidades pedidas
						nuevoPedidoProducto.setUnidades(unidades);
					} else {
						//añado todo el stock
						nuevoPedidoProducto.setUnidades(nuevoProducto.getStock());
					}
					break;
				} else {
					System.out.println("no existe");
				}
				
			
			
		} while (nombre.equals("fin"));
	
		
		

	}

}
