package clases;

public class Categoria {

	private int idCategoria;
	private String nombre;
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categoria(int idCategoria, String nombre) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombre() {
		return nombre;
	}
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
