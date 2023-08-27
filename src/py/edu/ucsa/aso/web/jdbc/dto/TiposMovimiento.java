package py.edu.ucsa.aso.web.jdbc.dto;

public class TiposMovimiento {
	private int id;
	private String codigo;
	private String descripcion;
	private String estado;
	private String tipoDebCred;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoDebCred() {
		return tipoDebCred;
	}

	public void setTipoDebCred(String tipoDebCred) {
		this.tipoDebCred = tipoDebCred;
	}

	@Override
	public String toString() {
		return "TiposMovimiento [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado="
				+ estado + ", tipoDebCred=" + tipoDebCred + "]";
	}
	
	

}
