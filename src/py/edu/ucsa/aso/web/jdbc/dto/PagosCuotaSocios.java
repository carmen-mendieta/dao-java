package py.edu.ucsa.aso.web.jdbc.dto;

import java.time.LocalDateTime;

public class PagosCuotaSocios {
	private int id;
	private int anhoCuota;
	private boolean exonerado;
	private LocalDateTime fechaCreacion;
	private int mesCuota;
	private int montoCuota;
	private Opcion estado;
	private Opcion motivoExoneracion;
	private MovimientosSocios movimientoSocio;
	private Socio socio;
	private Usuario usuarioCreacion;

	// Constructor
	public PagosCuotaSocios() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnhoCuota() {
		return anhoCuota;
	}

	public void setAnhoCuota(int anhoCuota) {
		this.anhoCuota = anhoCuota;
	}

	public boolean isExonerado() {
		return exonerado;
	}

	public void setExonerado(boolean exonerado) {
		this.exonerado = exonerado;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getMesCuota() {
		return mesCuota;
	}

	public void setMesCuota(int mesCuota) {
		this.mesCuota = mesCuota;
	}

	public int getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(int montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Opcion getEstado() {
		return estado;
	}

	public void setEstado(Opcion estado) {
		this.estado = estado;
	}

	public Opcion getMotivoExoneracion() {
		return motivoExoneracion;
	}

	public void setMotivoExoneracion(Opcion motivoExoneracion) {
		this.motivoExoneracion = motivoExoneracion;
	}

	public MovimientosSocios getMovimientoSocio() {
		return movimientoSocio;
	}

	public void setMovimientoSocio(MovimientosSocios movimientoSocio) {
		this.movimientoSocio = movimientoSocio;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

}
