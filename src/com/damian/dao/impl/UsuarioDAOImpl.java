package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterUsuario;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelUsuario;
import com.damian.pojo.Usuario;
import com.damian.service.DatosPersonalesService;
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
	public List<Usuario> findAll(String column, String order, int paginaInicio, int totalPaginas,
			HttpServletRequest request) {

		boolean dp = false;
		List<Usuario> usuarios = new ArrayList<>();

		if (column != null && column.length() > 15 && column.substring(0, 15).equals("datosPersonales")) {
			dp = true;
		}

		String columnAndOrder = Utils.validateColumnAndOrder(column, null, TABLA, KEY_COLUMN, KEY_ORDER, COLUMN_ORDER,
				request, this, usuarioOrdenDAO);

		if (columnAndOrder != null) {
			String sql = "SELECT * FROM " + TABLA + " ORDER BY " + columnAndOrder + " LIMIT " + paginaInicio + ","
					+ totalPaginas;

			int ubicacionOrden = sql.indexOf("BY usuario") + 11;
			String orderSql = sql.substring(ubicacionOrden, ubicacionOrden + 3);
			if (order != null && !order.equals("null")) {
				sql = sql.substring(0, ubicacionOrden) + order+ " LIMIT " + paginaInicio + ","
						+ totalPaginas;
			}

			usuarios = lista(sql);

			if (dp) {
				if (orderSql.equals("ASC")) {
					switch (column.substring(15)) {
					case "nombre":
						ordenarPorNombreASC(usuarios);
						break;
					case "apellido1":
						ordenarPorApellido1ASC(usuarios);
						break;
					case "fechaNacimiento":
						ordenarPorFechaNacimientoASC(usuarios);
						break;
					case "email":
						ordenarPorEmailASC(usuarios);
						break;
					case "telefono":
						ordenarPorTelefonoASC(usuarios);
						break;
					}
				} else {
					switch (column.substring(15)) {
					case "nombre":
						ordenarPorNombreDESC(usuarios);
						break;
					case "apellido1":
						ordenarPorApellido1DESC(usuarios);
						break;
					case "fechaNacimiento":
						ordenarPorFechaNacimientoDESC(usuarios);
						break;
					case "email":
						ordenarPorEmailDESC(usuarios);
						break;
					case "telefono":
						ordenarPorTelefonoDESC(usuarios);
						break;
					}
				}
			}

			return usuarios;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public void save(Usuario usuario) {
		if (usuario.getIdUsr() > 0) {
			update(usuario);
		} else {
			ModelUsuario mu = converterUsuario.convert(usuario);
			String sql = "INSERT INTO " + TABLA + " (usuario, clave, habilitado, fechaCreacion)"
					+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion());
		}
	}

	@Override
	public void update(Usuario usuario) {
		ModelUsuario mu = converterUsuario.convert(usuario);
		String sql = "UPDATE " + TABLA + " SET usuario=?, clave=?, habilitado=?, fechaCreacion=? WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mu.getUsuario(), mu.getClave(), mu.isHabilitado(), mu.getFechaCreacion(),
				mu.getIdUsr());
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
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
	public List<Usuario> findSearchAll() {
		List<ModelUsuario> muList = jdbcTemplate.query("SELECT idUsr, usuario FROM " + TABLA, BeanPropertyRowMapper.newInstance(ModelUsuario.class));
		List<Usuario> uList = new ArrayList<>();
		Usuario usuario = null;
		for (ModelUsuario mu : muList) {
			usuario = converterUsuario.convert(mu);
			usuario.setDatosPersonales(datosPersonalesService.findByUsrIdSearch(mu.getIdUsr()));
			uList.add(usuario);
		}
		return uList;
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
		return mu;
	}

	private void ordenarPorNombreASC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u1.getDatosPersonales().getNombre())
						.compareToIgnoreCase(new String(u2.getDatosPersonales().getNombre()));
			}
		});
	}

	private void ordenarPorApellido1ASC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u1.getDatosPersonales().getApellido1())
						.compareToIgnoreCase(new String(u2.getDatosPersonales().getApellido1()));
			}
		});
	}

	private void ordenarPorFechaNacimientoASC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				if (u2.getDatosPersonales().getFechaNacimiento() == null) {
					return 1;
				} else if (u1.getDatosPersonales().getFechaNacimiento() == null || u1.getDatosPersonales()
						.getFechaNacimiento().before(u2.getDatosPersonales().getFechaNacimiento())) {
					return -1;
				} else if (u1.getDatosPersonales().getFechaNacimiento()
						.after(u2.getDatosPersonales().getFechaNacimiento())) {
					return 1;
				} else {
					return 0;
				}
			}
		});
	}

	private void ordenarPorEmailASC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u1.getDatosPersonales().getEmail())
						.compareToIgnoreCase(new String(u2.getDatosPersonales().getEmail()));
			}
		});
	}

	private void ordenarPorTelefonoASC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u1.getDatosPersonales().getTelefono())
						.compareToIgnoreCase(new String(u2.getDatosPersonales().getTelefono()));
			}
		});
	}

	private void ordenarPorNombreDESC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u2.getDatosPersonales().getNombre())
						.compareToIgnoreCase(new String(u1.getDatosPersonales().getNombre()));
			}
		});
	}

	private void ordenarPorApellido1DESC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u2.getDatosPersonales().getApellido1())
						.compareToIgnoreCase(new String(u1.getDatosPersonales().getApellido1()));
			}
		});
	}

	private void ordenarPorFechaNacimientoDESC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				if (u2.getDatosPersonales().getFechaNacimiento() == null) {
					return -1;
				} else if (u1.getDatosPersonales().getFechaNacimiento() == null || u1.getDatosPersonales()
						.getFechaNacimiento().before(u2.getDatosPersonales().getFechaNacimiento())) {
					return 1;
				} else if (u1.getDatosPersonales().getFechaNacimiento()
						.after(u2.getDatosPersonales().getFechaNacimiento())) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}

	private void ordenarPorEmailDESC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u2.getDatosPersonales().getEmail())
						.compareToIgnoreCase(new String(u1.getDatosPersonales().getEmail()));
			}
		});
	}

	private void ordenarPorTelefonoDESC(List<Usuario> usuarios) {
		Collections.sort(usuarios, new Comparator<Usuario>() {

			@Override
			public int compare(Usuario u1, Usuario u2) {
				return new String(u2.getDatosPersonales().getTelefono())
						.compareToIgnoreCase(new String(u1.getDatosPersonales().getTelefono()));
			}
		});
	}

}
