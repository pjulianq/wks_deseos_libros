package com.co.libros.models.service;

import java.util.List;

import com.co.libros.models.entity.ListaDeLibros;

public interface IListaDeLibrosService {
	
	public void guardar(ListaDeLibros listaDelibros);
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario);

}
