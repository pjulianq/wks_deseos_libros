package com.co.lista_deseos.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.lista_deseos.clients.modelsdto.ListaDeLibros;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuarios;
import com.co.lista_deseos.web.models.service.ClienteServices;

/**
 * 
 * @author PQ
 * 03/10/2022
 * Controlador Inicial de la aplicación web, se encarga de las acciones
 */
@Controller
public class ControladorInicial {
	
	@Autowired
	ClienteServices clienteServices;	
	
	@GetMapping("/libreria")
	public String loggin(Model model) {
		model.addAttribute("identificacion","");
		return "logging";
	}
	
	
    @PostMapping("/ingresarSistema")
    public String ingresarSistema(@RequestParam("loggin") String loggin,@RequestParam("password") String password,Model model){
    	if(loggin==null || "".equals(loggin))
    		return "logging";
    	MaestroUsuarios usuario = clienteServices.obtenerUsuarioLogginPassword(loggin,password);
    	if(usuario==null)
    		return "logging";
    	model.addAttribute("maestroUsuarios", usuario==null?new MaestroUsuarios():usuario);
    	
    	consultarListasDeLibrosByUsuario(usuario.getIdUsuario(), model);	
    	return "paginaPrincipal";
    }	
	
	@GetMapping("/openRegistroUsuario")
	public String openRegistroUsuario(Model model) {
		model.addAttribute("maestroUsuarios",new MaestroUsuarios());
		return "registroUsuario";
	}
	
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@Valid MaestroUsuarios admUsuario,Errors errores){
        if(errores.hasErrors()){
            return "openRegistroUsuario";
        }
        clienteServices.guardarUsuario(admUsuario);
        return "logging";
    }  
    
	@GetMapping("/agregarListaLibros/{idUsuario}")
	public String agregarListaLibros(MaestroUsuarios maestro,Model model) {
		
		ListaDeLibros listaDeLibros = new ListaDeLibros();
		listaDeLibros.setIdMaestroUsuario(maestro.getIdUsuario());
		
		model.addAttribute("listaDeLibros",listaDeLibros);
		return "editarListaLibros";
	}
	
    @PostMapping("/guardarListaLibros")
    public String guardarListaLibros(@Valid ListaDeLibros listaDeLibros,Errors errores, Model model){
        if(errores.hasErrors()){
            return "openRegistroUsuario";
        }
        clienteServices.guardarListaDeLibros(listaDeLibros);
        
        MaestroUsuarios usuario = new MaestroUsuarios();
        usuario.setIdUsuario(listaDeLibros.getIdMaestroUsuario());
        model.addAttribute("maestroUsuarios",usuario);
        consultarListasDeLibrosByUsuario(listaDeLibros.getIdMaestroUsuario(), model);
        return "paginaPrincipal";
    }  
    
	@GetMapping("/obtenerListaDeLibrosByUsuario")
	public String obtenerListaDeLibrosByUsuario(@RequestParam("idUsuario") Long idUsuario, Model model) {
		
		consultarListasDeLibrosByUsuario(idUsuario, model);	
		return "registroUsuario";
	}   
	
	
	@RequestMapping(value="/guardarListaLibros", method=RequestMethod.POST, params="action=buscarLibros")
	public String buscarLibros(@Valid ListaDeLibros listaDeLibros,Model model,@RequestParam("nombreLibro") String nombreLibro) {
		return "paginaPrincipal";
	}
	
	private void consultarListasDeLibrosByUsuario(Long idUsuario,Model model) {
		var lista = clienteServices.obtenerListaDeLibrosByUsuario(idUsuario);
    	model.addAttribute("listasLibrosUsuarios", lista);		
	}
	
	
	
    
    
    
    
    
}
