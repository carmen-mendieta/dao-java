package py.edu.ucsa.aso.web.jdbc.dto;

import java.time.LocalDateTime;

public class MovimientosSocios {
	private Integer id;
	private LocalDateTime fechaPago;
	private int monto;
	private Opcion concepto;
	private Opcion estado;
	private Opcion medioPago;
	private Socio socio;
	private TiposMovimiento tipoMovimiento;
	private Usuario usuarioAprobacion;
	private Usuario usuarioCreacion;

	public MovimientosSocios() {
		super();
	}

	

	public MovimientosSocios(Integer id, TiposMovimiento tipoMovimiento) {
		super();
		this.id = id;
		this.tipoMovimiento = tipoMovimiento;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public Opcion getConcepto() {
		return concepto;
	}

	public void setConcepto(Opcion concepto) {
		this.concepto = concepto;
	}

	public Opcion getEstado() {
		return estado;
	}

	public void setEstado(Opcion estado) {
		this.estado = estado;
	}

	public Opcion getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(Opcion medioPago) {
		this.medioPago = medioPago;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public TiposMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TiposMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Usuario getUsuarioAprobacion() {
		return usuarioAprobacion;
	}

	public void setUsuarioAprobacion(Usuario usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	@Override
	public String toString() {
		return "MovimientosSocios [id=" + id + ", fechaPago=" + fechaPago + ", monto=" + monto + ", concepto="
				+ concepto + ", estado=" + estado + ", medioPago=" + medioPago + ", socio=" + socio
				+ ", tipoMovimiento=" + tipoMovimiento + ", usuarioAprobacion=" + usuarioAprobacion
				+ ", usuarioCreacion=" + usuarioCreacion + "]";
	}

	
	
}
