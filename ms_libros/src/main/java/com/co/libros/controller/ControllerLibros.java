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

import com.co.libros.models.entity.ListaDeLibros;
import com.co.libros.models.service.IListaDeLibrosService;

@RestController
public class ControllerLibros {
	
	@Autowired
	IListaDeLibrosService listaDeLibrosService;
	
	
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

}