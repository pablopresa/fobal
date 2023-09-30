package com.fobal.repository.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fobal.util.Util;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "alias")
	private String alias;
	@JdbcType(VarbinaryJdbcType.class)
//	@JsonIgnore
	@Column(name = "password")
	private byte[] password;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "tipo_usuario_id", nullable = false)
	private TipoUsuarioEntity tipoUsuario;
	@JsonIgnore
	@Column(name = "email")
	private String email;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "fecha_creacion")
	private String fechaCreacion;
	@Column(name = "valoracion_general")
	private Double valoracionGeneral;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "picado_id")
	private PicadoEntity picado;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "picado_equipo1")
	private PicadoEntity equipo1;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "picado_equipo2")
	private PicadoEntity equipo2;
	
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPicadoEntity> historial;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioHabilidadEntity> habilidades;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public PicadoEntity getPicado() {
		return picado;
	}

	public void setPicado(PicadoEntity picado) {
		this.picado = picado;
	}

	public TipoUsuarioEntity getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public PicadoEntity getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(PicadoEntity equipo1) {
		this.equipo1 = equipo1;
	}

	public PicadoEntity getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(PicadoEntity equipo2) {
		this.equipo2 = equipo2;
	}

	public List<UsuarioHabilidadEntity> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<UsuarioHabilidadEntity> habilidades) {
		this.habilidades = habilidades;
	}

	public Double getValoracionGeneral() {
		return valoracionGeneral;
	}

	public void setValoracionGeneral(Double valoracionGeneral) {
		this.valoracionGeneral = valoracionGeneral;
	}
	
	public List<UsuarioPicadoEntity> getHistorial() {
		return historial;
	}

	public void setHistorial(List<UsuarioPicadoEntity> historial) {
		this.historial = historial;
	}

	public UsuarioEntity() {

	}

	public UsuarioEntity(String nombre, String alias, TipoUsuarioEntity tipoUsuario) {

		this.nombre = nombre;
		this.alias = alias;
		this.tipoUsuario = tipoUsuario;
		this.valoracionGeneral = 0.0;
	}

	public UsuarioEntity(String nombre, String alias, Integer tipo) {

		this.nombre = nombre;
		this.alias = alias;
		this.valoracionGeneral = 0.0;
		this.tipoUsuario = new TipoUsuarioEntity(tipo);
	}

	@Override
	public String toString() {
		return "UsuarioEntity [nombre=" + nombre + ", valoracionGeneral=" + valoracionGeneral + "]";
	}

	public Double obtenerPromedioHabilidades(PicadoEntity picado) {

		List<UsuarioHabilidadEntity> habilidades = getHabilidades();
		Double promedio = 0.0;
		for (UsuarioHabilidadEntity habilidad : habilidades) {
			
			promedio += habilidad.getHabilidad().getPeso() * habilidad.getValor() / 10;

		}
		
		Double valoracion = Util.truncarDouble(promedio / habilidades.size(), 1);
		setValoracionGeneral(valoracion);
		return valoracion;
	}

}
