package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterRellenaObjeto;
import com.damian.converter.ConverterUsuario;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelDatosPersonales;
import com.damian.dao.model.ModelUsuario;
import com.damian.mapper.MapperDatosPersonales;
import com.damian.mapper.MapperUsuario;
import com.damian.pojo.Usuario;
import com.damian.service.DatosPersonalesService;
import com.damian.utils.LocalLogger;
import com.damian.utils.Utils;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	private JdbcTemplate jdbcTemplate;

	public UsuarioDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "usuario";
	private final String KEY = "idUsr";
	private final String KEY_COLUMN = "usuario";
	private final String KEY_ORDER = "ASC";
	private final String COLUMN_ORDER = "usuario ASC";

	@Autowired
	private ConverterRellenaObjeto converterRellenaObjeto;

	@Autowired
	private ConverterUsuario converterUsuario;

	@Autowired
	private DatosPersonalesService datosPersonalesService;

	@Autowired
	private UsuarioOrdenDAO usuarioOrdenDAO;

	@Override
	public Usuario findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {

			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterUsuario.convertAll(mapeo(rs));
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
	public List<Usuario> findAll(boolean customer, String column, String order, int paginaInicio, int totalPaginas,
			HttpServletRequest request) {

		String columnAndOrder = Utils.validateColumnAndOrder(column, order, TABLA, KEY_COLUMN, KEY_ORDER, COLUMN_ORDER,
				request, this, usuarioOrdenDAO);

		if (columnAndOrder != null) {

			String sql = null;
			if (customer) {
				sql = "SELECT usuario.idUsr, usuario.usuario, usuario.habilitado, usuario.fechaCreacion, datospersonales.idDatosPers, datospersonales.nombre, "
						+ "datospersonales.apellido1, datospersonales.apellido2, datospersonales.sexo, datospersonales.fechaNacimiento, datospersonales.nacionalidad_idPais, "
						+ "datospersonales.dni, datospersonales.email, datospersonales.telefono, datospersonales.observaciones, datospersonales.datospersonales_idUsr "
						+ "FROM usuario, datospersonales, usuario_rol WHERE usuario.idUsr = datospersonales.datospersonales_idUsr AND usuario.idUsr = usuario_rol.idUsr "
						+ "AND usuario_rol.idRol = 1 ORDER BY " + columnAndOrder + " LIMIT " + paginaInicio + ","
						+ totalPaginas;
			} else {
				sql = "SELECT * FROM usuario JOIN datospersonales ON usuario.idUsr = datospersonales.datospersonales_idUsr ORDER BY "
						+ columnAndOrder + " LIMIT " + paginaInicio + "," + totalPaginas;
			}

			List<Map<String, Object>> listJoin = jdbcTemplate.queryForList(sql);

			List<Usuario> usuarios = new ArrayList<>();

			for (Map<String, Object> user : listJoin) {
				ModelUsuario mu = MapperUsuario.mapeo(user);
				ModelDatosPersonales mdp = MapperDatosPersonales.mapeo(user);
				Usuario u = converterRellenaObjeto.rellenaUsuarioLista(mu, mdp);
				usuarios.add(u);
			}

			return usuarios;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public void save(Usuario usuario, HttpServletRequest request) {
		if (usuario.getIdUsr() > 0) {
			update(usuario, request);
		} else {
			ModelUsuario mu = converterUsuario.convert(usuario);
			String sql = "INSERT INTO " + TABLA
					+ " (usuario, clave, habilitado, fechaCreacion, modificadoPor, fechaModificacion)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion(),
					mu.getModificadoPor(), mu.getFechaModificacion());
			LocalLogger.save(TABLA, mu, request);
		}
	}

	@Override
	public void update(Usuario usuario, HttpServletRequest request) {
		ModelUsuario mu = converterUsuario.convert(usuario);
		String sql = "UPDATE " + TABLA
				+ " SET usuario=?, clave=?, habilitado=?, fechaCreacion=?, modificadoPor=?, fechaModificacion=? WHERE "
				+ KEY + "=?";
		jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion(),
				mu.getModificadoPor(), mu.getFechaModificacion(), mu.getIdUsr());
		LocalLogger.update(TABLA, mu, request);
	}

	@Override
	public void delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public Usuario findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Usuario>() {

			@Override
			public Usuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterUsuario.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	@Override
	public List<Usuario> findSearchAll(boolean customer) {
		String sql = null;
		if (customer) {
			sql = "SELECT usuario.idUsr, usuario.usuario FROM usuario, usuario_rol WHERE usuario.idUsr = usuario_rol.idUsr AND usuario_rol.idRol = 1";
		} else {
			sql = "SELECT idUsr, usuario FROM " + TABLA;
		}
		List<ModelUsuario> muList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelUsuario.class));
		List<Usuario> uList = new ArrayList<>();
		Usuario usuario = null;
		for (ModelUsuario mu : muList) {
			usuario = converterUsuario.convert(mu);
			usuario.setDatosPersonales(datosPersonalesService.findByUsrIdSearch(mu.getIdUsr()));
			uList.add(usuario);
		}
		return uList;
	}

	@Override
	public int countCustomers() {

		String 	sql = "SELECT COUNT(usuario.idUsr) FROM usuario, usuario_rol "
				+ "WHERE  usuario.idUsr = usuario_rol.idUsr AND usuario_rol.idRol = 1";

		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	private List<Usuario> lista(String sql) {
		List<ModelUsuario> muList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelUsuario.class));
		List<Usuario> uList = new ArrayList<>();
		for (ModelUsuario mu : muList) {
			uList.add(converterUsuario.convertAll(mu));
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
		mu.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		mu.setModificadoPor(rs.getString("modificadoPor"));
		return mu;
	}

}
