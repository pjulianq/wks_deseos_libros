package com.co.lista_deseos.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="maestro_usuarios")
public class MaestroUsuarios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
    
    @Column(name = "S_NOMBRE")
	private String sNombre;
    
    @Column(name = "S_LOGGIN")
	private String sLoggin;  
    
    @Column(name = "S_PASSWORD")
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
