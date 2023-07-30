package py.edu.ucsa.aso.web.jdbc.dto;


import java.sql.Date;
import java.time.LocalDate;

public class MontoCuota {
	private int id;
	private int montoCuota;
	private int numeroCuota;
	private Date fechaVencimiento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	public int getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(int montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}
