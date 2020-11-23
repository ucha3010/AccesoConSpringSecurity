package com.damian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.damian.pojo.Foto;

public interface FotoService {

	public Foto findByIdFot(int idFot);

	public List<Foto> findByIdUsr(int idUsr);

	public List<Foto> findByIdPro(int idPro);

	public List<Foto> findByIdEmp(int idEmp);

	public List<Foto> findByIdPropia(int idPropia);

	public List<Foto> findByIdCat(int idCat);

	public List<Foto> findByIdSub(int idSub);

	public List<Foto> findByIdPais(int idPais);

	public List<Foto> findByIdFor(int idFor);

	public List<Foto> findByIdEst(int idEst);

	public List<Foto> findByIdRol(int idRol);

	public List<Foto> findByIdMar(int idMar);

	public List<Foto> findBySlide();

	public int save(Foto foto, MultipartFile file, HttpServletRequest request);

	public int update(Foto foto, HttpServletRequest request);

	public Foto delete(int id, HttpServletRequest request);

	public int getMaxId();

	public Foto doPrincipal(int idFot, HttpServletRequest request);

	public String principalPictureName(List<Foto> fotos);

}
