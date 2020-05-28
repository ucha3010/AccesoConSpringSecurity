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

import com.damian.converter.ConverterFacturaEstado;
import com.damian.dao.EstadoDAO;
import com.damian.dao.FacturaDAO;
import com.damian.dao.FacturaEstadoDAO;
import com.damian.dao.model.ModelFacturaEstado;
import com.damian.pojo.FacturaEstado;
import com.damian.utils.LocalLogger;

@Repository
public class FacturaEstadoDAOImpl implements FacturaEstadoDAO {

	private JdbcTemplate jdbcTemplate;

	public FacturaEstadoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "factura_estado";
	private final String KEY = "id";

	@Autowired
	private ConverterFacturaEstado converterFacturaEstado;

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private EstadoDAO estadoDAO;

	@Override
	public List<FacturaEstado> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public FacturaEstado findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<FacturaEstado>() {

			@Override
			public FacturaEstado extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFacturaEstado.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public void save(FacturaEstado estadoFactura, HttpServletRequest request) {
		ModelFacturaEstado mfe = converterFacturaEstado.convert(estadoFactura);
		String sql = "INSERT INTO " + TABLA + " (idEst, idFac, fecha, creadoPor, observaciones)"
				+ " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mfe.getIdEst(), mfe.getIdFac(), mfe.getFecha(), mfe.getCreadoPor(),
				mfe.getObservaciones());
	}

	@Override
	public void update(FacturaEstado estadoFactura, HttpServletRequest request) {
		ModelFacturaEstado mfe = converterFacturaEstado.convert(estadoFactura);
		String sql = "UPDATE " + TABLA + " SET idEst=?, idFac=?, fecha=?, creadoPor=?, observaciones=? " + "WHERE "
				+ KEY + "=?";
		jdbcTemplate.update(sql, mfe.getIdEst(), mfe.getIdFac(), mfe.getFecha(), mfe.getCreadoPor(),
				mfe.getObservaciones(), mfe.getId());
	}

	@Override
	public void delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public List<FacturaEstado> findByIdEst(int idEst) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEst=" + idEst;

		return lista(sql);

	}

	@Override
	public List<FacturaEstado> findByIdFac(int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idFac=" + idFac + " ORDER BY fecha DESC";

		return lista(sql);
	}

	@Override
	public List<FacturaEstado> findByIdEstModel(int idEst) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEst=" + idEst;

		return listaModel(sql);
	}

	@Override
	public List<FacturaEstado> findByIdFacModel(int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idFac=" + idFac;

		return listaModel(sql);
	}

	@Override
	public List<FacturaEstado> findByIdEstAndIdFac(int idEst, int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEst=" + idEst + " AND idFac=" + idFac;
		return lista(sql);
	}

	private List<FacturaEstado> lista(String sql) {
		List<ModelFacturaEstado> mfeList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelFacturaEstado.class));
		List<FacturaEstado> feList = new ArrayList<>();
		for (ModelFacturaEstado mfe : mfeList) {
			feList.add(converterFacturaEstado.convertAll(mfe));
		}
		return feList;
	}

	private List<FacturaEstado> listaModel(String sql) {
		List<ModelFacturaEstado> mfeList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelFacturaEstado.class));
		List<FacturaEstado> feList = new ArrayList<>();
		for (ModelFacturaEstado mfe : mfeList) {
			FacturaEstado fe = converterFacturaEstado.convert(mfe);
			fe.setEstado(estadoDAO.findByIdModel(mfe.getIdEst()));
			fe.setFactura(facturaDAO.findByIdModel(mfe.getIdFac()));
			feList.add(fe);
		}
		return feList;
	}

	private ModelFacturaEstado mapeo(ResultSet rs) throws SQLException {
		ModelFacturaEstado mfe = new ModelFacturaEstado();
		mfe.setId(rs.getInt("id"));
		mfe.setIdEst(rs.getInt("idEst"));
		mfe.setIdFac(rs.getInt("idFac"));
		mfe.setFecha(rs.getTimestamp("fecha"));
		mfe.setCreadoPor(rs.getString("creadoPor"));
		mfe.setObservaciones(rs.getString("observaciones"));

		return mfe;
	}

}
