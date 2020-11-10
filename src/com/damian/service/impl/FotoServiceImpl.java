package com.damian.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.damian.dao.FotoDAO;
import com.damian.pojo.Foto;
import com.damian.service.FotoService;
import com.damian.utils.Ruta;
import com.damian.utils.Utils;

@Service
public class FotoServiceImpl implements FotoService {

	@Autowired
	private FotoDAO fotoDAO;

	@Override
	public Foto findByIdFot(int idFot) {
		return fotoDAO.findByIdFot(idFot);
	}

	@Override
	public List<Foto> findByIdUsr(int idUsr) {
		return fotoDAO.findByIdUsr(idUsr);
	}

	@Override
	public List<Foto> findByIdPro(int idPro) {
		return fotoDAO.findByIdPro(idPro);
	}

	@Override
	public List<Foto> findByIdEmp(int idEmp) {
		return fotoDAO.findByIdEmp(idEmp);
	}

	@Override
	public List<Foto> findByIdCat(int idCat) {
		return fotoDAO.findByIdCat(idCat);
	}

	@Override
	public List<Foto> findByIdSub(int idSub) {
		return fotoDAO.findByIdSub(idSub);
	}

	@Override
	public List<Foto> findByIdPais(int idPais) {
		return fotoDAO.findByIdPais(idPais);
	}

	@Override
	public List<Foto> findByIdFor(int idFor) {
		return fotoDAO.findByIdFor(idFor);
	}

	@Override
	public List<Foto> findByIdEst(int idEst) {
		return fotoDAO.findByIdEst(idEst);
	}

	@Override
	public List<Foto> findByIdRol(int idRol) {
		return fotoDAO.findByIdRol(idRol);
	}

	@Override
	public List<Foto> findBySlide() {
		return fotoDAO.findBySlide();
	}

	@Override
	public int save(Foto foto, MultipartFile file, HttpServletRequest request) {

		List<Foto> fotos = new ArrayList<>();
		String llamada = null;
		int id = 0;
		if (foto != null) {
			if (foto.getUsuario() != null && foto.getUsuario().getIdUsr() != 0) {
				fotos = findByIdUsr(foto.getUsuario().getIdUsr());
				llamada = "usuarios";
				id = foto.getUsuario().getIdUsr();
			}
			if (foto.getProducto() != null && foto.getProducto().getIdPro() != 0) {
				id = foto.getProducto().getIdPro();
				fotos = findByIdPro(id);
				llamada = "productos";
			}
			if (foto.isSlide()) {
				llamada = "slide";
			}
		}
		if (fotos.isEmpty() || fotos.size() < 4) {

			Ruta ruta = Ruta(llamada, id, request);

			try {
				byte[] bytes = file.getBytes();

				File dir = new File(ruta.getRutaAbsoluta());
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// Create the file on server
				File serverFile = new File(
						dir.getAbsolutePath() + System.getProperty("file.separator") + file.getOriginalFilename());
				if (serverFile.exists()) {
					// TODO DAMIAN hacer una excepción mejor para acá
					throw new Exception();
				}
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			} catch (Exception e) {
				// TODO DAMIAN devolver un mensaje acá
				return 0;
			}
			if (fotos != null && foto.isPrincipal()) {
				doAllNotPrincipal(fotos, request);
			}
			foto.setNombre(file.getOriginalFilename());
			foto.setPeso(file.getSize());
			foto.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
			foto.setCreadoPor(Utils.getLoggedUser(request));
			foto.setRuta(ruta.getRutaRelativa());
			foto.setModificadoPor(Utils.getLoggedUser(request));
			foto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
			foto.setExtension(getExtension(file.getOriginalFilename()));

			return fotoDAO.save(foto, request);
		} else {
			return 0;
		}
	}

	@Override
	public int update(Foto foto, HttpServletRequest request) {

		foto.setModificadoPor(Utils.getLoggedUser(request));
		foto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		return fotoDAO.update(foto, request);
	}

	@Override
	public Foto delete(int id, HttpServletRequest request) {

		Foto foto = findByIdFot(id);
		Ruta ruta = null;
		int idSalida = 0;
		if (foto != null) {
			if (foto.getUsuario() != null && foto.getUsuario().getIdUsr() != 0) {
				idSalida = foto.getUsuario().getIdUsr();
				ruta = Ruta("usuarios", idSalida, request);
			}
			if (foto.getProducto() != null && foto.getProducto().getIdPro() != 0) {
				idSalida = foto.getProducto().getIdPro();
				ruta = Ruta("productos", idSalida, request);
			}
			if (foto.isSlide()) {
				ruta = Ruta("slide", idSalida, request);
			}
		}
		if (ruta != null) {
			File file = new File(ruta.getRutaAbsoluta() + System.getProperty("file.separator") + foto.getNombre());
			file.delete();
			fotoDAO.delete(id, request);
		}
		return foto;
	}

	@Override
	public int getMaxId() {
		return fotoDAO.getMaxId();
	}

	@Override
	public Foto doPrincipal(int idFot, HttpServletRequest request) {
		Foto foto = findByIdFot(idFot);
		List<Foto> fotos = new ArrayList<>();
		if (foto != null) {
			if (foto.getUsuario() != null && foto.getUsuario().getIdUsr() != 0) {
				fotos = findByIdUsr(foto.getUsuario().getIdUsr());
			}
			if (foto.getProducto() != null && foto.getProducto().getIdPro() != 0) {
				fotos = findByIdPro(foto.getProducto().getIdPro());
			}
		}
		doAllNotPrincipal(fotos, request);
		foto.setPrincipal(true);
		update(foto, request);
		return foto;
	}

	@Override
	public String principalPictureName(List<Foto> fotos) {
		String nombre = null;
		for (Foto f : fotos) {
			if (f.isPrincipal()) {
				nombre = f.getNombre();
				break;
			}
		}
		return nombre;
	}

	private Ruta Ruta(String llamante, int id, HttpServletRequest request) {

		Ruta ruta = new Ruta();

		ruta.setRutaRelativa(System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
				+ "imgs" + System.getProperty("file.separator") + llamante + System.getProperty("file.separator") + id);
		ruta.setRutaAbsoluta(Utils.rutaHastaWebContent(request) + ruta.getRutaRelativa());
		return ruta;
	}

	private void doAllNotPrincipal(List<Foto> fotos, HttpServletRequest request) {
		for (Foto f : fotos) {
			if (f.isPrincipal()) {
				f.setPrincipal(false);
				update(f, request);
			}
		}
	}

	private String getExtension(String originalFilename) {

		String[] ext = originalFilename.split("\\.");
		if (ext.length > 1) {
			return ext[ext.length - 1];
		}
		return null;
	}
}
