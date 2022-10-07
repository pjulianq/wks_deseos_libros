package com.co.libros.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="lista_de_libros")
public class ListaDeLibros  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idListaLibros;
	
	@Column(name = "ID_MAESTRO_USUARIO")
	private Long idMaestroUsuario;
	
	@Column(name = "S_NOMBRE_LISTA")
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
