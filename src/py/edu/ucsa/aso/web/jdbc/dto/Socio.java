package py.edu.ucsa.aso.web.jdbc.dto;

public class Socio {
	private int nrosocio;
	private String nombre;
	private String apellido;
	private String cedula;
	private String celular;

	public int getNrosocio() {
		return nrosocio;
	}

	public void setNrosocio(int nrosocio) {
		this.nrosocio = nrosocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "Socio [nrosocio=" + nrosocio + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", celular=" + celular + "]";
	}

}
