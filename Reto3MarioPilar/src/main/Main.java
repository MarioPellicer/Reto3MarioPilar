package main;

import java.util.Scanner;
import clases.Categoria;
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;
import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.PedidoProductoDAO;
import dao.ProductoDAO;
import util.Funciones;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = -1;
		do {
			opcion = Funciones.dimeEntero(
					"introduce la opcion que deseas elegir: \n1-Mantenimiento \n2-Catalogo de Productos \n3-Pedidos \n4-Informes \n0-salir",
					sc);
			switch (opcion) {
			case 1:
				menu1Mantenimiento(sc);
				break;
			case 2:
				menu2Catalogo(sc);
				break;
			case 3:
				menu3Pedidos(sc);
				break;
			case 4:
				menu4Informes(sc);
				break;
			default:
				break;
			}
		} while (opcion != 0);

		sc.close();
	}

	public static void menu4Informes(Scanner sc) {
		int Menu=-1;
		do {
			Menu = Funciones.dimeEntero(
					"introduce la opcion que deseas elegir: \n1-Bajo stock \n2-Pedidos por cliente \n3-Productos mas vendidos \n0-salir",
					sc);
			switch (Menu) {
			case 1:
				for (Producto producto : ProductoDAO.bajoStock()) {
					System.out.println(producto);
				}
				int aumento = Funciones.dimeEntero("¿En cuántas unidades quieres aumentar estos productos?", sc);
				if (aumento > 0) {
					ProductoDAO.subirStock(aumento);
				}
				break;
			case 2:
				int idCliente = Funciones.dimeEntero("introudce un id de un cliente", sc);
				for (Pedido pedido : PedidoDAO.buscarClienteId(Menu)) {
					System.out.println(pedido);
				}
				break;
			case 3:
				for (PedidoProducto pedidoProducto : PedidoProductoDAO.masUnidades()) {
					System.out.println(pedidoProducto);
				}
				break;
			default:
				break;
			}
		} while (Menu!=0);
	}

	public static void menu3Pedidos(Scanner sc) {
		int Menu=-1;
		do {
			Menu = Funciones.dimeEntero(
					"introduce la opcion que deseas elegir: \n1-Crear Pedidos \n2- Ver pedidos \n0-salir",
					sc);
			switch (Menu) {
			case 1:
				
				break;
			case 2:
				
				for (PedidoProducto pedido : PedidoProductoDAO.verPedidos()) {
					System.out.println(pedido.getPedido().getFecha()+","+pedido.getPedido().getCliente().getNombre()+","+
							pedido.getPedido().getPrecioTotal()+","+pedido.getPedido().getCliente().getDireccion()+","
							+pedido.getProducto().getCategoria()+","+pedido.getProducto().getNombre()+","+pedido.getUnidades());
				}
				break;
			default:
				break;
			}
		} while (Menu != 0);
	}

	public static void menu2Catalogo(Scanner sc) {
		int Menu=-1;
		do {
			Menu = Funciones.dimeEntero(
					"introduce la opcion que deseas elegir: \n1-Listar Productos por categoria \n2-Buscar Productos \n0-salir",
					sc);
			switch (Menu) {
			case 1:
				for (Categoria categoria : CategoriaDAO.listaCategoria()) {
					System.out.println(categoria);
				}	
				int idCat = Funciones.dimeEntero("Elige una categoría por su id", sc);
				if (CategoriaDAO.buscarCategoria(idCat) != null) {
					for (Producto prod : ProductoDAO.selectCategoria(idCat)) {
					System.out.println(prod);
					}
				} else {
					System.out.println("No hay categoria con idCategoria = " + idCat);
				}
				
				break;
			case 2:
				
				Producto producto = new Producto(0, null, ProductoDAO.dimeString("Introduce un nombre (enter para no buscar por nombre)", sc), 0, null, ProductoDAO.dimeString("Introduce un color (enter para no buscar por color)", sc), ProductoDAO.dimeString("Introduce una talla (enter para no buscar por talla)", sc), 0);
				System.out.println(ProductoDAO.buscar(producto).size());
				for (Producto prod : ProductoDAO.buscar(producto)) {
					System.out.println(prod);
				}
				break;
			default:
				break;
			}
		} while (Menu != 0);
	}

	public static void menu1Mantenimiento(Scanner sc) {
		int Menu=-1,subMenu=-1;
		do {
			Menu = Funciones.dimeEntero(
					"introduce la opcion que deseas elegir: \n1-Gestion de categorias \n2-Gestion de productos \n3-Gestion de clientes \n0-salir",
					sc);
			switch (Menu) {
			case 1:
				Categoria nuevaCate = new Categoria();
				System.out.println("Indica el nombre de la nueva categoria");
				String Nombre = sc.nextLine();
				nuevaCate.setNombre(Nombre);
				CategoriaDAO.gestionCategorias(nuevaCate);
				break;
			case 2:
				
				
				for (Categoria categoria : CategoriaDAO.listaCategoria()) {
					System.out.println(categoria);
				}
				Producto producto = new Producto(0, new Categoria(Funciones.dimeEntero("introduce un id de la categoria", sc), null), Funciones.dimeString("Nombre:", sc), Funciones.dimeDouble("Precio:", sc), 
										Funciones.dimeString("Descripcion:", sc), Funciones.dimeString("Talla:", sc), Funciones.dimeString("Color:", sc), Funciones.dimeEntero("Stock:", sc));
				ProductoDAO.insertarProducto(producto);
				break;
			case 3:
				Cliente nuevoCliente=null;
				do {
					subMenu = Funciones.dimeEntero(
							"introduce la opcion que deseas elegir: \n1-Alta de nuevos \n2-Busqueda por codigo \n0-salir",
							sc);
					switch (subMenu) {
					case 1:
						 nuevoCliente = new Cliente(Funciones.dimeString("introduce un nombre para un nnuevo cliente", sc),
								Funciones.dimeString("introduce una direccion para un nuevo cliente", sc),Funciones.dimeEntero("introduce un codigo para un nuevo cliente", sc));
						ClienteDAO.altaDeNuevos(nuevoCliente);
						break;
					case 2:
						int codigo = Funciones.dimeEntero("introduce un codigo de un cliente", sc);
						 nuevoCliente = ClienteDAO.buscarCliente(codigo);
						 if (nuevoCliente!=null) {
							System.out.println(nuevoCliente);
							nuevoCliente = new Cliente(Funciones.dimeString("introduce un nombre para un nuevo cliente", sc),
									Funciones.dimeString("introduce una direccion para un nuevo cliente", sc),Funciones.dimeEntero("introduce un codigo para un nuevo cliente", sc));
							ClienteDAO.actualizaCliente(nuevoCliente,codigo);
						}else {
							System.out.println("el codgo no existe");
						}
						break;
					default:
						break;
					}
				} while (subMenu != 0);
				break;
			default:
				break;
			}
		} while (Menu != 0);
	}
}
