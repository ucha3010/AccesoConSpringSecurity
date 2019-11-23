package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.damian.converter.ConverterUsuario;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "usuario";
	private final String KEY = "idUsr";

	@Override
	public Usuario findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {

			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterUsuario.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Usuario> findByIdList(int id) {

		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(findById(id));
		return usuarios;
	}

	@Override
	public Usuario findByUsername(String usuario) {

		String sql = "SELECT * FROM " + TABLA + " WHERE usuario LIKE '" + usuario + "'";

		List<Usuario> usuarios = lista(sql);
		if (usuarios != null && usuarios.size() > 0) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Usuario> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(Usuario usuario) {
		if (usuario.getIdUsr() > 0) {
			update(usuario);
		} else {
			ModelUsuario mu = ConverterUsuario.convert(usuario);
			String sql = "INSERT INTO " + TABLA + " (usuario, clave, habilitado, fechaCreacion)"
					+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion());
		}
	}

	@Override
	public void update(Usuario usuario) {
		ModelUsuario mu = ConverterUsuario.convert(usuario);
		String sql = "UPDATE " + TABLA + "SET usuario=?, clave=?, habilitado=?, fechaCreacion=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion(),
				mu.getIdUsr());
	}

	@Override
	public void delete(Usuario usuario) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, usuario.getIdUsr());
	}

	private List<Usuario> lista(String sql) {
		List<ModelUsuario> muList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelUsuario.class));
		List<Usuario> uList = new ArrayList<>();
		for (ModelUsuario mu : muList) {
			uList.add(ConverterUsuario.convert(mu));
		}
		return uList;
	}

	private ModelUsuario mapeo(ResultSet rs) throws SQLException {
		ModelUsuario mu = new ModelUsuario();
		mu.setIdUsr(rs.getInt("idUsr"));
		mu.setUsuario(rs.getString("usuario"));
		mu.setClave(rs.getString("clave"));
		mu.setHabilitado(rs.getBoolean("habilitado"));
		mu.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
		return mu;
	}

}
