package com.co.libros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.co.libros.models.entity.LibroListaLibros;
import com.co.libros.models.entity.ListaDeLibros;
import com.co.libros.models.service.ILibroListaLibrosService;
import com.co.libros.models.service.IListaDeLibrosService;

@RestController
public class ControllerLibros {
	
	@Autowired
	IListaDeLibrosService listaDeLibrosService;
	
	@Autowired
	ILibroListaLibrosService libroListaLibrosService;
	
	
	@PostMapping(value = "/libros/guardarListaDeLibros/")
	public ResponseEntity<Void> guardarUsuario(@RequestBody ListaDeLibros entidad, UriComponentsBuilder builder) {
		listaDeLibrosService.guardar(entidad);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/idListaLibros/{id}").buildAndExpand(entidad.getIdListaLibros()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	} 	
	
	@GetMapping("/libros/obtenerListaDeLibrosByUsuario/{idUsuario}")
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(@PathVariable Long idUsuario){
		return listaDeLibrosService.obtenerListaDeLibrosByUsuario(idUsuario);
	}
	
	@PostMapping(value = "/libros/guardarLibro/")
	public ResponseEntity<Void> guardarLibro(@RequestBody LibroListaLibros entidad, UriComponentsBuilder builder) {
		libroListaLibrosService.guardar(entidad);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/idLibroListaLibros/{id}").buildAndExpand(entidad.getIdLibroListaLibros()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	} 
	
	
	@GetMapping("/libros/getLibroById/{id}")
	public LibroListaLibros getServicioById(@PathVariable Long id) {
		return libroListaLibrosService.findById(id);
	}	
	
	@PostMapping(value = "/libros/eliminarLibro/")
	public ResponseEntity<Void> eliminarLibro(@RequestBody LibroListaLibros entidad, UriComponentsBuilder builder) {
		libroListaLibrosService.eliminar(entidad);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/servicio/{idLibroListaLibros}").buildAndExpand(0).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	} 
	
	@PostMapping(value = "/libros/eliminarLista/")
	public ResponseEntity<Void> eliminarLista(@RequestBody ListaDeLibros entidad, UriComponentsBuilder builder) {
		listaDeLibrosService.eliminar(entidad);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/eliminarLista/{idListaLibros}").buildAndExpand(0).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	} 		
	
	@GetMapping("/libros/obtenerLibrosDeLista/{idListaLibros}")
	public List<LibroListaLibros> obtenerLibrosDeLista(@PathVariable Long idListaLibros){
		return libroListaLibrosService.obtenerLibrosDeLista(idListaLibros);
	}	
		

}
