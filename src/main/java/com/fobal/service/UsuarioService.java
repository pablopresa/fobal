package com.fobal.service;

import java.security.spec.KeySpec;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fobal.repository.TipoUsuarioRepository;
import com.fobal.repository.UsuarioRepository;
import com.fobal.repository.entity.TipoUsuarioEntity;
import com.fobal.repository.entity.UsuarioEntity;

@Service
public class UsuarioService {

	UsuarioRepository usuarioRepository;
	TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.tipoUsuarioRepository = tipoUsuarioRepository;
	}

	public UsuarioEntity obtenerPorId(Integer id) {
		return usuarioRepository.findUsuarioById(id);
	}

	public UsuarioEntity obtenerPorNombreUsuario(String nombre) {
		return usuarioRepository.findByNombreUsuario(nombre);
	}

	public UsuarioEntity obtenerPorEmailPassword(String nickname, byte[] password) {
		return usuarioRepository.findByLoginNickname(nickname, password);
	}

	public UsuarioEntity obtenerPorNombrePassword(String nombre, byte[] password) {
		return usuarioRepository.findByLoginUsername(nombre, password);
	}

	public UsuarioEntity guardar(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}

	public TipoUsuarioEntity guardar(TipoUsuarioEntity tipoUsuario) {
		return tipoUsuarioRepository.save(tipoUsuario);
	}
	
	public TipoUsuarioEntity obtenerPorNombre(String nombre) {
		return tipoUsuarioRepository.findByNombre(nombre);
	}
	
	public List<UsuarioEntity> obtenerTodos() {
		return usuarioRepository.findAll();
	}

	public TipoUsuarioEntity obtenerTipoUsuarioPorId(Integer id) {
		return (tipoUsuarioRepository.findById(id).isPresent()) ? tipoUsuarioRepository.findById(id).get() : null;
	}

	public byte[] hashPass(String pass) {
//		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
//		random.nextBytes(salt);
		KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return factory.generateSecret(spec).getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salt;
	}

	public UsuarioEntity obtenerPorNickname(String nickname) {
		return usuarioRepository.findByNickname(nickname);
	}

	
}
