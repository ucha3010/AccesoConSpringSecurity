package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.Direccion;

public interface DireccionDao {

	public Direccion findById(int idDir);

	public void save(Direccion direccion, HttpServletRequest request);

	public List<Direccion> findListFromUsuario(int idDatosPers);

	public void delete(int id, HttpServletRequest request);

	public Direccion findByIdModel(int id);

	public List<Direccion> findListFromUsuarioModel(int idDatosPers);

	public int getMaxId();

}
