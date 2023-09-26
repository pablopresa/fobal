package com.fobal.model;
public class LoginRegistroDTO {

	private Integer id;
	private String alias;
	private String nickname;
	private String nombre;
	private String password;
	private Integer tipo;
	
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String email) {
		this.nickname = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public boolean validarLogin() {
		return ((nickname!=null && password!=null && !nickname.isEmpty() && !password.isEmpty() ) || (nombre!=null && password!=null && !nombre.isEmpty() && !password.isEmpty()) );
	}
	public boolean validarRegistro() {
		return (nickname!=null && nombre!=null && password!=null && !nickname.isEmpty() && !nombre.isEmpty() && !password.isEmpty());
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}