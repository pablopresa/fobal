package com.fobal.repository.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_habilidad")
public class UsuarioHabilidadEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private UsuarioEntity usuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "habilidad_id", nullable = false)
	private HabilidadEntity habilidad;

	@Column(name = "valor")
	private Double valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public HabilidadEntity getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(HabilidadEntity habilidad) {
		this.habilidad = habilidad;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public UsuarioHabilidadEntity(UsuarioEntity usuario, HabilidadEntity habilidad, Double valor) {
		this.usuario = usuario;
		this.habilidad = habilidad;
		this.valor = valor;
	}

	public UsuarioHabilidadEntity() {
	}

}
