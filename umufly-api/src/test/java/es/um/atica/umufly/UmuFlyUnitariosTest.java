package es.um.atica.umufly;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.core.config.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import es.um.atica.umufly.vuelos.domain.model.Avion;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.CorreoElectronico;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.EstadoReserva;
import es.um.atica.umufly.vuelos.domain.model.EstadoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Itinerario;
import es.um.atica.umufly.vuelos.domain.model.Nacionalidad;
import es.um.atica.umufly.vuelos.domain.model.NombreCompleto;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.TipoDocumento;
import es.um.atica.umufly.vuelos.domain.model.TipoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@TestClassOrder( ClassOrderer.OrderAnnotation.class )
public class UmuFlyUnitariosTest {

	private DocumentoIdentidad titular;
	private Pasajero pasajero;
	private Vuelo vueloPendiente;
	private Vuelo vueloCancelado;
	private Vuelo vueloCompletado;
	private final LocalDateTime SALIDA = LocalDateTime.of( 2025, 6, 15, 10, 0 );
	private final LocalDateTime LLEGADA = LocalDateTime.of( 2025, 6, 15, 12, 0 );
	private final LocalDateTime ANTES_DE_SALIDA = SALIDA.minusHours( 2 );
	private final LocalDateTime DESPUES_DE_SALIDA = SALIDA.plusMinutes( 20 );

	@BeforeEach
	void setUp() {
		titular = new DocumentoIdentidad( TipoDocumento.NIF, "12345678Z" );
		pasajero = Pasajero.of( titular, new NombreCompleto( "Juan", "García", "López" ), new CorreoElectronico( "juan@ejemplo.com" ), new Nacionalidad( "Española" ) );
		Itinerario itinerario = new Itinerario( SALIDA, LLEGADA, "MAD", "BCN" );
		Avion avion = new Avion( 180 );
		vueloPendiente = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.PENDIENTE, avion );
		vueloCancelado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.CANCELADO, avion );
		vueloCompletado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.COMPLETADO, avion );
	}

	@Nested
	@DisplayName( "formalizarReservaVuelo" )
	@Order( 1 )
	class formalizarReserva {

		@Test
		void formalizarReservaVuelo() {
			ReservaVuelo reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_DE_SALIDA, 0, 10 );
			reserva.formalizarReserva();
			assertEquals( EstadoReserva.ACTIVA, reserva.getEstado() );
		}

	}

	@Nested
	@DisplayName( "crearReservaVuelo" )
	@Order( 2 )
	class crearReserva {

		@Test
		void crearReservaVuelo() {
			ReservaVuelo reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_DE_SALIDA, 0, 10 );
			assertEquals( EstadoReserva.PENDIENTE, reserva.getEstado() );
		}

	}

}
