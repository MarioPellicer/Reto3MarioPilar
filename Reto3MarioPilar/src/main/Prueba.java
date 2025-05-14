package main;

import java.util.Scanner;

import clases.Categoria;
import clases.Producto;
import dao.CategoriaDAO;
import dao.ProductoDAO;
import util.Funciones;

public class Prueba {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
		

	}

}
