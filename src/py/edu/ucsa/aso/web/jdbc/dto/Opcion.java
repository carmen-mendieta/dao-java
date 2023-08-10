package py.edu.ucsa.aso.web.jdbc.dto;

public class Opcion {
	private Integer id;
	private String codigo;
	private String descripcion;
	private String estado;
	private Dominio dominio;
	private Opcion opcionPadre;

	public Opcion() {
		super();
	}
	
	public Opcion(Integer id) {
		super();
		this.id = id;
	}

	public Opcion(Integer id, String codigo, String descripcion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
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

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public Opcion getOpcionPadre() {
		return opcionPadre;
	}

	public void setOpcionPadre(Opcion opcionPadre) {
		this.opcionPadre = opcionPadre;
	}

	@Override
	public String toString() {
		return "Opcion [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", dominio=" + dominio + ", opcionPadre=" + opcionPadre + "]";
	}

}
