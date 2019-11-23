package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.damian.converter.ConverterUsuarioEmpresa;
import com.damian.dao.UsuarioEmpresaDAO;
import com.damian.dao.model.ModelUsuarioEmpresa;
import com.damian.pojo.UsuarioEmpresa;

public class UsuarioEmpresaDAOImpl implements UsuarioEmpresaDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioEmpresaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "usuario_empresa";
	private final String KEY1 = "idUsr";
	private final String KEY2 = "idEmp";

	@Override
	public List<UsuarioEmpresa> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(UsuarioEmpresa usuarioEmpresa) {
		ModelUsuarioEmpresa mue = ConverterUsuarioEmpresa.convert(usuarioEmpresa);
		String sql = "INSERT INTO " + TABLA + " (idUsr, idEmp, fechaCreacion, creadoPor)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, mue.getIdUsr(), mue.getIdEmp(), mue.getFechaCreacion(), mue.getCreadoPor());
	}

	@Override
	public void update(UsuarioEmpresa usuarioEmpresa) {
		ModelUsuarioEmpresa mue = ConverterUsuarioEmpresa.convert(usuarioEmpresa);
		String sql = "UPDATE " + TABLA + "SET fechaCreacion=?, creadoPor=? " + "WHERE " + KEY1 + "=? AND " + KEY2
				+ "=?";
		jdbcTemplate.update(sql, mue.getFechaCreacion(), mue.getCreadoPor(), mue.getIdUsr(), mue.getIdEmp());
	}

	@Override
	public void delete(UsuarioEmpresa usuarioEmpresa) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, usuarioEmpresa.getUsuario().getIdUsr(), usuarioEmpresa.getEmpresa().getIdEmp());
	}

	@Override
	public List<UsuarioEmpresa> findByIdUsr(int idUsr) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idUsr=" + idUsr;

		return lista(sql);

	}

	@Override
	public List<UsuarioEmpresa> findByIdEmp(int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;

		return lista(sql);
	}

	private List<UsuarioEmpresa> lista(String sql) {
		List<ModelUsuarioEmpresa> mueList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelUsuarioEmpresa.class));
		List<UsuarioEmpresa> ueList = new ArrayList<>();
		for (ModelUsuarioEmpresa mue : mueList) {
			ueList.add(ConverterUsuarioEmpresa.convert(mue));
		}
		return ueList;
	}

	private ModelUsuarioEmpresa mapeo(ResultSet rs) throws SQLException {
		ModelUsuarioEmpresa mue = new ModelUsuarioEmpresa();
		mue.setIdUsr(rs.getInt("idUsr"));
		mue.setIdEmp(rs.getInt("idEmp"));
		mue.setFechaCreacion(rs.getDate("fechaCreacion"));
		mue.setCreadoPor(rs.getString("creadoPor"));
		return mue;
	}

}
