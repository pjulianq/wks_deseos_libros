package com.co.lista_deseos.clients.modelsdto;

import java.io.Serializable;

public class DtoSession implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idUsuarioSession;
	private Long idListaLibrosSession;
	public Long getIdUsuarioSession() {
		return idUsuarioSession;
	}
	public void setIdUsuarioSession(Long idUsuarioSession) {
		this.idUsuarioSession = idUsuarioSession;
	}
	public Long getIdListaLibrosSession() {
		return idListaLibrosSession;
	}
	public void setIdListaLibrosSession(Long idListaLibrosSession) {
		this.idListaLibrosSession = idListaLibrosSession;
	}
	
	

}
