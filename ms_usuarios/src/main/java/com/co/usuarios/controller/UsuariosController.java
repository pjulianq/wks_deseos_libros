package com.co.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.usuarios.models.entity.MaestroUsuarios;
import com.co.usuarios.models.service.IMaestroUsuariosService;

@RestController
public class UsuariosController {
	
	@Autowired
	private IMaestroUsuariosService maestroUsuariosService;
	
	@GetMapping("/usuarios/listarUsuarios")
	public List<MaestroUsuarios> listarUsuarios(){
		return maestroUsuariosService.findAll();
	}

}
