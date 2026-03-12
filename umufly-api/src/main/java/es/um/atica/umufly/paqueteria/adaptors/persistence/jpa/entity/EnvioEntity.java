package es.um.atica.umufly.paqueteria.adaptors.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "ENVIO", schema = "FORMACION_TICARUM" )
public class EnvioEntity {

	@Id
	@Column( name = "id_envio", length = 100 )
	private String idEnvio;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_remitente", nullable = false )
	private String remitente;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_destinatario", nullable = false )
	private String destinatario;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_paquete", nullable = false )
	private String paquete;

	@NotNull
	@ManyToOne
	@JoinColumn( name = "id_vuelo", nullable = false )
	private String vuelo;

	@NotNull
	@Column( name = "id_seguimiento", length = 50, nullable = false, unique = true )
	private String idSeguimiento;

	@NotNull
	@Column( name = "importe_envio", precision = 10, scale = 2, nullable = false )
	private double importeEnvio;

	@NotNull
	@Column( name = "estado", length = 20, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private EstadoPaqueteEnum estado;

	public String getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio( String idEnvio ) {
		this.idEnvio = idEnvio;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente( String remitente ) {
		this.remitente = remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario( String destinatario ) {
		this.destinatario = destinatario;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete( String paquete ) {
		this.paquete = paquete;
	}

	public String getVuelo() {
		return vuelo;
	}

	public void setVuelo( String vuelo ) {
		this.vuelo = vuelo;
	}

	public String getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento( String idSeguimiento ) {
		this.idSeguimiento = idSeguimiento;
	}

	public double getImporteEnvio() {
		return importeEnvio;
	}

	public void setImporteEnvio( double importeEnvio ) {
		this.importeEnvio = importeEnvio;
	}

	public EstadoPaqueteEnum getEstado() {
		return estado;
	}

	public void setEstado( EstadoPaqueteEnum estado ) {
		this.estado = estado;
	}

}
