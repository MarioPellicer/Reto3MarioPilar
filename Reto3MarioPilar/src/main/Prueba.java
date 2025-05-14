package main;

import java.util.Scanner;

import clases.Categoria;
import clases.Cliente;
import clases.Producto;
import dao.CategoriaDAO;
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
				System.out.println(nuevoCliente.getNombre());
			} catch (Exception e) {
				System.out.println("introduce un codigo ");
			}
		} while (!(nuevoCliente!=null));
		do {
			String nombre = Funciones.dimeString("introduce un nombre de un producto", sc);
					nuevoProducto = ProductoDAO.selectNombre(nombre);
				if (nuevoProducto!=null) {
					
					System.out.println(nuevoProducto);
					break;
				} else {
					System.out.println("no existe");
				}
				
			
			
		} while (true);
	
		
		

	}

}
