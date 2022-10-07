package com.co.lista_deseos.web.models.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.co.lista_deseos.clients.modelsdto.LibroListaLibrosDto;
import com.co.lista_deseos.clients.modelsdto.ListaDeLibrosDto;
import com.co.lista_deseos.clients.modelsdto.MaestroUsuariosDto;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBItemsWrapper;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBVolumeInfoWrapper;
import com.co.lista_deseos.clients.modelsdto.apibooksgoogle.GBWrapper;
import com.co.lista_deseos.models.dao.ILibroListaLibrosDAO;
import com.co.lista_deseos.models.dao.IListaDeLibrosDAO;
import com.co.lista_deseos.models.dao.IMaestroUsuariosDAO;
import com.co.lista_deseos.models.entity.LibroListaLibros;
import com.co.lista_deseos.models.entity.ListaDeLibros;
import com.co.lista_deseos.models.entity.MaestroUsuarios;

import lombok.extern.slf4j.Slf4j;

@Service("serviceRestTemplate")
@Slf4j
public class ClienteServicesImpl implements ClienteServices{
	
	private final String basepathMsUsuarios="http://localhost:8002/";
	private final String basepathMsLibros="http://localhost:8003/";
	private final String basepathZuul="http://localhost:8090/";
	
	//private final String baseApiBook="https://www.googleapis.com/books/v1/volumes?q=-term";
	//private final String baseFilterAutor ="+inauthor:";
	//private final String baseFilterTitulo ="+intitle:";
	//private final String baseFilterPublisher ="+inpublisher:";
	//private final String baseKey ="AIzaSyDuhgwVipeWWm7RaBQQ2k-ZpEuJWFZdKF8";
	
	
	@Autowired
	RestTemplate clienteRest;
	
	@Autowired
	IMaestroUsuariosDAO maestroUsuariosDAO;
	
	@Autowired
	IListaDeLibrosDAO listaDeLibrosDAO;
	
	@Autowired
	ILibroListaLibrosDAO libroListaLibrosDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${google.keyAPIBooks}")
	private String gooleKeyAPIBooks;
	
	@Value("${google.urlAPIBooks}")
	private String gooleURLAPIBooks;	
	
	@Value("${google.baseFilterAutor}")
	private String baseFilterAutor;	
	
	@Value("${google.baseFilterTitulo}")
	private String baseFilterTitulo;	
	
	@Value("${google.baseFilterPublisher}")
	private String baseFilterPublisher;		

	@Override
	public MaestroUsuariosDto obtenerUsuarioLogginPassword(String loggin, String password) {
		//MaestroUsuarios usuario = clienteRest.getForObject(basepathZuul+"/servicio-usuarios/usuarios/obtenerUsuarioLogginPassword/"+loggin+"/"+password, MaestroUsuarios.class);
		//MaestroUsuarios usuario = clienteRest.getForObject(basepathMsUsuarios+"/usuarios/obtenerUsuarioLogginPassword/"+loggin+"/"+password, MaestroUsuarios.class);
		com.co.lista_deseos.models.entity.MaestroUsuarios usuario = maestroUsuariosDAO.obtenerUsuarioLogginPassword(loggin, password);
		MaestroUsuariosDto usuarioreturn= null;
		if(usuario!=null)
			usuarioreturn = modelMapper.map(usuario, MaestroUsuariosDto.class);
		
		return usuarioreturn;
	}

	
	@Override
	public void guardarUsuario(MaestroUsuariosDto usuario) {
		/*
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			
			url = new URI(basepathZuul+"servicio-usuarios/usuarios/guardarUsuario/");
			HttpEntity<MaestroUsuariosDto> requestEntity = new HttpEntity<>(usuario, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());	

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
		
		MaestroUsuarios mu = modelMapper.map(usuario, MaestroUsuarios.class);
		maestroUsuariosDAO.save(mu);
	}	
	
	@Override
	public void guardarListaDeLibros(ListaDeLibrosDto listaDeLibros) {
		/*
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepathZuul+"servicio-libros/libros/guardarListaDeLibros/");
			HttpEntity<ListaDeLibrosDto> requestEntity = new HttpEntity<>(listaDeLibros, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());				
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
		ListaDeLibros ll = modelMapper.map(listaDeLibros, ListaDeLibros.class);
		listaDeLibrosDAO.save(ll);
	}


	@Override
	public List<ListaDeLibrosDto> obtenerListaDeLibrosByUsuario(Long idUsuario) {
		//List<ListaDeLibros> lista = Arrays.asList(clienteRest.getForObject(basepathZuul+"servicio-libros/libros/obtenerListaDeLibrosByUsuario/"+idUsuario, ListaDeLibros[].class));
		List<ListaDeLibrosDto> lista=null; 
		List<ListaDeLibros> listaDao = listaDeLibrosDAO.obtenerListaDeLibrosByUsuario(idUsuario);
		if(listaDao!=null && listaDao.size()>0) {
			lista = new ArrayList<ListaDeLibrosDto>();
			for(ListaDeLibros l:listaDao)
				lista.add( modelMapper.map(l, ListaDeLibrosDto.class));
		}
		return lista;
		
	}
	

	@Override
	public List<GBVolumeInfoWrapper> obtenerLibrosGoole(String titulo, String autor,String editorial) {
		RestTemplate restTemplate = new RestTemplate();
		String stsrConsultaGoogle = gooleURLAPIBooks;
		if(titulo!=null && !"".equals(titulo))
			stsrConsultaGoogle = stsrConsultaGoogle + baseFilterTitulo + titulo;
		if(autor!=null && !"".equals(autor))
			stsrConsultaGoogle = stsrConsultaGoogle + baseFilterAutor + autor;
		if(editorial!=null && !"".equals(editorial))
			stsrConsultaGoogle = stsrConsultaGoogle + baseFilterPublisher + editorial;		
		stsrConsultaGoogle = stsrConsultaGoogle +"&printType=books";
		stsrConsultaGoogle = stsrConsultaGoogle +"&key="+gooleKeyAPIBooks;
			
		ResponseEntity<GBWrapper> entity = restTemplate.getForEntity(stsrConsultaGoogle, GBWrapper.class);
		GBWrapper wrapper=(entity.getBody());
		GBItemsWrapper[] lstLibros = wrapper.getItems();
		GBVolumeInfoWrapper add;
		List<GBVolumeInfoWrapper> lstlibrosInfo = new ArrayList<GBVolumeInfoWrapper>();
		String strAutores="";
		if(lstLibros!=null)
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
		//System.out.println(((GBWrapper) entity.getBody()).getItems()[0].getVolumeInfo().getPublisher());	
		return lstlibrosInfo;
	}
	
	
	@Override
	public void guardarLibroEnLista(LibroListaLibrosDto libro) {
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepathZuul+"servicio-libros/libros/guardarLibro/");
			HttpEntity<LibroListaLibrosDto> requestEntity = new HttpEntity<>(libro, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());				
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
		libroListaLibrosDAO.save(modelMapper.map(libro, LibroListaLibros.class));
	}

	@Override
	public String eliminarLibro(LibroListaLibrosDto entidad) {
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepathZuul+"servicio-libros/libros/eliminarLibro/");
			HttpEntity<LibroListaLibrosDto> requestEntity = new HttpEntity<>(entidad, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());
			return uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
		libroListaLibrosDAO.delete(modelMapper.map(entidad, LibroListaLibros.class));
		return "";
	}

	@Override
	public LibroListaLibrosDto librofindById(Long id) {
		LibroListaLibros libro = libroListaLibrosDAO.findById(id).orElse(null);
		LibroListaLibrosDto retorno=null;
		if(libro!=null)
			retorno = modelMapper.map(libro, LibroListaLibrosDto.class);
		
		//LibroListaLibrosDto entidad = clienteRest.getForObject(basepathZuul+"servicio-libros/libros/getLibroById/"+id, LibroListaLibrosDto.class);
		return retorno;
	}
	@Override
	public List<LibroListaLibrosDto> obtenerLibrosDeLista(Long idListaLibros) {
		List<LibroListaLibros> listaDAO = libroListaLibrosDAO.obtenerLibrosDeLista(idListaLibros);
		List<LibroListaLibrosDto> lista = null;
		if(listaDAO!=null && listaDAO.size()>0) {
			lista = new ArrayList<LibroListaLibrosDto>();
			for(LibroListaLibros e:listaDAO)
				lista.add(modelMapper.map(e, LibroListaLibrosDto.class));
			
		}
		
		//List<LibroListaLibrosDto> lista = Arrays.asList(clienteRest.getForObject(basepathZuul+"servicio-libros/libros/obtenerLibrosDeLista/"+idListaLibros, LibroListaLibrosDto[].class));
		return lista;
		
	}
	
	
	@Override
	public String eliminarLista(ListaDeLibrosDto entidad) {
		/*
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI url;
		try {
			url = new URI(basepathZuul+"servicio-libros/libros/eliminarLista/");
			HttpEntity<ListaDeLibrosDto> requestEntity = new HttpEntity<>(entidad, headers);
			RestTemplate restTemplate = new RestTemplate();
			URI uri = restTemplate.postForLocation(url, requestEntity);
			System.out.println(uri.getPath());
			return uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/
		
		List<LibroListaLibros> lstLibrosDel = libroListaLibrosDAO.obtenerLibrosDeLista(entidad.getIdListaLibros());
		if(lstLibrosDel!=null && lstLibrosDel.size()>0)
			for(LibroListaLibros ent:lstLibrosDel)
				libroListaLibrosDAO.delete(ent);
		listaDeLibrosDAO.delete(modelMapper.map(entidad, ListaDeLibros.class));
		return "";
	}	
	
	
	
	
}
