package com.co.lista_deseos.web.models.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.lista_deseos.clients.modelsdto.LibroListaLibros;
import com.co.lista_deseos.clients.modelsdto.ListaDeLibros;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuarios;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBItemsWrapper;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBVolumeInfoWrapper;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBWrapper;

import lombok.extern.slf4j.Slf4j;

@Service("serviceRestTemplate")
@Slf4j
public class ClienteServicesImpl implements ClienteServices{
	
	private final String basepath="http://localhost:8090/";
	private final String baseApiBook="https://www.googleapis.com/books/v1/volumes?q=-term";
	private final String baseFilterAutor ="+inauthor:";
	private final String baseFilterTitulo ="+intitle:";
	private final String baseKey ="AIzaSyDuhgwVipeWWm7RaBQQ2k-ZpEuJWFZdKF8";
	
	
	@Autowired
	RestTemplate clienteRest;

	@Override
	public MaestroUsuarios obtenerUsuarioLogginPassword(String loggin, String password) {
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
	

	@Override
	public List<GBVolumeInfoWrapper> obtenerLibrosGoole(String titulo, String autor) {
		RestTemplate restTemplate = new RestTemplate();
		String stsrConsultaGoogle = baseApiBook;
		if(titulo!=null && !"".equals(titulo))
			stsrConsultaGoogle = stsrConsultaGoogle + baseFilterTitulo + titulo;
		if(autor!=null && !"".equals(autor))
			stsrConsultaGoogle = stsrConsultaGoogle + baseFilterAutor + autor;
		stsrConsultaGoogle = stsrConsultaGoogle +"&printType=books";
		stsrConsultaGoogle = stsrConsultaGoogle +"&key="+baseKey;
			
		ResponseEntity<GBWrapper> entity = restTemplate.getForEntity(stsrConsultaGoogle, GBWrapper.class);
		GBWrapper wrapper=(entity.getBody());
		GBItemsWrapper[] lstLibros = wrapper.getItems();
		GBVolumeInfoWrapper add;
		List<GBVolumeInfoWrapper> lstlibrosInfo = new ArrayList<GBVolumeInfoWrapper>();
		String strAutores="";
		for(GBItemsWrapper object:lstLibros) {
			add = object.getVolumeInfo();
			add.setTitle(add.getTitle().replace("?",""));
			add.setId(object.getId());
			strAutores="";
			if(add.getAuthors()!=null) {
				for(String aut:add.getAuthors()) {
					strAutores=strAutores+ "" +aut;
				}
				add.setAutorsString(strAutores);
			}
			lstlibrosInfo.add(add);
		}
		//ResponseEntity entity2 = restTemplate.getForEntity("https://www.googleapis.com/books/v1/volumes?q=-term+intitle:caperucita&printType=books&key=AIzaSyDuhgwVipeWWm7RaBQQ2k-ZpEuJWFZdKF8", String.class);
		System.out.println(((GBWrapper) entity.getBody()).getItems()[0].getVolumeInfo().getPublisher());	
		return lstlibrosInfo;
	}
	
	
	@Override
	public void guardarLibroEnLista(LibroListaLibros libro) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepath+"servicio-libros/libros/guardarLibro/");
			HttpEntity<LibroListaLibros> requestEntity = new HttpEntity<>(libro, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());				
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String eliminarLibro(LibroListaLibros entidad) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepath+"servicio-libros/libros/eliminarLibro/");
			HttpEntity<LibroListaLibros> requestEntity = new HttpEntity<>(entidad, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());
			return uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public LibroListaLibros librofindById(Long id) {
		LibroListaLibros entidad = clienteRest.getForObject(basepath+"servicio-libros/libros/getLibroById/"+id, LibroListaLibros.class);
		return entidad;
	}
	@Override
	public List<LibroListaLibros> obtenerLibrosDeLista(Long idListaLibros) {
		List<LibroListaLibros> lista = Arrays.asList(clienteRest.getForObject(basepath+"servicio-libros/libros/obtenerLibrosDeLista/"+idListaLibros, LibroListaLibros[].class));
		return lista;
		
	}
	
	
	@Override
	public String eliminarLista(ListaDeLibros entidad) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepath+"servicio-libros/libros/eliminarLista/");
			HttpEntity<ListaDeLibros> requestEntity = new HttpEntity<>(entidad, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());
			return uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return "";
	}	
	
	
	
	
}
