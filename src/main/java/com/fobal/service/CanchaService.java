package com.fobal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fobal.repository.CanchaRepository;
import com.fobal.repository.entity.CanchaEntity;

@Service
public class CanchaService {

	CanchaRepository canchaRepository;

	public CanchaService(CanchaRepository canchaRepository) {

		this.canchaRepository = canchaRepository;
	}

	public CanchaEntity obtenerCancha(Integer idCancha) {

		return canchaRepository.findById(idCancha).get();
	}

	public List<CanchaEntity> obtenerCanchas() {

		return canchaRepository.findAll();
	}

	public CanchaEntity guardar(CanchaEntity cancha) {

		return canchaRepository.save(cancha);
	}

}
