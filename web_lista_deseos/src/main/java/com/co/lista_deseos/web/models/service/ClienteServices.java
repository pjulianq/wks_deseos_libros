package com.co.lista_deseos.web.models.service;

import java.util.List;

import com.co.lista_deseos.clients.modelsdto.LibroListaLibrosDto;
import com.co.lista_deseos.clients.modelsdto.ListaDeLibrosDto;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuariosDto;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBVolumeInfoWrapper;

public interface ClienteServices {
	
	public MaestroUsuariosDto obtenerUsuarioLogginPassword(String loggin, String password);
	public void guardarUsuario(MaestroUsuariosDto entidad);
	public void guardarListaDeLibros(ListaDeLibrosDto entidad);
	public List<ListaDeLibrosDto> obtenerListaDeLibrosByUsuario(Long idUsuario);
	public List<GBVolumeInfoWrapper> obtenerLibrosGoole(String titulo, String autor, String editorial);
	public void guardarLibroEnLista(LibroListaLibrosDto entidad);
	
	public String eliminarLibro(LibroListaLibrosDto entidad);
	public LibroListaLibrosDto librofindById(Long id);
	public List<LibroListaLibrosDto> obtenerLibrosDeLista (Long idLista);
	
	public String eliminarLista(ListaDeLibrosDto entidad);
	

}
