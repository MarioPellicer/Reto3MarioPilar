package clases;

public class Categoria {

	private int idCategoria;
	private String nombre;
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor con todos los atributos 
	 * @param idCategoria int
	 * @param nombre String
	 */
	public Categoria(int idCategoria, String nombre) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}
	/**
	 * devuelve el id de la categoria
	 * @return int devuelve el id de la categoria
	 */
	public int getIdCategoria() {
		return idCategoria;
	}
	/**
	 * cambia el idcategoria
	 * @param idCategoria int devuelve el id de la categoria 
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * devuelve el nombre
	 * @return String devuelve el nombre de la categoria
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * cambia el nombre
	 * @param nombre String 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return idCategoria + " - " + nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Categoria)
		{
			Categoria per = (Categoria)obj;
			if(per.getIdCategoria() == this.idCategoria)
				return true;
		}
		return false;
	}
}
