package es.um.atica.umufly.paqueteria.domain.model;

import java.util.regex.Pattern;

public record Telefono( String valor ) {

	private static final String PATRON_TELEFONO = "^[0-9+\\-\\s()]{0,30}$";

	public Telefono {
		if ( valor == null || valor.isBlank() ) {
			throw new IllegalArgumentException( "El telefono no puede ser nulo" );
		}
		if ( !Pattern.matches( PATRON_TELEFONO, valor ) ) {
			throw new IllegalArgumentException( "El telefono no tiene un formato válido" );
		}
	}
}
