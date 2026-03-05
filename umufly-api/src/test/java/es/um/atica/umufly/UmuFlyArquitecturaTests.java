package es.um.atica.umufly;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaParameter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import jakarta.validation.Valid;


@AnalyzeClasses( packages = "es.um.atica.umufly" )
public class UmuFlyArquitecturaTests {

	private static final ArchCondition<JavaMethod> METODO_REST_VALIDA_PARAMETROS =
			new ArchCondition<>("Rest debe tener @Valid o @Validated en parámetros @RequestBody") {
		@Override
		public void check( JavaMethod metodo, ConditionEvents events ) {
			boolean validaParametro = false;
			for ( JavaParameter parametro : metodo.getParameters() ) {
				// Compruebo si el metodo tiene RequestBody (JSON)
				if ( parametro.isAnnotatedWith( RequestBody.class ) ) {
					validaParametro = parametro.isAnnotatedWith( Valid.class ) || parametro.isAnnotatedWith( Validated.class ) || metodo.getOwner().isAnnotatedWith( Validated.class );
					if ( !validaParametro ) {
						String message = String.format( "El método %s tiene un @RequestBody sin validación", metodo.getFullName() );
						// Aniado un evento de violacion
						events.add( SimpleConditionEvent.violated( metodo, message ) );
					}
				}
			}
		}
	};


	@ArchTest
	static final ArchRule ninguna_interfaz_acaba_en_impl = noClasses().that().areInterfaces().should()
	.haveSimpleNameEndingWith( "Impl" ).because( "No deben de acabar en Impl" );

	@ArchTest
	static final ArchRule codigo_respeta_arquitectura_hexagonal = layeredArchitecture()// Define una estructura por capas
	.consideringAllDependencies() // Cojo todas las dependencias del proyecto
	.layer( "Domain" ).definedBy( "..domain.." ) // Defino la capa de dominio por todas las clases dentro de ..domain..
	.layer( "Application" ).definedBy( "..application.." ) // Defino la capa de aplicacion por todas las clases dentro de
	.layer( "Adapters" ).definedBy( "..adaptors.." )// Defino la capa de adaptadores por todas las clases dentro de ..adaptors..
	.whereLayer( "Domain" ).mayOnlyBeAccessedByLayers( "Application", "Adapters" ) // Indico que "imports" de la
	// capa de dominio pueden estar en Application y Adapters
	.whereLayer( "Application" ).mayOnlyBeAccessedByLayers( "Adapters" )// Indico que "imports" de la capa de
	// adaptadores pueden estar en Application (pero no en dominio)
	.whereLayer( "Adapters" ).mayNotBeAccessedByAnyLayer(); // Ninguna capa debe tener import de adapters

	@ArchTest
	static final ArchRule api_rest_debe_validar_datos_entrada = methods().that().areDeclaredInClassesThat()
	.areAnnotatedWith( RestController.class ).and().arePublic().should( METODO_REST_VALIDA_PARAMETROS );

	@ArchTest
	static final ArchRule interfaz_acaba_en_impl1 = classes().that().areNotInterfaces().and().areNotEnums().and().implement( JavaClass.Predicates.INTERFACES ).
	should().haveSimpleNameEndingWith( "Impl" );

	@ArchTest
	static final ArchRule dto_acaba_en_dto = classes().that().areNotInterfaces().and().areNotEnums().and().resideInAnyPackage( "..dto.." ).should().haveSimpleNameEndingWith( "DTO" );

	@ArchTest
	static final ArchRule restControllers_en_adaptors = classes().that().areAnnotatedWith( RestController.class ).should().resideInAnyPackage( "..adaptors.." );






}
