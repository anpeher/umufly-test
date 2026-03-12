package es.um.atica.umufly.paqueteria.domain.model;

import java.util.UUID;

import es.um.atica.umufly.paqueteria.domain.exception.ErrorPesoException;

public class PaqueteEnvio {

	private UUID id;
	private String descripcion;
	private double peso;
	private Fragil fragil;

	private PaqueteEnvio( UUID id, String descripcion, double peso, Fragil fragil ) {
		this.id = id;
		this.descripcion = descripcion;
		this.peso = peso;
		this.fragil = fragil;
	}

	public PaqueteEnvio of( UUID id, String descripcion, double peso, Fragil fragil ) {
		if ( peso < 1 ) {
			throw new ErrorPesoException( "el peso debe de ser mayor que 0" );
		}

		return new PaqueteEnvio( id, descripcion, peso, fragil );
	}

	public boolean isFragil() {
		return Fragil.S.equals( fragil );
	}

	public UUID getId() {
		return id;
	}

	public void setId( UUID id ) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion( String descripcion ) {
		this.descripcion = descripcion;
	}

	public Fragil getFragil() {
		return fragil;
	}

	public void setFragil( Fragil fragil ) {
		this.fragil = fragil;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso( double peso ) {
		this.peso = peso;
	}

}
