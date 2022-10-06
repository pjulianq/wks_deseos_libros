package com.co.lista_deseos.clients.modelsdto;

import java.io.Serializable;

public class ListaDeLibros implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idListaLibros;
	private Long idMaestroUsuario;
	private String sNombreLista;
	
	
	
	
	
	
	public Long getIdListaLibros() {
		return idListaLibros;
	}
	public void setIdListaLibros(Long idListaLibros) {
		this.idListaLibros = idListaLibros;
	}

	public Long getIdMaestroUsuario() {
		return idMaestroUsuario;
	}
	public void setIdMaestroUsuario(Long idMaestroUsuario) {
		this.idMaestroUsuario = idMaestroUsuario;
	}
	public String getsNombreLista() {
		return sNombreLista;
	}
	public void setsNombreLista(String sNombreLista) {
		this.sNombreLista = sNombreLista;
	}


}
