package com.co.libros.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.libros.models.entity.LibroListaLibros;

public interface ILibroListaLibrosDAO extends CrudRepository<LibroListaLibros, Long>{
	
	@Query("select u from LibroListaLibros u where u.idListaLibros = ?1")
	List<LibroListaLibros> obtenerLibrosDeLista(Long idLista);
}
