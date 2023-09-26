package com.fobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fobal.repository.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	@Query(value = "select u from UsuarioEntity u where u.nickname=:nickname and u.password=:pass")
	UsuarioEntity findByLoginNickname(@Param("nickname") String nickname, @Param("pass") byte[] pass);

	@Query(value = "select u from UsuarioEntity u where u.nombre=:nombre and u.password=:pass")
	UsuarioEntity findByLoginUsername(@Param("nombre") String nombre, @Param("pass") byte[] pass);

//	@Query(value = "select u from UsuarioEntity u where u.email=:mail")
//	UsuarioEntity findByEmail(@Param("mail") String email);

	@Query(value = "select u from UsuarioEntity u where u.nombre=:nombre")
	UsuarioEntity findByNombreUsuario(@Param("nombre") String nombre );

	@Query(value = "select u from UsuarioEntity u where u.id=:id")
	UsuarioEntity findUsuarioById(@Param("id") Integer id);
	
	@Query(value = "select u from UsuarioEntity u where u.nickname = :nickname")
	UsuarioEntity findByNickname(@Param("nickname") String nickname);

}
