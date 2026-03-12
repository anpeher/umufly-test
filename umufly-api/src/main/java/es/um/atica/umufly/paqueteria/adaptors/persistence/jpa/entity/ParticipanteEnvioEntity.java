package es.um.atica.umufly.paqueteria.adaptors.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "PARTICIPANTE_ENVIO", schema = "FORMACION_TICARUM" )
public class ParticipanteEnvioEntity {

	@Id
	@NotNull
	@Column( name = "ID", nullable = false, length = 36 )
	private String id_participante;

	@NotNull
	@Column( name = "nombre", length = 100, nullable = false )
	private String nombre;

	@NotNull
	@Column( name = "apellidos", length = 150, nullable = false )
	private String apellidos;

	@NotNull
	@Column( name = "numero_documento", length = 15, nullable = false )
	private String numeroDocumento;

	@NotNull
	@Column( name = "tipo_documento", length = 2, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private TipoDocumentoEnum tipoDocumento;

	@NotNull
	@Column( name = "email", length = 250, nullable = false )
	private String email;

	@NotNull
	@Column( name = "telefono", length = 30 )
	private String telefono;

	@NotNull
	@Column( name = "tipo_participante", length = 20 )
	@Enumerated( value = EnumType.STRING )
	private TipoParticipanteEnum TipoParticipante;

	public String getId_participante() {
		return id_participante;
	}

	public void setId_participante( String id_participante ) {
		this.id_participante = id_participante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos( String apellidos ) {
		this.apellidos = apellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento( String numeroDocumento ) {
		this.numeroDocumento = numeroDocumento;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento( TipoDocumentoEnum tipoDocumento ) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono( String telefono ) {
		this.telefono = telefono;
	}

	public TipoParticipanteEnum getTipoParticipante() {
		return TipoParticipante;
	}

	public void setTipoParticipante( TipoParticipanteEnum tipoParticipante ) {
		TipoParticipante = tipoParticipante;
	}

}
