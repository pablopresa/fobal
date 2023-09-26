package com.fobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fobal.repository.entity.TipoUsuarioEntity;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {

	@Query(value = "select tu from TipoUsuarioEntity tu where tu.nombre=:nombre")
	TipoUsuarioEntity findByNombre(@Param("nombre") String nombre );
}