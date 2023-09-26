package com.fobal.repository.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "picado")
public class PicadoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "picado_level")
	private Double picadoLevel;
	@Column(name = "completo")
	private Boolean completo;
	@Column(name = "finalizado")
	private Boolean finalizado;
	@ManyToOne
	@JoinColumn(name = "cancha_id", nullable = false)
	private CanchaEntity cancha;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jugador")
	private UsuarioEntity jugador;
	
	@OneToMany(mappedBy = "picado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioEntity> usuarios;

	@OneToMany(mappedBy = "equipo1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioEntity> equipo1;

	@OneToMany(mappedBy = "equipo2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioEntity> equipo2;

	public PicadoEntity() {

	}

	public PicadoEntity(CanchaEntity cancha, Date fecha) {

		this.cancha = cancha;
		this.fecha = fecha;
		this.picadoLevel = 0.0;
		this.completo = false;
		this.usuarios = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getPicadoLevel() {
		return picadoLevel;
	}

	public void setPicadoLevel(Double picadoLevel) {
		this.picadoLevel = picadoLevel;
	}

	public CanchaEntity getCancha() {
		return cancha;
	}

	public void setCancha(CanchaEntity cancha) {
		this.cancha = cancha;
	}

	public List<UsuarioEntity> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioEntity> jugadores) {
		this.usuarios = jugadores;
	}

	public List<UsuarioEntity> getEquipo1() {
		return equipo1;
	}

	public Boolean getCompleto() {
		return completo;
	}

	public void setCompleto(Boolean completo) {
		this.completo = completo;
	}
	
	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public UsuarioEntity getJugador() {
		return jugador;
	}

	public void setJugador(UsuarioEntity jugador) {
		this.jugador = jugador;
	}

	public void setEquipo1(List<UsuarioEntity> equipo1) {
		this.equipo1 = equipo1;
	}

	public List<UsuarioEntity> getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(List<UsuarioEntity> equipo2) {
		this.equipo2 = equipo2;
	}

	public void setEquipos(List<UsuarioEntity> equipo1, List<UsuarioEntity> equipo2) {

		setEquipo1(equipo1);
		setEquipo2(equipo2);
	}

	public void agregarUsuario(UsuarioEntity usuario) {
		if (usuarios == null) {
			usuarios = new ArrayList<>();
		}

		usuarios.add(usuario);
		usuario.setPicado(this);
	}
}
