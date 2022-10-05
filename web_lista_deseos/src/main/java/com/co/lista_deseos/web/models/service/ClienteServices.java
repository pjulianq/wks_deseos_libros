package com.co.lista_deseos.web.models.service;

import java.util.List;

import com.co.lista_deseos.clients.modelsdto.ListaDeLibros;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuarios;

public interface ClienteServices {
	
	public MaestroUsuarios obtenerUsuarioLogginPassword(String loggin, String password);
	public void guardarUsuario(MaestroUsuarios entidad);
	public void guardarListaDeLibros(ListaDeLibros entidad);
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario);
	

}
