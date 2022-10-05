package com.co.libros.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.libros.models.entity.ListaDeLibros;

public interface IListaDeLibrosDAO extends CrudRepository<ListaDeLibros,Long>{
	
	@Query("select u from ListaDeLibros u where u.idMaestroUsuario = ?1")
	List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario);

}
