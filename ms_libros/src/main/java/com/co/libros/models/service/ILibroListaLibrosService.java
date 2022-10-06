package com.co.libros.models.service;

import java.util.List;

import com.co.libros.models.entity.LibroListaLibros;

public interface ILibroListaLibrosService {
	
	public void guardar(LibroListaLibros libro);
	public LibroListaLibros findById(Long id);
	public void eliminar(LibroListaLibros entidad);
	public List<LibroListaLibros> obtenerLibrosDeLista(Long idListaLibros);

}
