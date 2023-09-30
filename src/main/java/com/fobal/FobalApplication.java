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

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(FobalApplication.class, args);

		UsuarioService usuarioService = context.getBean(UsuarioService.class);
		CanchaService canchaService = context.getBean(CanchaService.class);
		PicadoService picadoService = context.getBean(PicadoService.class);
		HabilidadService habilidadService = context.getBean(HabilidadService.class);

		TipoUsuarioEntity tipoJugador = usuarioService.guardar(new TipoUsuarioEntity("Jugador"));
		TipoUsuarioEntity tipoAdmin = usuarioService.guardar(new TipoUsuarioEntity("Administrador"));

		HabilidadEntity definicion = habilidadService.guardar(new HabilidadEntity("Definicion", 10));
		HabilidadEntity magia = habilidadService.guardar(new HabilidadEntity("Magia", 10));
		HabilidadEntity tiro = habilidadService.guardar(new HabilidadEntity("Tiro", 10));
		HabilidadEntity defensa = habilidadService.guardar(new HabilidadEntity("Defensa", 10));
		HabilidadEntity habilitaJuego = habilidadService.guardar(new HabilidadEntity("Habilita Juego", 10));
		HabilidadEntity estadoFisico = habilidadService.guardar(new HabilidadEntity("Estado Fisico", 10));

		UsuarioEntity usuario1 = usuarioService.guardar(new UsuarioEntity("Juan Enrique Graña", "El Estaca", tipoJugador));
		UsuarioHabilidadEntity usuario1Habilidad1 = new UsuarioHabilidadEntity(usuario1, definicion, randomStat());
		UsuarioHabilidadEntity usuario1Habilidad2 = new UsuarioHabilidadEntity(usuario1, magia, randomStat());
		UsuarioHabilidadEntity usuario1Habilidad3 = new UsuarioHabilidadEntity(usuario1, tiro, randomStat());
		UsuarioHabilidadEntity usuario1Habilidad4 = new UsuarioHabilidadEntity(usuario1, defensa, randomStat());
		UsuarioHabilidadEntity usuario1Habilidad5 = new UsuarioHabilidadEntity(usuario1, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario1Habilidad6 = new UsuarioHabilidadEntity(usuario1, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario1 = Arrays.asList(usuario1Habilidad1, usuario1Habilidad2,
				usuario1Habilidad3, usuario1Habilidad4, usuario1Habilidad5, usuario1Habilidad6);

		UsuarioEntity usuario2 = usuarioService.guardar(new UsuarioEntity("Nicolas Hovagimian", "Giorgian", tipoJugador));
		UsuarioHabilidadEntity usuario2Habilidad1 = new UsuarioHabilidadEntity(usuario2, definicion, randomStat());
		UsuarioHabilidadEntity usuario2Habilidad2 = new UsuarioHabilidadEntity(usuario2, magia, randomStat());
		UsuarioHabilidadEntity usuario2Habilidad3 = new UsuarioHabilidadEntity(usuario2, tiro, randomStat());
		UsuarioHabilidadEntity usuario2Habilidad4 = new UsuarioHabilidadEntity(usuario2, defensa, randomStat());
		UsuarioHabilidadEntity usuario2Habilidad5 = new UsuarioHabilidadEntity(usuario2, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario2Habilidad6 = new UsuarioHabilidadEntity(usuario2, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario2 = Arrays.asList(usuario2Habilidad1, usuario2Habilidad2,
				usuario2Habilidad3, usuario2Habilidad4, usuario2Habilidad5, usuario2Habilidad6);

		UsuarioEntity usuario3 = usuarioService.guardar(new UsuarioEntity("Andres Baptista", "Batigol", tipoJugador));
		UsuarioHabilidadEntity usuario3Habilidad1 = new UsuarioHabilidadEntity(usuario3, definicion, randomStat());
		UsuarioHabilidadEntity usuario3Habilidad2 = new UsuarioHabilidadEntity(usuario3, magia, randomStat());
		UsuarioHabilidadEntity usuario3Habilidad3 = new UsuarioHabilidadEntity(usuario3, tiro, randomStat());
		UsuarioHabilidadEntity usuario3Habilidad4 = new UsuarioHabilidadEntity(usuario3, defensa, randomStat());
		UsuarioHabilidadEntity usuario3Habilidad5 = new UsuarioHabilidadEntity(usuario3, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario3Habilidad6 = new UsuarioHabilidadEntity(usuario3, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario3 = Arrays.asList(usuario3Habilidad1, usuario3Habilidad2,
				usuario3Habilidad3, usuario3Habilidad4, usuario3Habilidad5, usuario3Habilidad6);

		UsuarioEntity usuario4 = usuarioService.guardar(new UsuarioEntity("Nacho", "Charly", tipoJugador));
		UsuarioHabilidadEntity usuario4Habilidad1 = new UsuarioHabilidadEntity(usuario4, definicion, randomStat());
		UsuarioHabilidadEntity usuario4Habilidad2 = new UsuarioHabilidadEntity(usuario4, magia, randomStat());
		UsuarioHabilidadEntity usuario4Habilidad3 = new UsuarioHabilidadEntity(usuario4, tiro, randomStat());
		UsuarioHabilidadEntity usuario4Habilidad4 = new UsuarioHabilidadEntity(usuario4, defensa, randomStat());
		UsuarioHabilidadEntity usuario4Habilidad5 = new UsuarioHabilidadEntity(usuario4, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario4Habilidad6 = new UsuarioHabilidadEntity(usuario4, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario4 = Arrays.asList(usuario4Habilidad1, usuario4Habilidad2,
				usuario4Habilidad3, usuario4Habilidad4, usuario4Habilidad5, usuario4Habilidad6);

		UsuarioEntity usuario5 = usuarioService.guardar(new UsuarioEntity("Pablo K", "El Calculista", tipoJugador));
		UsuarioHabilidadEntity usuario5Habilidad1 = new UsuarioHabilidadEntity(usuario5, definicion, randomStat());
		UsuarioHabilidadEntity usuario5Habilidad2 = new UsuarioHabilidadEntity(usuario5, magia, randomStat());
		UsuarioHabilidadEntity usuario5Habilidad3 = new UsuarioHabilidadEntity(usuario5, tiro, randomStat());
		UsuarioHabilidadEntity usuario5Habilidad4 = new UsuarioHabilidadEntity(usuario5, defensa, randomStat());
		UsuarioHabilidadEntity usuario5Habilidad5 = new UsuarioHabilidadEntity(usuario5, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario5Habilidad6 = new UsuarioHabilidadEntity(usuario5, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario5 = Arrays.asList(usuario5Habilidad1, usuario5Habilidad2,
				usuario5Habilidad3, usuario5Habilidad4, usuario5Habilidad5, usuario5Habilidad6);

		UsuarioEntity usuario6 = usuarioService.guardar(new UsuarioEntity("Matias Acevedo", "Colo", tipoJugador));
		UsuarioHabilidadEntity usuario6Habilidad1 = new UsuarioHabilidadEntity(usuario6, definicion, randomStat());
		UsuarioHabilidadEntity usuario6Habilidad2 = new UsuarioHabilidadEntity(usuario6, magia, randomStat());
		UsuarioHabilidadEntity usuario6Habilidad3 = new UsuarioHabilidadEntity(usuario6, tiro, randomStat());
		UsuarioHabilidadEntity usuario6Habilidad4 = new UsuarioHabilidadEntity(usuario6, defensa, randomStat());
		UsuarioHabilidadEntity usuario6Habilidad5 = new UsuarioHabilidadEntity(usuario6, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario6Habilidad6 = new UsuarioHabilidadEntity(usuario6, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario6 = Arrays.asList(usuario6Habilidad1, usuario6Habilidad2,
				usuario6Habilidad3, usuario6Habilidad4, usuario6Habilidad5, usuario6Habilidad6);

		UsuarioEntity usuario7 = usuarioService.guardar(new UsuarioEntity("Mauricio Alves", "El Cana", tipoJugador));
		UsuarioHabilidadEntity usuario7Habilidad1 = new UsuarioHabilidadEntity(usuario7, definicion, randomStat());
		UsuarioHabilidadEntity usuario7Habilidad2 = new UsuarioHabilidadEntity(usuario7, magia, randomStat());
		UsuarioHabilidadEntity usuario7Habilidad3 = new UsuarioHabilidadEntity(usuario7, tiro, randomStat());
		UsuarioHabilidadEntity usuario7Habilidad4 = new UsuarioHabilidadEntity(usuario7, defensa, randomStat());
		UsuarioHabilidadEntity usuario7Habilidad5 = new UsuarioHabilidadEntity(usuario7, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario7Habilidad6 = new UsuarioHabilidadEntity(usuario7, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario7 = Arrays.asList(usuario7Habilidad1, usuario7Habilidad2,
				usuario7Habilidad3, usuario7Habilidad4, usuario7Habilidad5, usuario7Habilidad6);

		UsuarioEntity usuario8 = usuarioService.guardar(new UsuarioEntity("Victor Vallejo", "Vallejo", tipoJugador));
		UsuarioHabilidadEntity usuario8Habilidad1 = new UsuarioHabilidadEntity(usuario8, definicion, randomStat());
		UsuarioHabilidadEntity usuario8Habilidad2 = new UsuarioHabilidadEntity(usuario8, magia, randomStat());
		UsuarioHabilidadEntity usuario8Habilidad3 = new UsuarioHabilidadEntity(usuario8, tiro, randomStat());
		UsuarioHabilidadEntity usuario8Habilidad4 = new UsuarioHabilidadEntity(usuario8, defensa, randomStat());
		UsuarioHabilidadEntity usuario8Habilidad5 = new UsuarioHabilidadEntity(usuario8, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario8Habilidad6 = new UsuarioHabilidadEntity(usuario8, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario8 = Arrays.asList(usuario8Habilidad1, usuario8Habilidad2,
				usuario8Habilidad3, usuario8Habilidad4, usuario8Habilidad5, usuario8Habilidad6);

		UsuarioEntity usuario9 = usuarioService.guardar(new UsuarioEntity("Stefano Sandonato", "Stafeno", tipoJugador));
		UsuarioHabilidadEntity usuario9Habilidad1 = new UsuarioHabilidadEntity(usuario9, definicion, randomStat());
		UsuarioHabilidadEntity usuario9Habilidad2 = new UsuarioHabilidadEntity(usuario9, magia, randomStat());
		UsuarioHabilidadEntity usuario9Habilidad3 = new UsuarioHabilidadEntity(usuario9, tiro, randomStat());
		UsuarioHabilidadEntity usuario9Habilidad4 = new UsuarioHabilidadEntity(usuario9, defensa, randomStat());
		UsuarioHabilidadEntity usuario9Habilidad5 = new UsuarioHabilidadEntity(usuario9, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario9Habilidad6 = new UsuarioHabilidadEntity(usuario9, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario9 = Arrays.asList(usuario9Habilidad1, usuario9Habilidad2,
				usuario9Habilidad3, usuario9Habilidad4, usuario9Habilidad5, usuario9Habilidad6);

		UsuarioEntity usuario10 = usuarioService.guardar(new UsuarioEntity("Claudio", "Claudio", tipoJugador));
		UsuarioHabilidadEntity usuario10Habilidad1 = new UsuarioHabilidadEntity(usuario10, definicion, randomStat());
		UsuarioHabilidadEntity usuario10Habilidad2 = new UsuarioHabilidadEntity(usuario10, magia, randomStat());
		UsuarioHabilidadEntity usuario10Habilidad3 = new UsuarioHabilidadEntity(usuario10, tiro, randomStat());
		UsuarioHabilidadEntity usuario10Habilidad4 = new UsuarioHabilidadEntity(usuario10, defensa, randomStat());
		UsuarioHabilidadEntity usuario10Habilidad5 = new UsuarioHabilidadEntity(usuario10, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario10Habilidad6 = new UsuarioHabilidadEntity(usuario10, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario10 = Arrays.asList(usuario10Habilidad1, usuario10Habilidad2,
				usuario10Habilidad3, usuario10Habilidad4, usuario10Habilidad5, usuario10Habilidad6);

		UsuarioEntity usuario11 = usuarioService.guardar(new UsuarioEntity("Nacho", "Nacho", tipoJugador));
		UsuarioHabilidadEntity usuario11Habilidad1 = new UsuarioHabilidadEntity(usuario11, definicion, randomStat());
		UsuarioHabilidadEntity usuario11Habilidad2 = new UsuarioHabilidadEntity(usuario11, magia, randomStat());
		UsuarioHabilidadEntity usuario11Habilidad3 = new UsuarioHabilidadEntity(usuario11, tiro, randomStat());
		UsuarioHabilidadEntity usuario11Habilidad4 = new UsuarioHabilidadEntity(usuario11, defensa, randomStat());
		UsuarioHabilidadEntity usuario11Habilidad5 = new UsuarioHabilidadEntity(usuario11, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario11Habilidad6 = new UsuarioHabilidadEntity(usuario11, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario11 = Arrays.asList(usuario11Habilidad1, usuario11Habilidad2,
				usuario11Habilidad3, usuario11Habilidad4, usuario11Habilidad5, usuario11Habilidad6);

		UsuarioEntity usuario12 = usuarioService.guardar(new UsuarioEntity("Martín", "Tatosky", tipoJugador));
		UsuarioHabilidadEntity usuario12Habilidad1 = new UsuarioHabilidadEntity(usuario12, definicion, randomStat());
		UsuarioHabilidadEntity usuario12Habilidad2 = new UsuarioHabilidadEntity(usuario12, magia, randomStat());
		UsuarioHabilidadEntity usuario12Habilidad3 = new UsuarioHabilidadEntity(usuario12, tiro, randomStat());
		UsuarioHabilidadEntity usuario12Habilidad4 = new UsuarioHabilidadEntity(usuario12, defensa, randomStat());
		UsuarioHabilidadEntity usuario12Habilidad5 = new UsuarioHabilidadEntity(usuario12, habilitaJuego, randomStat());
		UsuarioHabilidadEntity usuario12Habilidad6 = new UsuarioHabilidadEntity(usuario12, estadoFisico, randomStat());
		List<UsuarioHabilidadEntity> habilidadesUsuario12 = Arrays.asList(usuario12Habilidad1, usuario12Habilidad2,
				usuario12Habilidad3, usuario12Habilidad4, usuario12Habilidad5, usuario12Habilidad6);

//		UsuarioEntity yo = usuarioService.guardar(new UsuarioEntity("Pablo Presa", "Pablin", tipoAdmin));
//		UsuarioHabilidadEntity miHabilidad1 = new UsuarioHabilidadEntity(yo, definicion, randomStat());
//		UsuarioHabilidadEntity miHabilidad2 = new UsuarioHabilidadEntity(yo, magia, randomStat());
//		UsuarioHabilidadEntity miHabilidad3 = new UsuarioHabilidadEntity(yo, tiro, randomStat());
//		UsuarioHabilidadEntity miHabilidad4 = new UsuarioHabilidadEntity(yo, defensa, randomStat());
//		UsuarioHabilidadEntity miHabilidad5 = new UsuarioHabilidadEntity(yo, habilitaJuego, randomStat());
//		UsuarioHabilidadEntity miHabilidad6 = new UsuarioHabilidadEntity(yo, estadoFisico, randomStat());
//		List<UsuarioHabilidadEntity> misHabilidades = Arrays.asList(miHabilidad1, miHabilidad2, miHabilidad3,
//				miHabilidad4, miHabilidad5, miHabilidad6);

		habilidadService.guardar(habilidadesUsuario1);
		habilidadService.guardar(habilidadesUsuario2);
		habilidadService.guardar(habilidadesUsuario3);
		habilidadService.guardar(habilidadesUsuario4);
		habilidadService.guardar(habilidadesUsuario5);
		habilidadService.guardar(habilidadesUsuario6);
		habilidadService.guardar(habilidadesUsuario7);
		habilidadService.guardar(habilidadesUsuario8);
		habilidadService.guardar(habilidadesUsuario9);
		habilidadService.guardar(habilidadesUsuario10);
		habilidadService.guardar(habilidadesUsuario11);
		habilidadService.guardar(habilidadesUsuario12);
//		habilidadService.guardar(misHabilidades);

		List<UsuarioEntity> jugadores = Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6,
				usuario7, usuario8, usuario9);

		CanchaEntity cancha1 = canchaService.guardar(new CanchaEntity("Central 5", Medida.MEDIANA));

		PicadoEntity picado1 = picadoService.guardar(new PicadoEntity(cancha1, new Date()));

		picadoService.agregarJugadores(picado1, jugadores, 10);

		log.info(getMensajeInicio());
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

}
