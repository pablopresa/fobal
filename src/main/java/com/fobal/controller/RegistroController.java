package com.fobal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fobal.model.LoginRegistroDTO;
import com.fobal.model.UsuarioDTO;
import com.fobal.repository.entity.UsuarioEntity;
import com.fobal.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/registro")
public class RegistroController {

	private static final int USUARIO_JUGADOR = 1;
	
	private UsuarioService usuarioService;
	
	@Autowired
	public RegistroController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("")
	public ResponseEntity<UsuarioDTO> registrarse(@RequestBody LoginRegistroDTO params) {
		UsuarioDTO resp = new UsuarioDTO();
		if(!params.validarRegistro()) {
			resp.setMensaje("Los campos no pueden estar vacíos.");
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST); 
		}
		if(usuarioService.obtenerPorNickname(params.getNickname())!=null){
			resp.setMensaje("Ya existe un usuario con ese nickname.");
			return new ResponseEntity<>(resp, HttpStatus.CONFLICT); 
		}
		if(usuarioService.obtenerPorNombreUsuario(params.getNombre())!=null) {
			resp.setMensaje("Ya existe un usuario con ese nombre.");
			return new ResponseEntity<>(resp, HttpStatus.CONFLICT); 
		}
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setPassword(usuarioService.hashPass(params.getPassword()));
		usuario.setTipoUsuario(usuarioService.obtenerTipoUsuarioPorId(USUARIO_JUGADOR));
		usuario.setNickname(params.getNickname());
		usuario.setNombre(params.getNombre());
		usuario.setAlias(params.getAlias());
		usuario.setFechaCreacion(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date()));
		UsuarioEntity usrBdd = usuarioService.guardar(usuario);
		if(usrBdd==null) {
			resp.setUsuario(usrBdd);
			resp.setMensaje("Error al guardar el usuario.");
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.setMensaje("Usuario creado con éxito.");
		resp.setUsuario(usrBdd);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
}