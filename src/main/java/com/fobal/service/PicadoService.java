package com.fobal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fobal.model.UsuarioPar;
import com.fobal.repository.CanchaRepository;
import com.fobal.repository.PicadoRepository;
import com.fobal.repository.UsuarioPicadoRepository;
import com.fobal.repository.UsuarioRepository;
import com.fobal.repository.entity.CanchaEntity;
import com.fobal.repository.entity.PicadoEntity;
import com.fobal.repository.entity.UsuarioEntity;
import com.fobal.repository.entity.UsuarioPicadoEntity;
import com.fobal.util.Util;

@Service
public class PicadoService {

	Logger logger = Logger.getLogger(PicadoService.class);

	PicadoRepository picadoRepository;
	CanchaRepository canchaRepository;
	UsuarioRepository usuarioRepository;
	UsuarioPicadoRepository usuarioPicadoRepository;

	@Autowired
	public PicadoService(PicadoRepository picadoRepository, CanchaRepository canchaRepository,
			UsuarioRepository usuarioRepository, UsuarioPicadoRepository usuarioPicadoRepository) {

		this.picadoRepository = picadoRepository;
		this.canchaRepository = canchaRepository;
		this.usuarioRepository = usuarioRepository;
		this.usuarioPicadoRepository = usuarioPicadoRepository;
	}

	public PicadoEntity guardar(PicadoEntity picado) {

		return picadoRepository.save(picado);
	}

	public UsuarioPicadoEntity guardar(UsuarioPicadoEntity picado) {

		return usuarioPicadoRepository.save(picado);
	}

	public PicadoEntity obtenerPicado(Integer idPicado) {

		return picadoRepository.findById(idPicado).get();
	}

	public List<PicadoEntity> obtenerPicados() {

		return picadoRepository.findAll();
	}

	public PicadoEntity agregarJugadores(PicadoEntity picado, List<UsuarioEntity> jugadores, Integer max) {

		List<UsuarioEntity> jugadoresPicado = picado.getUsuarios();

		for (UsuarioEntity jugador : jugadores) {

			if (!jugadoresPicado.contains(jugador)) {

				picado.agregarUsuario(jugador);
			}
			if (picado.getUsuarios().size() == max) {
				generarPicado(picado);
				break;
			}
		}
		return picadoRepository.save(picado);
	}

	private void generarPicado(PicadoEntity picado) {

//		Inicializo variables
		List<UsuarioEntity> jugadoresBdd = new ArrayList<>();
		Double sumaTotal = 0.0;
		Map<Double, List<UsuarioEntity>> mapaResultados = new HashMap<>();
		List<Double> resultados = new ArrayList<>();

//		Recorro la lista de jugadores del picado
		for (UsuarioEntity jugador : picado.getUsuarios()) {

//			Guardo valoración general de cada uno
			jugador.obtenerPromedioHabilidades(picado);

//			Guardo el jugador con su valoración actualizada y obtengo
//			el jugador guardado en la base de datos
			UsuarioEntity jugadorBdd = usuarioRepository.save(jugador);

//			Si el jugador existe
			if (jugadorBdd != null) {

//				Lo agrego a la lista total de jugadores
				jugadoresBdd.add(jugadorBdd);
			}
		}

		Integer largoJugadores = jugadoresBdd.size();
		int cuentaCombinaciones = 0;
//		Recorro todos los jugadores
		for (int i = 0; i < largoJugadores; i++) {
			for (int j = i + 1; j < largoJugadores; j++) {
				for (int k = j + 1; k < largoJugadores; k++) {
					for (int l = k + 1; l < largoJugadores; l++) {
						for (int m = l + 1; m < largoJugadores; m++) {
							cuentaCombinaciones++;
//							Y obtengo todas las sumas posibles de las valoraciones
//							de cinco jugadores
							List<UsuarioEntity> combinacion = new ArrayList<>();
							combinacion.add(jugadoresBdd.get(i));
							combinacion.add(jugadoresBdd.get(j));
							combinacion.add(jugadoresBdd.get(k));
							combinacion.add(jugadoresBdd.get(l));
							combinacion.add(jugadoresBdd.get(m));

//							Obtengo la valoración del equipo
							double suma = obtenerValoracionEquipo(combinacion, picado.getCancha());

//							Agrego la valoración del equipo a la suma
//							total de valoraciones
							sumaTotal += suma;

//							Agrego la suma a una lista para facilitar la búsqueda 
//							de la valoración más cercana a la ideal
							resultados.add(suma);
//							Y agrego la suma a un mapa donde la clave es la suma de las valoraciones
//							y el valor es la lista de jugadores que suman esa valoración
							mapaResultados.put(suma, combinacion);
						}
					}
				}
			}
		}

//		En un caso ideal, la suma de las valoraciones de los dos 
//		equipos debería sumar la mitad de la valoración total,
//		y así los dos equipos tendrían la misma valoración
		Double sumaIdeal = Util.truncarDouble(sumaTotal / 2, 1);

//		Busco la combinación de cinco jugadores que se acerque más a la suma ideal
		Double mejorSuma = encontrarNumeroMasCercano(resultados, sumaIdeal);

//		Obtengo los jugadores que conforman esa suma y los agrego a un equipo
		List<UsuarioEntity> equipo1 = mapaResultados.get(mejorSuma);

//		Armo el segundo equipo con los jugadores que no quedaron en el primero
		List<UsuarioEntity> equipo2 = jugadoresBdd.stream().filter(jugador -> !equipo1.contains(jugador))
				.collect(Collectors.toList());

//		El picado level es el promedio de todas las valoraciones
		picado.setPicadoLevel(Util.truncarDouble(sumaTotal / cuentaCombinaciones, 1));

//		Agrego los equipos al picado
		picado.setEquipos(equipo1, equipo2);
//		Lo marco como completo
		picado.setCompleto(true);

//		Y guardo el picado con los equipos armados
		guardar(picado);

//		Tapelao
	}

	private Double encontrarNumeroMasCercano(List<Double> listaDoubles, Double numeroObjetivo) {

		double numeroMasCercano = listaDoubles.get(0);
		double diferenciaMinima = Math.abs(numeroObjetivo - numeroMasCercano);

		for (double numero : listaDoubles) {
			double diferenciaActual = Math.abs(numeroObjetivo - numero);
			if (diferenciaActual < diferenciaMinima) {
				numeroMasCercano = numero;
				diferenciaMinima = diferenciaActual;
			}
		}

		return numeroMasCercano;
	}

	private Double obtenerValoracionEquipo(List<UsuarioEntity> jugadores, CanchaEntity cancha) {

		Map<UsuarioPar, Integer> diferencias = new HashMap<>();
		for (int i = 0; i < jugadores.size(); i++) {
			UsuarioEntity jugador1 = jugadores.get(i);
			for (int j = 0; j < jugadores.size(); j++) {
				UsuarioEntity jugador2 = jugadores.get(j);

				UsuarioPar par = new UsuarioPar(jugador1, jugador2);

				if (!jugador1.equals(jugador2) && !diferencias.containsKey(par)) {

					List<UsuarioPicadoEntity> picadosCompartidos = jugador1.getHistorial().stream()
							.filter(x -> x.getPicado().getUsuarios().contains(jugador2)).toList();

					Integer ganados = Integer
							.parseInt(picadosCompartidos.stream().filter(x -> x.isGanado()).count() + "");

					Integer perdidos = picadosCompartidos.size() - ganados;

					Integer diferencia = ganados - perdidos;

					diferencias.put(par, diferencia);
				}
			}
		}
		return jugadores.stream().mapToDouble(UsuarioEntity::getValoracionGeneral).sum();
	}

	public PicadoEntity finalizarPicado(Integer idPicado) {

		PicadoEntity picadoBdd = obtenerPicado(idPicado);

		marcarGanadores(picadoBdd.getEquipo1(), picadoBdd);
		marcarPerdedores(picadoBdd.getEquipo2(), picadoBdd);

		picadoBdd.setFinalizado(true);
		return guardar(picadoBdd);
	}

	private void marcarGanadores(List<UsuarioEntity> jugadores, PicadoEntity picado) {
		
		marcarJugadores(jugadores, picado, true);
	}

	private void marcarPerdedores(List<UsuarioEntity> jugadores, PicadoEntity picado) {

		marcarJugadores(jugadores, picado, false);
	}
	
	private void marcarJugadores(List<UsuarioEntity> jugadores, PicadoEntity picado, boolean ganado) {
		UsuarioPicadoEntity usuarioPicado = new UsuarioPicadoEntity();
		usuarioPicado.setPicado(picado);

		for(UsuarioEntity jugador : jugadores) {
			usuarioPicado.setUsuario(jugador);
			usuarioPicado.setGanado(ganado);
			jugador.getHistorial().add(usuarioPicado);
			
			guardar(usuarioPicado);

			usuarioRepository.save(jugador);
		}
	}

}
