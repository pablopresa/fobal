package com.fobal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fobal.repository.entity.CanchaEntity;
import com.fobal.service.CanchaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cancha")
public class CanchaController {

	CanchaService canchaService;

	@Autowired
	public CanchaController(CanchaService canchaService) {
		this.canchaService = canchaService;
	}

	@PostMapping("")
	public ResponseEntity<CanchaEntity> crearCancha(@RequestBody CanchaEntity cancha) {

		CanchaEntity canchaBdd = canchaService.guardar(cancha);

		return (canchaBdd != null) ? new ResponseEntity<>(canchaBdd, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{idCancha}")
	public ResponseEntity<CanchaEntity> obtenerCancha(@PathVariable Integer idCancha) {

		CanchaEntity canchaEntity = canchaService.obtenerCancha(idCancha);

		return (canchaEntity != null) ? new ResponseEntity<>(canchaEntity, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CanchaEntity>> obtenerCanchas() {

		List<CanchaEntity> canchas = canchaService.obtenerCanchas();

		return (canchas != null) ? new ResponseEntity<>(canchas, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
