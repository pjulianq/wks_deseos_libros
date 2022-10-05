package com.co.libros.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.libros.models.dao.IListaDeLibrosDAO;
import com.co.libros.models.entity.ListaDeLibros;

@Service
public class ListaDeLibrosImpl implements IListaDeLibrosService{

	@Autowired
	IListaDeLibrosDAO listaDeLibrosService;
	
	@Override
	public void guardar(ListaDeLibros listaDelibros) {
		listaDeLibrosService.save(listaDelibros);
	}

	@Override
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario) {
		return listaDeLibrosService.obtenerListaDeLibrosByUsuario(idUsuario);
	}
	
	

}
