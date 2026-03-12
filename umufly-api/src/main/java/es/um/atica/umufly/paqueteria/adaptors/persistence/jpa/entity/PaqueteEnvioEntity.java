package es.um.atica.umufly.paqueteria.adaptors.persistence.jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "PAQUETE_ENVIO", schema = "FORMACION_TICARUM" )
public class PaqueteEnvioEntity {

	@Id
	@Column( name = "id_paquete", length = 100 )
	private String idPaquete;

	@Column( name = "descripcion", length = 500, nullable = false )
	private String descripcion;

	@Column( name = "peso_kg", precision = 8, scale = 2, nullable = false )
	private BigDecimal pesoKg;

	@Column( name = "fragil", length = 1, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private FragilEnum fragil = FragilEnum.N;

	public String getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete( String idPaquete ) {
		this.idPaquete = idPaquete;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion( String descripcion ) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg( BigDecimal pesoKg ) {
		this.pesoKg = pesoKg;
	}

	public FragilEnum getFragil() {
		return fragil;
	}

	public void setFragil( FragilEnum fragil ) {
		this.fragil = fragil;
	}

}
