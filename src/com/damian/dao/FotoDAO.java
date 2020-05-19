package com.damian.dao;

import java.util.List;

import com.damian.pojo.Foto;

public interface FotoDAO {

	public Foto findByIdFot(int idFot);

	public List<Foto> findByIdUsr(int idUsr);

	public List<Foto> findByIdPro(int idPro);

	public List<Foto> findByIdEmp(int idEmp);

	public List<Foto> findByIdCat(int idCat);

	public List<Foto> findByIdSub(int idSub);

	public List<Foto> findByIdPais(int idPais);

	public List<Foto> findByIdFor(int idFor);

	public List<Foto> findByIdEst(int idEst);

	public List<Foto> findByIdRol(int idRol);

	public int save(Foto foto);

	public int update(Foto foto);

	public int delete(int id);

	public int getMaxId();

}
