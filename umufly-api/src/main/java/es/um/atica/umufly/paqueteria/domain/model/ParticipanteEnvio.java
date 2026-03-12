package es.um.atica.umufly.paqueteria.domain.model;

import java.util.UUID;

public class ParticipanteEnvio {

	private UUID id;
	private NombreCompleto nombre;
	private DocumentoIdentidad identificador;
	private CorreoElectronico correo;
	private Telefono telefono;
	private TipoParticipante tipoParticipante;

	private ParticipanteEnvio( UUID id, NombreCompleto nombre, DocumentoIdentidad identificador, CorreoElectronico correo, Telefono telefono, TipoParticipante tipoParticipante ) {
		this.id = id;
		this.nombre = nombre;
		this.identificador = identificador;
		this.correo = correo;
		this.telefono = telefono;
		this.tipoParticipante = tipoParticipante;
	}

	public ParticipanteEnvio of( UUID id, NombreCompleto nombre, DocumentoIdentidad identificador, CorreoElectronico correo, Telefono telefono, TipoParticipante tipoParticipante ) {
		return new ParticipanteEnvio( id, nombre, identificador, correo, telefono, tipoParticipante );
	}

	public UUID getId() {
		return id;
	}

	public void setId( UUID id ) {
		this.id = id;
	}

	public NombreCompleto getNombre() {
		return nombre;
	}

	public void setNombre( NombreCompleto nombre ) {
		this.nombre = nombre;
	}

	public DocumentoIdentidad getIdentificador() {
		return identificador;
	}

	public void setIdentificador( DocumentoIdentidad identificador ) {
		this.identificador = identificador;
	}

	public CorreoElectronico getCorreo() {
		return correo;
	}

	public void setCorreo( CorreoElectronico correo ) {
		this.correo = correo;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono( Telefono telefono ) {
		this.telefono = telefono;
	}

	public TipoParticipante getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante( TipoParticipante tipoParticipante ) {
		this.tipoParticipante = tipoParticipante;
	}

}
