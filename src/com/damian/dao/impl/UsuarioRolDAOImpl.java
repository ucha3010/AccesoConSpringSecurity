package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterUsuarioRol;
import com.damian.dao.UsuarioRolDAO;
import com.damian.dao.model.ModelUsuarioRol;
import com.damian.pojo.UsuarioRol;

@Repository
public class UsuarioRolDAOImpl implements UsuarioRolDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioRolDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "usuario_rol";
	private final String KEY1 = "idUsr";
	private final String KEY2 = "idRol";

	@Override
	public List<UsuarioRol> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(UsuarioRol usuarioRol) {
		ModelUsuarioRol mur = ConverterUsuarioRol.convert(usuarioRol);
		String sql = "INSERT INTO " + TABLA + " (idUsr, idRol, fechaCreacion, creadoPor)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, mur.getIdUsr(), mur.getIdRol(), mur.getFechaCreacion(), mur.getCreadoPor());
	}

	@Override
	public void update(UsuarioRol usuarioRol) {
		ModelUsuarioRol mur = ConverterUsuarioRol.convert(usuarioRol);
		String sql = "UPDATE " + TABLA + "SET fechaCreacion=?, creadoPor=? " + "WHERE " + KEY1 + "=? AND " + KEY2
				+ "=?";
		jdbcTemplate.update(sql, mur.getFechaCreacion(), mur.getCreadoPor(), mur.getIdUsr(), mur.getIdRol());
	}

	@Override
	public void delete(UsuarioRol usuarioRol) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, usuarioRol.getUsuario().getIdUsr(), usuarioRol.getRol().getIdRol());
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
			urList.add(ConverterUsuarioRol.convert(mur));
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

}
