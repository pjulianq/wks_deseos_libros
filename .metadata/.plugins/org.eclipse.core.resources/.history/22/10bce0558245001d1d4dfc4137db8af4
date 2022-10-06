package com.co.lista_deseos.web.models.service;

import java.util.List;

import com.co.lista_deseos.clients.modelsdto.LibroListaLibros;
import com.co.lista_deseos.clients.modelsdto.ListaDeLibros;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuarios;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBVolumeInfoWrapper;

public interface ClienteServices {
	
	public MaestroUsuarios obtenerUsuarioLogginPassword(String loggin, String password);
	public void guardarUsuario(MaestroUsuarios entidad);
	public void guardarListaDeLibros(ListaDeLibros entidad);
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario);
	public List<GBVolumeInfoWrapper> obtenerLibrosGoole(String titulo, String autor);
	public void guardarLibroEnLista(LibroListaLibros entidad);
	
	public String eliminarLibro(LibroListaLibros entidad);
	public LibroListaLibros librofindById(Long id);
	public List<LibroListaLibros> obtenerLibrosDeLista (Long idLista);
	
	public String eliminarLista(ListaDeLibros entidad);
	

}
