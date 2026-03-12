package es.um.atica.umufly.paqueteria.aplication.usecase.crearenviopaqueteria;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.parking.application.port.ReservasParkingReadRepository;
import es.um.atica.umufly.parking.application.port.ReservasParkingWritePort;
import es.um.atica.umufly.parking.application.port.ReservasParkingWriteRepository;
import es.um.atica.umufly.parking.domain.model.ReservaParking;

@Component
public class CancelarPaqueteCommandHandler implements SyncCommandHandler<ReservaParking, CrearPaqueteCommand> {

	private final ReservasParkingReadRepository reservasParkingReadRepository;
	private final ReservasParkingWriteRepository reservasParkingWriteRepository;
	private final ReservasParkingWritePort reservasParkingWritePort;
	private final Clock clock;

	public CancelarPaqueteCommandHandler( ReservasParkingReadRepository reservasparkingRepository, ReservasParkingWriteRepository reservasParkingWriteRepository, ReservasParkingWritePort reservasParkingWritePort, Clock clock ) {
		this.reservasParkingReadRepository = reservasparkingRepository;
		this.reservasParkingWriteRepository = reservasParkingWriteRepository;
		this.reservasParkingWritePort = reservasParkingWritePort;
		this.clock = clock;
	}

	@Override
	public ReservaParking handle( CrearPaqueteCommand command ) throws Exception {
		// 1. Recuperamos la reserva
		ReservaParking reserva = reservasParkingReadRepository.findParkingById( command.getDocumentoIdentidadTitular(), command.getIdParking() );

		reserva.cancelarParking( LocalDateTime.now( clock ) );
		UUID idReservaFormalizada = reservasParkingReadRepository.findIdFormalizadaByParkingById( command.getIdParking() );

		// 2. Cancelamos la reserva llamando al backoffice para que se haga eco de la cancelacion
		if ( idReservaFormalizada == null ) {
			throw new NoSuchElementException( "La reserva indicada no ha sido solicitada a traves de umufly, pongase en contacto con MUCHO VUELO" );
		}
		reservasParkingWritePort.cancelarParking( command.getDocumentoIdentidadTitular(), idReservaFormalizada );

		// 2. Cancelamos la reserva en el fronOffice
		reservasParkingWriteRepository.cancelParking( reserva.getId() );

		return reserva;
	}

}
