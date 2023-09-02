package py.edu.ucsa.aso.web.jdbc.dto;

public class Dominio {
	private Integer id;
	private String codigo;
	private String descripcion;
	private String estado;
	private Dominio dominioPadre;

	
	
	public Dominio() {
		super();
	}

	public Dominio(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Dominio getDominioPadre() {
		return dominioPadre;
	}

	public void setDominioPadre(Dominio dominioPadre) {
		this.dominioPadre = dominioPadre;
	}

	@Override
	public String toString() {
		return "Dominio [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", dominioPadre=" + dominioPadre + "]";
	}

}
