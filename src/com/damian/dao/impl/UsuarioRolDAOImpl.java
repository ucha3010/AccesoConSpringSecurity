package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterUsuarioRol;
import com.damian.dao.RolDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioRolDAO;
import com.damian.dao.model.ModelUsuarioRol;
import com.damian.pojo.UsuarioRol;
import com.damian.utils.LocalLogger;

@Repository
public class UsuarioRolDAOImpl implements UsuarioRolDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioRolDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "usuario_rol";
	private final String KEY1 = "idUsr";
	private final String KEY2 = "idRol";

	@Autowired
	private ConverterUsuarioRol converterUsuarioRol;

	@Autowired
	private RolDAO rolDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public List<UsuarioRol> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(UsuarioRol usuarioRol, HttpServletRequest request) {
		ModelUsuarioRol mur = converterUsuarioRol.convert(usuarioRol);
		String sql = "INSERT INTO " + TABLA + " (idUsr, idRol, fechaCreacion, creadoPor)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, mur.getIdUsr(), mur.getIdRol(), mur.getFechaCreacion(), mur.getCreadoPor());
		LocalLogger.save(TABLA, usuarioRol, request);
	}

	@Override
	public void update(UsuarioRol usuarioRol, HttpServletRequest request) {
		ModelUsuarioRol mur = converterUsuarioRol.convert(usuarioRol);
		String sql = "UPDATE " + TABLA + " SET fechaCreacion=?, creadoPor=? " + "WHERE " + KEY1 + "=? AND " + KEY2
				+ "=?";
		jdbcTemplate.update(sql, mur.getFechaCreacion(), mur.getCreadoPor(), mur.getIdUsr(), mur.getIdRol());
		LocalLogger.save(TABLA, usuarioRol, request);
	}

	@Override
	public void delete(UsuarioRol usuarioRol, HttpServletRequest request) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, usuarioRol.getUsuario().getIdUsr(), usuarioRol.getRol().getIdRol());
		LocalLogger.delete(TABLA, usuarioRol, request);
	}

	@Override
	public List<UsuarioRol> findByIdUsr(int idUsr) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idUsr=" + idUsr;

		return lista(sql);

	}

	@Override
	public List<UsuarioRol> findByIdRol(int idRol) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idRol=" + idRol;

		return lista(sql);

	}

	private List<UsuarioRol> lista(String sql) {
		List<ModelUsuarioRol> murList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelUsuarioRol.class));
		List<UsuarioRol> urList = new ArrayList<>();
		for (ModelUsuarioRol mur : murList) {
			urList.add(converterUsuarioRol.convertAll(mur));
		}
		return urList;
	}

	private ModelUsuarioRol mapeo(ResultSet rs) throws SQLException {
		ModelUsuarioRol mue = new ModelUsuarioRol();
		mue.setIdUsr(rs.getInt("idUsr"));
		mue.setIdRol(rs.getInt("idRol"));
		mue.setFechaCreacion(rs.getDate("fechaCreacion"));
		mue.setCreadoPor(rs.getString("creadoPor"));
		return mue;
	}

	@Override
	public List<UsuarioRol> findByIdUsrModel(int idUsr) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idUsr=" + idUsr;

		return listaModel(sql);
	}

	@Override
	public List<UsuarioRol> findByIdRolModel(int idRol) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idRol=" + idRol;

		return listaModel(sql);
	}

	@Override
	public UsuarioRol findByIdUsrAndIdRol(int idUsr, int idRol) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idUsr=" + idUsr + " AND idRol=" + idRol;
		return jdbcTemplate.query(sql, new ResultSetExtractor<UsuarioRol>() {

			@Override
			public UsuarioRol extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterUsuarioRol.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	private List<UsuarioRol> listaModel(String sql) {
		List<ModelUsuarioRol> murList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelUsuarioRol.class));
		List<UsuarioRol> urList = new ArrayList<>();
		for (ModelUsuarioRol mur : murList) {
			UsuarioRol ur = converterUsuarioRol.convert(mur);
			ur.setUsuario(usuarioDAO.findByIdModel(mur.getIdUsr()));
			ur.setRol(rolDAO.findByIdModel(mur.getIdRol()));
			urList.add(ur);
		}
		return urList;
	}

}
