package com.fobal.controller;

import org.apache.log4j.Logger;
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
@RequestMapping("/login")
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);
	
	private UsuarioService usuarioService;
	
	@Autowired
	public LoginController(UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;
	}
	
	@PostMapping(value="")
	public ResponseEntity<UsuarioDTO> loguearse(@RequestBody LoginRegistroDTO usuario) {
		
		UsuarioDTO resp = new UsuarioDTO();
		if(!usuario.validarLogin()) {
			resp.setMensaje("Los campos no pueden estar vacíos.");
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST); 
		}
		byte[] pass = usuarioService.hashPass(usuario.getPassword());
		UsuarioEntity usuarioBdd = usuarioService.obtenerPorEmailPassword(usuario.getNickname(), pass);
		if(usuarioBdd==null) {
			usuarioBdd = usuarioService.obtenerPorNombrePassword(usuario.getNickname(), pass);
		}
		if(usuarioBdd==null) {
			resp.setMensaje("Nombre de usuario o password incorrecto.");
			return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
		}
		else {
			resp.setUsuario(usuarioBdd);
			resp.setMensaje("Login correcto.");
			logger.info(String.format("Se loguea el usuario. ID: %s, NOMBRE: %s ", usuarioBdd.getId(), usuarioBdd.getNombre()));
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
	}
}
