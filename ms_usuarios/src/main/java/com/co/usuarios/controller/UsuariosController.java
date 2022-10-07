package com.co.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.co.usuarios.models.entity.MaestroUsuarios;
import com.co.usuarios.models.service.IMaestroUsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IMaestroUsuariosService maestroUsuariosService;
	
	@GetMapping("/listarUsuarios")
	public List<MaestroUsuarios> listarUsuarios(){
		return maestroUsuariosService.findAll();
	}
	
	@GetMapping("/obtenerUsuarioLogginPassword/{loggin}/{password}")
	public MaestroUsuarios obtenerUsuarioLogginPassword(@PathVariable String loggin,@PathVariable String password) {
		return maestroUsuariosService.obtenerUsuarioLogginPassword(loggin,password);
	}
	
	@PostMapping(value = "/usuarios/guardarUsuario/")
	public ResponseEntity<Void> guardarUsuario(@RequestBody MaestroUsuarios entidad, UriComponentsBuilder builder) {
		maestroUsuariosService.guardar(entidad);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/idUsuario/{id}").buildAndExpand(entidad.getIdUsuario()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	} 	

}
