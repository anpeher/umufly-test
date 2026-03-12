package es.um.atica.umufly.paqueteria.domain.model;

import java.util.UUID;

// Agregqado Raiz
public class Envio {

	private UUID id;
	private ParticipanteEnvio remitente;
	private ParticipanteEnvio destinatario;
	private PaqueteEnvio paqueteEnvio;
	private UUID idVuelo;
	private UUID seguimiento;
	private Importe importe;
	private EstadoPaquete estado;

	private final static double PRECIO_PESO_FRAGIL = 4.0;
	private final static double PRECIO_PESO_ESTANDAR = 2.5;

	private Envio( UUID id, ParticipanteEnvio remitente, ParticipanteEnvio destinatario, PaqueteEnvio paqueteEnvio, UUID idVuelo, UUID seguimiento, Importe importe, EstadoPaquete estado ) {
		this.id = id;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.paqueteEnvio = paqueteEnvio;
		this.idVuelo = idVuelo;
		this.seguimiento = seguimiento;
		this.importe = importe;
		this.estado = estado;
	}

	public static Envio of( UUID id, ParticipanteEnvio remitente, ParticipanteEnvio destinatario, PaqueteEnvio paqueteEnvio, UUID idVuelo, UUID seguimiento, EstadoPaquete estado ) {
		Importe importe;
		if ( paqueteEnvio.isFragil() ) {
			importe = new Importe( paqueteEnvio.getPeso() * PRECIO_PESO_FRAGIL );
		} else {
			importe = new Importe( paqueteEnvio.getPeso() * PRECIO_PESO_ESTANDAR );
		}

		return new Envio( id, remitente, destinatario, paqueteEnvio, idVuelo, seguimiento, importe, estado );

	}

	public static Envio solicitarEnvio( ParticipanteEnvio remitente, ParticipanteEnvio destinatario, PaqueteEnvio paqueteEnvio, UUID idVuelo, EstadoPaquete estado ) {

		return of( UUID.randomUUID(), remitente, destinatario, paqueteEnvio, idVuelo, UUID.randomUUID(), EstadoPaquete.FACTURADO );
	}

	public void lanzarEnvio() {
		estado = EstadoPaquete.EN_TRANSITO;
	}

	public void envioEntregado() {
		estado = EstadoPaquete.ENTREGADO;
	}

	public UUID getId() {
		return id;
	}

	public void setId( UUID id ) {
		this.id = id;
	}

	public ParticipanteEnvio getRemitente() {
		return remitente;
	}

	public void setRemitente( ParticipanteEnvio remitente ) {
		this.remitente = remitente;
	}

	public ParticipanteEnvio getDestinatario() {
		return destinatario;
	}

	public void setDestinatario( ParticipanteEnvio destinatario ) {
		this.destinatario = destinatario;
	}

	public PaqueteEnvio getPaqueteEnvio() {
		return paqueteEnvio;
	}

	public void setPaqueteEnvio( PaqueteEnvio paqueteEnvio ) {
		this.paqueteEnvio = paqueteEnvio;
	}

	public UUID getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo( UUID idVuelo ) {
		this.idVuelo = idVuelo;
	}

	public UUID getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento( UUID seguimiento ) {
		this.seguimiento = seguimiento;
	}

	public Importe getImporte() {
		return importe;
	}

	public void setImporte( Importe importe ) {
		this.importe = importe;
	}

	public EstadoPaquete getEstado() {
		return estado;
	}

	public void setEstado( EstadoPaquete estado ) {
		this.estado = estado;
	}

}
