package com.co.lista_deseos.web.models.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.lista_deseos.clients.modelsdto.ListaDeLibros;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuarios;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBWrapper;

import lombok.extern.slf4j.Slf4j;

@Service("serviceRestTemplate")
@Slf4j
public class ClienteServicesImpl implements ClienteServices{
	
	private final String basepath="http://localhost:8090/";
	
	
	@Autowired
	RestTemplate clienteRest;

	@Override
	public MaestroUsuarios obtenerUsuarioLogginPassword(String loggin, String password) {
		
		String isbn = "9380658745";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity entity = restTemplate.getForEntity("https://www.googleapis.com/books/v1/volumes?q=-term+intitle:caperucita&printType=books&key=AIzaSyDuhgwVipeWWm7RaBQQ2k-ZpEuJWFZdKF8", GBWrapper.class);
		ResponseEntity entity2 = restTemplate.getForEntity("https://www.googleapis.com/books/v1/volumes?q=-term+intitle:caperucita&printType=books&key=AIzaSyDuhgwVipeWWm7RaBQQ2k-ZpEuJWFZdKF8", String.class);
		System.out.println(((GBWrapper) entity.getBody()).getItems()[0].getVolumeInfo().getPublisher());
		
		
		
		MaestroUsuarios usuario = clienteRest.getForObject(basepath+"/servicio-usuarios/usuarios/obtenerUsuarioLogginPassword/"+loggin+"/"+password, MaestroUsuarios.class);
		return usuario;
	}

	
	@Override
	public void guardarUsuario(MaestroUsuarios usuario) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepath+"servicio-usuarios/usuarios/guardarUsuario/");
			HttpEntity<MaestroUsuarios> requestEntity = new HttpEntity<>(usuario, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());				
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void guardarListaDeLibros(ListaDeLibros listaDeLibros) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepath+"servicio-libros/libros/guardarListaDeLibros/");
			HttpEntity<ListaDeLibros> requestEntity = new HttpEntity<>(listaDeLibros, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());				
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario) {
		List<ListaDeLibros> lista = Arrays.asList(clienteRest.getForObject(basepath+"servicio-libros/libros/obtenerListaDeLibrosByUsuario/"+idUsuario, ListaDeLibros[].class));
		return lista;
		
	}
	
	public void consultarLibrosGoogle() {
		
		 try {
			URL url = new URL("http://www.google.com/books/feeds/volumes/?q=ISBN%3C" + "9780262140874" + "%3E");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //VolumeQuery volumeQuery = new VolumeQuery(url);
		
	}
	
	
	
	

}