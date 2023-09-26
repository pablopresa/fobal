package com.fobal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fobal.repository.entity.UsuarioEntity;
import com.fobal.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuario")
public class UsuarioController {

	Logger logger = Logger.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioEntity> obtenerUsuario(@PathVariable Integer idUsuario) {

		UsuarioEntity usuario = usuarioService.obtenerPorId(idUsuario);

		return (usuario != null) ? new ResponseEntity<>(usuario, HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@GetMapping("")
	public ResponseEntity<List<UsuarioEntity>> obtenerUsuarios() {

		List<UsuarioEntity> usuarios = usuarioService.obtenerTodos();

		return (usuarios != null && !usuarios.isEmpty()) ? new ResponseEntity<>(usuarios, HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

	@PostMapping("")
	public ResponseEntity<UsuarioEntity> crearUsuario(@RequestBody UsuarioEntity usuario) {

		logger.info(usuario.toString());

		UsuarioEntity usuarioNuevo = usuarioService.guardar(usuario);

		return (usuarioNuevo != null) ? new ResponseEntity<>(usuarioNuevo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
}
