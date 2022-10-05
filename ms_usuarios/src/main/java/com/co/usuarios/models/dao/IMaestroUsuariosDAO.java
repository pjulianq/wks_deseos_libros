package com.co.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.usuarios.models.entity.MaestroUsuarios;

public interface IMaestroUsuariosDAO extends CrudRepository<MaestroUsuarios,Long>{
	
	
	@Query("select u from MaestroUsuarios u where u.sLoggin = ?1 and u.sPassword = ?2")
	MaestroUsuarios obtenerUsuarioLogginPassword(String loggin, String password);

}
