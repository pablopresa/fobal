package com.fobal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fobal.model.PicadoDTO;
import com.fobal.repository.entity.CanchaEntity;
import com.fobal.repository.entity.PicadoEntity;
import com.fobal.repository.entity.UsuarioEntity;
import com.fobal.service.CanchaService;
import com.fobal.service.PicadoService;
import com.fobal.service.HabilidadService;
import com.fobal.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/picado")
public class PicadoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Integer MAXIMO_JUGADORES_POR_PICADO = 10;

	PicadoService picadoService;
	UsuarioService usuarioService;
	CanchaService canchaService;
	HabilidadService statService;

	@Autowired
	public PicadoController(PicadoService picadoService, UsuarioService usuarioService, CanchaService canchaService,
			HabilidadService statService) {

		this.picadoService = picadoService;
		this.usuarioService = usuarioService;
		this.canchaService = canchaService;
		this.statService = statService;
	}

	@PostMapping("")
	public ResponseEntity<PicadoEntity> crearPicado(@RequestBody PicadoDTO picado) {

		CanchaEntity cancha = canchaService.obtenerCancha(picado.getIdCancha());
		PicadoEntity picadoEntity = new PicadoEntity(cancha, picado.getFecha());
		picadoService.guardar(picadoEntity);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{idPicado}")
	public ResponseEntity<PicadoEntity> obtenerPicado(@PathVariable Integer idPicado) {

		PicadoEntity picado = picadoService.obtenerPicado(idPicado);

		return (picado != null) ? new ResponseEntity<>(picado, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("")
	public ResponseEntity<List<PicadoEntity>> obtenerPicados() {

		List<PicadoEntity> picados = picadoService.obtenerPicados();

		return (picados != null && !picados.isEmpty()) ? new ResponseEntity<>(picados, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PatchMapping("/{idPicado}")
	public ResponseEntity<PicadoEntity> agregarJugadores(@RequestBody List<UsuarioEntity> usuarios,
			@PathVariable Integer idPicado) {

		PicadoEntity picado = picadoService.obtenerPicado(idPicado);
		if (picado == null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (picado.getUsuarios() != null && picado.getUsuarios().size() >= MAXIMO_JUGADORES_POR_PICADO) {

			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		List<UsuarioEntity> usuariosBdd = new ArrayList<>();
		for (UsuarioEntity usuario : usuarios) {

			UsuarioEntity jugador = usuarioService.obtenerPorId(usuario.getId());
			if (jugador != null) {
				usuariosBdd.add(jugador);
			}
		}
		if (usuariosBdd.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		PicadoEntity picadoGuardado = picadoService.agregarJugadores(picado, usuariosBdd, MAXIMO_JUGADORES_POR_PICADO);

		return (picadoGuardado != null) ? new ResponseEntity<>(picadoGuardado, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
