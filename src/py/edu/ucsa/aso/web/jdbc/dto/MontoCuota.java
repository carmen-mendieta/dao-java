package py.edu.ucsa.aso.web.jdbc.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MontoCuota {
	private Integer id;
	private double monto;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaIniVigencia;
	private LocalDateTime fechaFinVigencia;
	private LocalDateTime fechaInactivacion;
	private String estado;
	private Usuario usuarioInactivacion;
	private int numeroCuota;
	private Date fechaVencimiento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaIniVigencia() {
		return fechaIniVigencia;
	}

	public void setFechaIniVigencia(LocalDateTime fechaIniVigencia) {
		this.fechaIniVigencia = fechaIniVigencia;
	}

	public LocalDateTime getFechaFinVigencia() {
		return fechaFinVigencia;
	}

	public void setFechaFinVigencia(LocalDateTime fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}

	public LocalDateTime getFechaInactivacion() {
		return fechaInactivacion;
	}

	public void setFechaInactivacion(LocalDateTime fechaInactivacion) {
		this.fechaInactivacion = fechaInactivacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuarioInactivacion() {
		return usuarioInactivacion;
	}

	public void setUsuarioInactivacion(Usuario usuarioInactivacion) {
		this.usuarioInactivacion = usuarioInactivacion;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}
