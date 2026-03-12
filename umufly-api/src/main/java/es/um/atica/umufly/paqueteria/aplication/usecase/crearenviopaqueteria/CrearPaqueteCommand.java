package es.um.atica.umufly.paqueteria.aplication.usecase.crearenviopaqueteria;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.parking.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

public class CrearPaqueteCommand extends SyncCommand<ReservaParking> {

	private final DocumentoIdentidad documentoIdentidadTitular;
	private final UUID idParking;

	private CrearPaqueteCommand( DocumentoIdentidad documentoIdentidadTitular, UUID idParking ) {
		this.documentoIdentidadTitular = documentoIdentidadTitular;
		this.idParking = idParking;
	}

	public static CrearPaqueteCommand of( DocumentoIdentidad documentoIdentidadTitular, UUID idParking ) {
		return new CrearPaqueteCommand( documentoIdentidadTitular, idParking );
	}

	public DocumentoIdentidad getDocumentoIdentidadTitular() {
		return documentoIdentidadTitular;
	}

	public UUID getIdParking() {
		return idParking;
	}
}
