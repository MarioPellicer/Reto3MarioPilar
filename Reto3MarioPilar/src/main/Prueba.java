package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import util.Funciones;

public class Prueba {

	static void crearPedido(Scanner sc) {
		Cliente nuevoCliente=null;
		Producto nuevoProducto=null;
		Pedido pedido = new Pedido();
		List<PedidoProducto> lista = new ArrayList<PedidoProducto>();
		
		//mostrar cliente
		do {
			try {
				int codigo = Funciones.dimeEntero("introduce un codigo de un cliente", sc);
				nuevoCliente = ClienteDAO.buscarCliente(codigo);
				System.out.println(nuevoCliente.getNombre() + ", " + nuevoCliente.getCodigo());
			} catch (Exception e) {
				System.out.println("no existe ese cliente ");
			}
		} while (!(nuevoCliente!=null));
		
		//añadir productos
		String nombre = "";
		do {
			nombre = Funciones.dimeString("introduce un nombre de un producto ('fin' para terminar)", sc);
			if (nombre.equals("fin")) {
				break;
			}
			PedidoProducto nuevoPedidoProducto = new PedidoProducto(); 
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
				lista.add(nuevoPedidoProducto);
			} else {
				System.out.println("no existe");
			}
			
		} while (true);
		
		String dir = "";
		do {
			dir = Funciones.dimeString("¿Quieres usar " + nuevoCliente.getDireccion() + " como dirección de envío? (si/no)", sc);
			if (dir.equals("no")) {
				nuevoCliente.setDireccion(Funciones.dimeString("Escribe la nueva dirección", sc));
			}
		} while (!(dir.equals("si") || dir.equals("no")));
		
		pedido.setCliente(nuevoCliente);
		pedido.setDireccionEnvio(nuevoCliente.getDireccion());
		pedido.setFecha(Funciones.convierte_LocalDate_a_Date(LocalDate.now()));
		
		//calcular precio total
		double precioTotal = 0;
		for (PedidoProducto pedidoProducto : lista) {
			
			pedidoProducto.calcularPrecio();
			System.out.println(pedidoProducto);
			precioTotal += pedidoProducto.getPrecio();
		}
		pedido.setPrecioTotal(precioTotal);
		PedidoDAO.insertarPedido(pedido);
		System.out.println("Pedido guardado. Precio total: " + precioTotal + "€");
		
		for (PedidoProducto pedidoProducto : lista) {
			pedidoProducto.setPedido(pedido);
			
		}
	}

}
/*Una vez que ya hemos seleccionado los productos a comprar, se mostrará la dirección del cliente que
habíamos seleccionado antes y preguntaremos si usamos esa dirección como dirección de envío o no.
Si nos dicen que no pediremos la nueva y pondremos esa como dirección de envío.
Guardaremos el pedido en la base de datos mostrando “Pedido guardado “, e indicaremos el precio
total.*/
