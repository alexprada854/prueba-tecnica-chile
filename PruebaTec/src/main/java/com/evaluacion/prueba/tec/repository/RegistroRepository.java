package com.evaluacion.prueba.tec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.prueba.tec.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Integer> {
	List<Registro> findByName(String Name);
	Optional<Registro> findById(Integer id);
}
