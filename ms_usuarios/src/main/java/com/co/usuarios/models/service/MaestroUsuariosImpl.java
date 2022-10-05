package com.co.usuarios.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.usuarios.models.dao.IMaestroUsuariosDAO;
import com.co.usuarios.models.entity.MaestroUsuarios;

@Service
public class MaestroUsuariosImpl implements IMaestroUsuariosService{
	
	@Autowired
	IMaestroUsuariosDAO maestroUsuariosDAO;

	@Override
	@Transactional(readOnly = true)
	public List<MaestroUsuarios> findAll() {
		return (List<MaestroUsuarios>) maestroUsuariosDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public MaestroUsuarios findById(Long idUsuario) {
		return maestroUsuariosDAO.findById(idUsuario).orElse(null);
	}

	@Override
	public MaestroUsuarios obtenerUsuarioLogginPassword(String loggin, String password) {
		return maestroUsuariosDAO.obtenerUsuarioLogginPassword(loggin, password);
	}

	@Override
	public void guardar(MaestroUsuarios maestroUsuario) {
		maestroUsuariosDAO.save(maestroUsuario);
	}

}
