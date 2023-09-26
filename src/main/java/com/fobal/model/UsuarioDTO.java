package com.fobal.model;

import com.fobal.repository.entity.UsuarioEntity;

public class UsuarioDTO {

	UsuarioEntity usuario;
	String mensaje;
	
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
