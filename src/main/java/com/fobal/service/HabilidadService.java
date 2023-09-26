package com.fobal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fobal.repository.HabilidadRepository;
import com.fobal.repository.UsuarioHabilidadRepository;
import com.fobal.repository.UsuarioRepository;
import com.fobal.repository.entity.HabilidadEntity;
import com.fobal.repository.entity.UsuarioHabilidadEntity;

@Service
public class HabilidadService {

	HabilidadRepository habilidadRepository;
	UsuarioHabilidadRepository usuarioHabilidadRepository;
	UsuarioRepository usuarioRepository;

	@Autowired
	public HabilidadService(UsuarioRepository usuarioRepository, HabilidadRepository statRepository,
			UsuarioHabilidadRepository usuarioHabilidadRepository) {
		this.usuarioRepository = usuarioRepository;
		this.habilidadRepository = statRepository;
		this.usuarioHabilidadRepository = usuarioHabilidadRepository;
	}

	public HabilidadEntity guardar(HabilidadEntity stat) {

		return habilidadRepository.save(stat);
	}

	public UsuarioHabilidadEntity guardar(UsuarioHabilidadEntity habilidad) {

		return usuarioHabilidadRepository.save(habilidad);
	}

	public List<UsuarioHabilidadEntity> guardar(List<UsuarioHabilidadEntity> habilidades) {

		List<UsuarioHabilidadEntity> habilidadesBdd = new ArrayList<>();
		for (UsuarioHabilidadEntity habilidad : habilidades) {

			UsuarioHabilidadEntity habilidadBdd = usuarioHabilidadRepository.save(habilidad);
			habilidadesBdd.add(habilidadBdd);
		}
		return habilidadesBdd;
	}

}
