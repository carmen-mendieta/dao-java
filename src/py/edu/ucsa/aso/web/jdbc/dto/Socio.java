package py.edu.ucsa.aso.web.jdbc.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Socio {
	private Integer id;
	private String nombres;
	private String apellidos;
	private String email;
	private Integer nroSocio;
	private String nroCedula;
	private LocalDate fechaIngreso;
	private Opcion estadoActual;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNroSocio() {
		return nroSocio;
	}

	public void setNroSocio(Integer nroSocio) {
		this.nroSocio = nroSocio;
	}

	public String getNroCedula() {
		return nroCedula;
	}

	public void setNroCedula(String nroCedula) {
		this.nroCedula = nroCedula;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Opcion getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Opcion estadoActual) {
		this.estadoActual = estadoActual;
	}

	public LocalDateTime getFechaEstadoActual() {
		return fechaEstadoActual;
	}

	public void setFechaEstadoActual(LocalDateTime fechaEstadoActual) {
		this.fechaEstadoActual = fechaEstadoActual;
	}

	public boolean isFundador() {
		return fundador;
	}

	public void setFundador(boolean fundador) {
		this.fundador = fundador;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Socio getSocioPoponente() {
		return socioPoponente;
	}

	public void setSocioPoponente(Socio socioPoponente) {
		this.socioPoponente = socioPoponente;
	}

	public Opcion getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(Opcion tipoSocio) {
		this.tipoSocio = tipoSocio;
	}

	private LocalDateTime fechaEstadoActual;
	private boolean fundador;
	private Usuario usuarioCreacion;
	private LocalDateTime fechaCreacion;
	private Socio socioPoponente;
	private Opcion tipoSocio;

}
