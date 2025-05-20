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
					"\nIntroduce la opción que deseas elegir: \n1-Mantenimiento \n2-Catálogo de Productos \n3-Pedidos \n4-Informes \n0-Salir",
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
					"\nIntroduce la opción que deseas elegir: \n1-Bajo stock \n2-Pedidos por cliente \n3-Productos más vendidos \n0-Salir",
					sc);
			switch (Menu) {
			case 1:
				if (ProductoDAO.bajoStock().size() == 0) {
					System.out.println("No hay productos con bajo stock");
				} 
				for (Producto producto : ProductoDAO.bajoStock()) {
					System.out.println(producto);
					int aumento = Funciones.dimeEntero("¿En cuántas unidades quieres aumentar este productos?", sc);
					if (aumento > 0) {
						ProductoDAO.subirStock(aumento, producto);
					} 
				}
				break;
			case 2:
				int idCliente = Funciones.dimeEntero("Introduce un código de un cliente", sc);

				int i = 0;
				for (PedidoProducto pedido : PedidoProductoDAO.buscarClienteId(idCliente)) {
					System.out.println(pedido.getPedido() + ", " + pedido.getProducto().getCategoria().getNombre() + 
						", " + pedido.getProducto().getNombre() + ", " + pedido.getUnidades() + " unidades");
					i++;
				} if (i == 0) {
					System.out.println("No existe cliente");
				}
				break;
			case 3:
				for (PedidoProducto pedidoProducto : PedidoProductoDAO.masUnidades()) {
					System.out.println(pedidoProducto);
				} break;
			default:
				break;
			}
		} while (Menu!=0);
	}

	public static void menu3Pedidos(Scanner sc) {
		int Menu=-1;
		do {
			Menu = Funciones.dimeEntero(
					"\nIntroduce la opción que deseas elegir: \n1-Crear Pedidos \n2-Ver pedidos \n0-Salir",
					sc);
			switch (Menu) {
			case 1:
				Prueba.crearPedido(sc);
				break;
			case 2:
				
				for (Pedido pedido : PedidoDAO.verPedidos()) {
					System.out.println(Funciones.convierte_Date_a_String(pedido.getFecha()) + ", " + pedido.getCliente().getNombre() + ", " + 
							pedido.getPrecioTotal() + ", " + pedido.getDireccionEnvio());
					for (PedidoProducto producto : PedidoProductoDAO.productos(pedido)) {
						System.out.println(producto.getProducto().getNombre() + ", " + producto.getProducto().getCategoria().getNombre() + ", " + producto.getUnidades());
					}
					System.out.println();
				} break;
				
			default:
				break;
			}
		} while (Menu != 0);
	}

	public static void menu2Catalogo(Scanner sc) {
		int Menu=-1;
		do {
			Menu = Funciones.dimeEntero(
					"\nIntroduce la opción que deseas elegir: \n1-Listar Productos por categoría \n2-Buscar Productos \n0-Salir",
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
					System.out.println("No hay categoría con idCategoria = " + idCat);
				}
				
				break;
			case 2:
				
				Producto producto = new Producto(0, null, ProductoDAO.dimeString("Introduce un nombre (enter para no buscar por nombre)", sc), 
						0, null, ProductoDAO.dimeString("Introduce un color (enter para no buscar por color)", sc), 
						ProductoDAO.dimeString("Introduce una talla (enter para no buscar por talla)", sc), 0);

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
					"\nIntroduce la opción que deseas elegir: \n1-Gestión de categorías \n2-Gestión de productos \n3-Gestión de clientes \n0-Salir",
					sc);
			switch (Menu) {
			case 1:
				Categoria nuevaCate = new Categoria();
				String Nombre = Funciones.dimeString("Indica el nombre de la nueva categoría", sc);
				nuevaCate.setNombre(Nombre);
				CategoriaDAO.gestionCategorias(nuevaCate);
				break;
			case 2:
				
				
				for (Categoria categoria : CategoriaDAO.listaCategoria()) {
					System.out.println(categoria);
				}
				
				Categoria cat = null;
				do {
					cat = new Categoria(Funciones.dimeEntero("Introduce un id de la categoría", sc), null) ;
				} while (!CategoriaDAO.listaCategoria().contains(cat));
				
				Producto producto = new Producto(0, cat, Funciones.dimeString("Nombre:", sc), Funciones.dimeDouble("Precio:", sc), 
						Funciones.dimeString("Descripción:", sc), Funciones.dimeString("Talla:", sc), Funciones.dimeString("Color:", sc), Funciones.dimeEntero("Stock:", sc));
				ProductoDAO.insertarProducto(producto);
				
				
				break;
			case 3:
				Cliente nuevoCliente=null;
				do {
					subMenu = Funciones.dimeEntero(
							"\nIntroduce la opción que deseas elegir: \n1-Alta de nuevos \n2-Búsqueda por código \n0-Salir",
							sc);
					switch (subMenu) {
					case 1:
						int cod = -1;
						do {
							cod = Funciones.dimeEntero("Introduce un código para un nuevo cliente", sc);
						} while (cod < 0 || ClienteDAO.buscarCliente(cod) != null);
						nuevoCliente = new Cliente(Funciones.dimeString("Introduce un nombre para un nuevo cliente", sc),
								Funciones.dimeString("Introduce una dirección para un nuevo cliente", sc), cod);
						ClienteDAO.altaDeNuevos(nuevoCliente);
						break;
					case 2:
						int codigo = Funciones.dimeEntero("Introduce un código de un cliente", sc);
						 nuevoCliente = ClienteDAO.buscarCliente(codigo);
						 if (nuevoCliente!=null) {
							System.out.println(nuevoCliente);
							int codi = -1;
							do {
								codi = Funciones.dimeEntero("Introduce un código para un nuevo cliente", sc);
								if (codi == nuevoCliente.getCodigo()) {
									break;
								}
							} while (codi < 0 || ClienteDAO.buscarCliente(codi) != null);
							nuevoCliente = new Cliente(Funciones.dimeString("Introduce un nombre para un nuevo cliente", sc),
									Funciones.dimeString("Introduce una dirección para un nuevo cliente", sc),codi);
							ClienteDAO.actualizaCliente(nuevoCliente,codigo);
						}else {
							System.out.println("El código no existe");
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
