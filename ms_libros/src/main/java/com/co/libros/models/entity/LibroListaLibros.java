package com.co.libros.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="libros_lista_libros")
public class LibroListaLibros implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLibroListaLibros;
	
	@Column(name = "ID_LISTA_LIBROS")
	private Long idListaLibros;
	
	@Column(name = "S_ID_LIBRO_API_GOOGLE")
	private String sIdLibroApiGoogle;
	
	@Column(name = "S_NOMBRE_LIBRO")
	private String sNombreLibro;
	
	@Column(name = "S_AUTOR_LIBRO")
	private String sAutorLibro;
	
	@Column(name = "S_EDITORIAL_LIBRO")
	private String sEditorialLibro;

	public Long getIdLibroListaLibros() {
		return idLibroListaLibros;
	}

	public void setIdLibroListaLibros(Long idLibroListaLibros) {
		this.idLibroListaLibros = idLibroListaLibros;
	}

	public Long getIdListaLibros() {
		return idListaLibros;
	}

	public void setIdListaLibros(Long idListaLibros) {
		this.idListaLibros = idListaLibros;
	}

	public String getsIdLibroApiGoogle() {
		return sIdLibroApiGoogle;
	}

	public void setsIdLibroApiGoogle(String sIdLibroApiGoogle) {
		this.sIdLibroApiGoogle = sIdLibroApiGoogle;
	}

	public String getsNombreLibro() {
		return sNombreLibro;
	}

	public void setsNombreLibro(String sNombreLibro) {
		this.sNombreLibro = sNombreLibro;
	}

	public String getsAutorLibro() {
		return sAutorLibro;
	}

	public void setsAutorLibro(String sAutorLibro) {
		this.sAutorLibro = sAutorLibro;
	}

	public String getsEditorialLibro() {
		return sEditorialLibro;
	}

	public void setsEditorialLibro(String sEditorialLibro) {
		this.sEditorialLibro = sEditorialLibro;
	}
	
	


}
