package com.fobal.repository.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "stats")
public class HabilidadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="peso")
	private Integer peso;
	
	@OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioHabilidadEntity> usuarios;
	
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
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	public HabilidadEntity() {
		
	};
	
	public HabilidadEntity(String nombre, Integer peso) {
		this.nombre = nombre;
		this.peso = peso;
	}
}
