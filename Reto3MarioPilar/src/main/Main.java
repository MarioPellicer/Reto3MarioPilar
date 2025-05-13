package main;

import java.util.Scanner;

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
					"introduce la opcion que deseas elegir: \n1-Bajo stock \n2- Pedidos por cliente \n3- Productos mas vendidos \n0-salir",
					sc);
			switch (Menu) {
			case 1:
				
				break;
			case 2:

				break;
			case 3:
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
					"introduce la opcion que deseas elegir: \n1-Listar Productos por categoria \n2- Buscar Productos \n0-salir",
					sc);
			switch (Menu) {
			case 1:
				
				break;
			case 2:

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

				break;
			case 2:

				break;
			case 3:
				do {
					subMenu = Funciones.dimeEntero(
							"introduce la opcion que deseas elegir: \n1-Alta de nuevos \n2-Busqueda por codigo \n0-salir",
							sc);
					switch (subMenu) {
					case 1:

						break;
					case 2:

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
