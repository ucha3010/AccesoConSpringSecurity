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

import com.damian.converter.ConverterEmpresa;
import com.damian.dao.EmpresaDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelEmpresa;
import com.damian.pojo.Empresa;
import com.damian.utils.Utils;

@Repository
public class EmpresaDAOImpl implements EmpresaDAO {

	private JdbcTemplate jdbcTemplate;

	public EmpresaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "empresa";
	private final String KEY = "idEmp";
	private final String KEY_COLUMN = "nombreComercial";
	private final String KEY_ORDER = "ASC";
	private final String COLUMN_ORDER = "nombreComercial ASC";

	@Autowired
	private ConverterEmpresa converterEmpresa;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioOrdenDAO usuarioOrdenDAO;

	@Override
	public Empresa findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Empresa>() {

			@Override
			public Empresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterEmpresa.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public List<Empresa> findByIdList(int id) {
		List<Empresa> empresas = new ArrayList<>();
		empresas.add(findById(id));
		return empresas;
	}

	@Override
	public List<Empresa> findAll(String column, HttpServletRequest request) {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY " + Utils.validateColumnAndOrder(column, null, TABLA,
				KEY_COLUMN, KEY_ORDER, COLUMN_ORDER, request, usuarioDAO, usuarioOrdenDAO);

		return lista(sql);
	}

	@Override
	public void save(Empresa empresa) {
		if (empresa.getIdEmp() > 0) {
			update(empresa);
		} else {
			ModelEmpresa me = converterEmpresa.convert(empresa);
			String sql = "INSERT INTO " + TABLA + " (nombreComercial, tipoSociedad, actividad, cif, email, paginaWeb, "
					+ "telefono, fax, observaciones)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, me.getNombreComercial(), me.getTipoSociedad(), me.getActividad(), me.getCif(),
					me.getEmail(), me.getPaginaWeb(), me.getTelefono(), me.getFax(), me.getObservaciones());
		}
	}

	@Override
	public void update(Empresa empresa) {
		ModelEmpresa me = converterEmpresa.convert(empresa);
		String sql = "UPDATE " + TABLA + " SET nombreComercial=?, tipoSociedad=?, actividad=?, "
				+ "cif=?, email=?, paginaWeb=?, " + "telefono=?, fax=?, observaciones=? " + "WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, me.getNombreComercial(), me.getTipoSociedad(), me.getActividad(), me.getCif(),
				me.getEmail(), me.getPaginaWeb(), me.getTelefono(), me.getFax(), me.getObservaciones(), me.getIdEmp());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Empresa> findByEmpresaName(String empresaName) {

		String sql = "SELECT * FROM " + TABLA + " WHERE nombreComercial LIKE '" + empresaName + "'";

		return lista(sql);
	}

	private List<Empresa> lista(String sql) {
		List<ModelEmpresa> meList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelEmpresa.class));
		List<Empresa> eList = new ArrayList<>();
		for (ModelEmpresa me : meList) {
			eList.add(converterEmpresa.convertAll(me));
		}
		return eList;
	}

	private ModelEmpresa mapeo(ResultSet rs) throws SQLException {
		ModelEmpresa me = new ModelEmpresa();
		me.setIdEmp(rs.getInt("idEmp"));
		me.setNombreComercial(rs.getString("nombreComercial"));
		me.setTipoSociedad(rs.getString("tipoSociedad"));
		me.setActividad(rs.getString("actividad"));
		me.setCif(rs.getString("cif"));
		me.setEmail(rs.getString("email"));
		me.setPaginaWeb(rs.getString("paginaWeb"));
		me.setTelefono(rs.getString("telefono"));
		me.setFax(rs.getString("fax"));
		me.setObservaciones(rs.getString("observaciones"));
		return me;
	}

	@Override
	public Empresa findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Empresa>() {

			@Override
			public Empresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterEmpresa.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}
}
