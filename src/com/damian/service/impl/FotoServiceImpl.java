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
			org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
					.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			foto.setCreadoPor(context.getAuthentication().getName());
			foto.setRuta(ruta.getRutaRelativa());
			foto.setModificadoPor(context.getAuthentication().getPrincipal().toString());
			foto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));

			return fotoDAO.save(foto);
		} else {
			return 0;
		}
	}

	@Override
	public int update(Foto foto, HttpServletRequest request) {
		org.springframework.security.core.context.SecurityContextImpl context = (org.springframework.security.core.context.SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		foto.setModificadoPor(context.getAuthentication().getPrincipal().toString());
		foto.setFechaModificacion(new Timestamp(System.currentTimeMillis()));
		return fotoDAO.update(foto);
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
		}
		if (ruta != null) {
			File file = new File(ruta.getRutaAbsoluta() + System.getProperty("file.separator") + foto.getNombre());
			file.delete();
			fotoDAO.delete(id);
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

		// TODO DAMIAN por algún motivo el comando System.getProperty("user.dir") me
		// está devolviendo la ruta donde está instalado el Eclipse en lugar
		// de devolver la ruta de workspace (según leí, esa es la ruta que debería
		// devolver). Con lo cual utilizo esto de abajo.
		String rutaWorkspace = System.getProperty("catalina.base");
		int finWorkspace = rutaWorkspace.indexOf(".metadata");
		// Ruta hasta el workspace
		rutaWorkspace = rutaWorkspace.substring(0, finWorkspace);
		// Ruta hasta el proyecto
		rutaWorkspace = rutaWorkspace + request.getContextPath().substring(1);
		// Ruta dentro del proyecto
		ruta.setRutaRelativa(System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
				+ "imgs" + System.getProperty("file.separator") + llamante + System.getProperty("file.separator") + id);
		ruta.setRutaAbsoluta(
				rutaWorkspace + System.getProperty("file.separator") + "WebContent" + ruta.getRutaRelativa());
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
}
