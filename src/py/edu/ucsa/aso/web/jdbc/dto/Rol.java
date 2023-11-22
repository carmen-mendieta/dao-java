package py.edu.ucsa.aso.web.jdbc.dto;

public class Rol {
	private int id;
	private String nombreRol;

	public Rol() {
		super();
	}

	public Rol(int id, String nombreRol) {
		super();
		this.id = id;
		this.nombreRol = nombreRol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombreRol=" + nombreRol + "]";
	}
	

}
