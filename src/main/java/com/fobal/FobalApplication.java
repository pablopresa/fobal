package com.fobal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.fobal.model.Medida;
import com.fobal.repository.entity.CanchaEntity;
import com.fobal.repository.entity.HabilidadEntity;
import com.fobal.repository.entity.PicadoEntity;
import com.fobal.repository.entity.TipoUsuarioEntity;
import com.fobal.repository.entity.UsuarioEntity;
import com.fobal.repository.entity.UsuarioHabilidadEntity;
import com.fobal.service.CanchaService;
import com.fobal.service.HabilidadService;
import com.fobal.service.PicadoService;
import com.fobal.service.UsuarioService;

@SpringBootApplication
public class FobalApplication {

	private static final Logger log = Logger.getLogger(FobalApplication.class);
	private static UsuarioService usuarioService;
	private static CanchaService canchaService;
	private static PicadoService picadoService;
	private static HabilidadService habilidadService;
	private static List<HabilidadEntity> habilidades;

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(FobalApplication.class, args);

		usuarioService = context.getBean(UsuarioService.class);
		canchaService = context.getBean(CanchaService.class);
		picadoService = context.getBean(PicadoService.class);
		habilidadService = context.getBean(HabilidadService.class);

		TipoUsuarioEntity tipoJugador = usuarioService.guardar(new TipoUsuarioEntity("Jugador"));
		TipoUsuarioEntity tipoAdmin = usuarioService.guardar(new TipoUsuarioEntity("Administrador"));

		HabilidadEntity definicion = habilidadService.guardar(new HabilidadEntity("Definición", 10));
		HabilidadEntity magia = habilidadService.guardar(new HabilidadEntity("Magia", 10));
		HabilidadEntity tiro = habilidadService.guardar(new HabilidadEntity("Tiro", 10));
		HabilidadEntity defensa = habilidadService.guardar(new HabilidadEntity("Defensa", 10));
		HabilidadEntity habilitaJuego = habilidadService.guardar(new HabilidadEntity("Habilita Juego", 10));
		HabilidadEntity estadoFisico = habilidadService.guardar(new HabilidadEntity("Estado Físico", 10));

		habilidades = new ArrayList<>();
		habilidades.add(definicion);
		habilidades.add(magia);
		habilidades.add(tiro);
		habilidades.add(defensa);
		habilidades.add(habilitaJuego);
		habilidades.add(estadoFisico);

		crearJugador("Leonardo Rijo", "Leo", tipoJugador, Arrays.asList(8.8, 8.3, 8.6, 8.1, 8.9, 9.0, 6.8));
		crearJugador("Nicolas Caldarelli", "Calda", tipoJugador, Arrays.asList(8.5, 8.1, 8.6, 8.3, 8.9, 9.0, 7.0));
		crearJugador("Nicolas Hovagimian", "Giorgian", tipoJugador, Arrays.asList(7.8, 8.3, 7.5, 8.1, 7.2, 9.0, 7.0));
		crearJugador("Juan Graña", "Qiqe", tipoJugador, Arrays.asList(8.2, 8.2, 7.8, 7.9, 8.6, 8.0, 8.0));
		crearJugador("Pablo Kalemkerian", "Pablo K", tipoJugador, Arrays.asList(8.0, 8.1, 8.3, 7.7, 8.5, 8.6, 7.0));
		crearJugador("Pablo Presa", "Pablin", tipoJugador, Arrays.asList(7.3, 7.2, 7.5, 8.5, 8.1, 9.2, 8.5));
		crearJugador("Claudio Rodriguez", "Claudio", tipoJugador, Arrays.asList(7.5, 7.1, 7.2, 7.8, 7.7, 7.7, 7.0));
		crearJugador("Victor Vallejo", "Vallejo", tipoJugador, Arrays.asList(8.0, 7.8, 8.1, 7.3, 8.4, 6.8, 6.8));
		crearJugador("Andres Baptista", "Batigol", tipoJugador, Arrays.asList(7.3, 6.8, 7.1, 7.3, 7.3, 7.8, 6.5));
		crearJugador("Nacho Charly", "Charly", tipoJugador, Arrays.asList(7.8, 7.6, 7.8, 5.0, 7.8, 6.5, 5.5));
		crearJugador("Juan Brito", "Brito", tipoJugador, Arrays.asList(7.0, 7.0, 7.0, 7.0, 7.0, 7.0, 7.0));
		crearJugador("nombre", "Cana", tipoJugador, Arrays.asList(7.0, 6.8, 6.5, 6.8, 7.5, 6.5, 6.8));
		crearJugador("Stefano", "Stefano", tipoJugador, Arrays.asList(6.5, 6.5, 6.5, 7.5, 7.0, 7.5, 6.5));
		crearJugador("Santiago Ferreira", "Mago", tipoJugador, Arrays.asList(7.8, 8.3, 6.0, 5.0, 6.8, 4.5, 5.5));
		crearJugador("Nacho", "Gordo Nacho", tipoJugador, Arrays.asList(6.7, 5.3, 7.7, 4.0, 7.5, 2.8, 7.0));
		crearJugador("Martin Correa", "Tatosky", tipoJugador, Arrays.asList(3.7, 4.5, 4.0, 5.5, 4.3, 7.5, 5.5));
		crearJugador("Lui", "Lui", tipoJugador, Arrays.asList(2.0, 3.0, 1.0, 4.0, 3.0, 6.0, 5.0));
		crearJugador("Dape", "Dape", tipoJugador, Arrays.asList(7.0, 7.0, 7.0, 7.0, 7.0, 7.5, 7.0));
		crearJugador("Ivan", "Ivo", tipoJugador, Arrays.asList(7.6, 7.0, 7.4, 7.0, 6.8, 7.9, 5.5));
		crearJugador("Bautista Muñoz", "Bauti", tipoJugador, Arrays.asList(8.8, 8.0, 8.3, 7.9, 8.3, 8.3, 6.5));
		crearJugador("Martin Acevedo", "Colo", tipoJugador, Arrays.asList(8.5, 8.0, 8.6, 7.6, 8.1, 7.0, 7.0));
		crearJugador("Tuto", "Tuto", tipoJugador, Arrays.asList(8.4, 8.6, 8.1, 7.3, 7.7, 6.5, 6.3));
		crearJugador("Martin Nobile", "Nobile", tipoJugador, Arrays.asList(8.4, 8.0, 8.3, 7.9, 8.2, 8.0, 6.5));
		crearJugador("Roballo", "Roballo", tipoJugador, Arrays.asList(7.0, 7.0, 7.0, 7.8, 7.0, 7.8, 7.0));
		crearJugador("Roballo jr", "Roballo jr", tipoJugador, Arrays.asList(7.0, 7.0, 7.0, 7.8, 7.0, 7.8, 7.0));

		CanchaEntity cancha1 = canchaService.guardar(new CanchaEntity("Central 5", Medida.MEDIANA));

		PicadoEntity picado1 = picadoService.guardar(new PicadoEntity(cancha1, new Date()));

//		picadoService.agregarJugadores(picado1, jugadores, 10);
	}

	private static String getMensajeInicio() {
		List<String> mensajes = new ArrayList<>();
		mensajes.add("Arrancandonga...");
		mensajes.add("Salimooooo...");
		mensajes.add("Vapaiii...");
		mensajes.add("Tamo en vivo...");
		mensajes.add("Tamo activo...");
		mensajes.add("Metele que son pasteles...");
		mensajes.add("Vamo los pibes...");
		mensajes.add("Ritmo y sustancia para toda la vagancia...");
		mensajes.add("Arranquetti...");
		mensajes.add("Se viene el estallido...");
		mensajes.add("Arranca o no arranca? SIEMPRE ARRANCA...");
		mensajes.add("Larrrrrrgaron...");
		mensajes.add("Se mueve se mueve, se juega se juega...");
		mensajes.add("Futbol señores, y también señoras...");
		mensajes.add("Dale que arranca eeeeeeh...");
		mensajes.add("Correte que vengo...");
		mensajes.add("Listos o no, alla vamos...");
		mensajes.add("Hable ahora o calle para siempre...");
		mensajes.add("Sabe que sabe...");
		mensajes.add("Pero... sabe que sabe?");
		mensajes.add("Bien de bien...");
		mensajes.add("Cantalo cantalo cantalo cantalo...");
		mensajes.add(
				"at [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `java.lang.Integer` from Object value (token `JsonToken.START_OBJECT`); nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize value of type `java.lang.Integer` from Object value (token `JsonToken.START_OBJECT`). \n Te la creiste jajaja");

		return mensajes.get(randomInt(0, mensajes.size()));
	}

	private static int randomInt(int minimo, int maximo) {
		return (new Random()).nextInt(maximo - minimo) + minimo;
	}

	private static double randomStat() {

		int ceroCien = randomInt(20, 100);
		return ceroCien / 10;
	}

	private static void crearHabilidades(UsuarioEntity usuario, List<Double> valores) {

		List<UsuarioHabilidadEntity> habilidadesUsuario = new ArrayList<>();

		for (int i = 0; i < habilidades.size(); i++) {

			HabilidadEntity habilidad = habilidades.get(i);
			Double valor = valores.get(i);
			habilidadesUsuario.add(new UsuarioHabilidadEntity(usuario, habilidad, valor));
		}

		habilidadService.guardar(habilidadesUsuario);
	}

	private static void crearJugador(String nombre, String apodo, TipoUsuarioEntity tipo, List<Double> habilidades) {

		UsuarioEntity usuario = usuarioService.guardar(new UsuarioEntity(nombre, apodo, tipo));
		crearHabilidades(usuario, habilidades);
	}

}
