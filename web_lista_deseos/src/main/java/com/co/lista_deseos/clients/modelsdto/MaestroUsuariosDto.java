package com.co.lista_deseos.clients.modelsdto;

import java.io.Serializable;

public class MaestroUsuariosDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long idUsuario;
	private String sNombre;
	private String sLoggin;
	private String sPassword;
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getsNombre() {
		return sNombre;
	}
	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}
	public String getsLoggin() {
		return sLoggin;
	}
	public void setsLoggin(String sLoggin) {
		this.sLoggin = sLoggin;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	
	
	
	
	
	
	

}
