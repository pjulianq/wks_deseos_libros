package com.co.libros.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.libros.models.dao.ILibroListaLibrosDAO;
import com.co.libros.models.entity.LibroListaLibros;

@Service
public class LibroListaLibrosImpl implements ILibroListaLibrosService {

	@Autowired
	ILibroListaLibrosDAO libroListaLibrosDAO;
	
	@Override
	public void guardar(LibroListaLibros libro) {
		libroListaLibrosDAO.save(libro);
		
	}

	@Override
	public LibroListaLibros findById(Long id) {
		return libroListaLibrosDAO.findById(id).orElse(null);
	}

	@Override
	public void eliminar(LibroListaLibros entidad) {
		libroListaLibrosDAO.delete(entidad);
	}

	@Override
	public List<LibroListaLibros> obtenerLibrosDeLista(Long idListaLibros) {
		return libroListaLibrosDAO.obtenerLibrosDeLista(idListaLibros);
	}
	
	
	
}
