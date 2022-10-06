package com.co.libros.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.libros.models.dao.ILibroListaLibrosDAO;
import com.co.libros.models.dao.IListaDeLibrosDAO;
import com.co.libros.models.entity.LibroListaLibros;
import com.co.libros.models.entity.ListaDeLibros;

@Service
public class ListaDeLibrosImpl implements IListaDeLibrosService{

	@Autowired
	IListaDeLibrosDAO listaDeLibrosDAO;
	
	@Autowired
	ILibroListaLibrosDAO libroListaLibrosDAO;
	

	
	@Override
	public void guardar(ListaDeLibros listaDelibros) {
		listaDeLibrosDAO.save(listaDelibros);
	}

	@Override
	public List<ListaDeLibros> obtenerListaDeLibrosByUsuario(Long idUsuario) {
		return listaDeLibrosDAO.obtenerListaDeLibrosByUsuario(idUsuario);
	}

	@Override
	public void eliminar(ListaDeLibros entidad) {
		List<LibroListaLibros> lstLibrosDel = libroListaLibrosDAO.obtenerLibrosDeLista(entidad.getIdListaLibros());
		if(lstLibrosDel!=null && lstLibrosDel.size()>0)
			for(LibroListaLibros ent:lstLibrosDel)
				libroListaLibrosDAO.delete(ent);
		listaDeLibrosDAO.delete(entidad);
		
	}
	
	

	
	

}
