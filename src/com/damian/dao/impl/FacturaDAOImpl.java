package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterFactura;
import com.damian.dao.FacturaDAO;
import com.damian.dao.model.ModelFactura;
import com.damian.pojo.Factura;

@Repository
public class FacturaDAOImpl implements FacturaDAO {

	private JdbcTemplate jdbcTemplate;

	public FacturaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "factura";
	private final String KEY = "idFac";

	@Autowired
	private ConverterFactura converterFactura;

	@Override
	public List<Factura> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY fechaCompra DESC";

		return lista(sql);
	}

	@Override
	public Factura findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Factura>() {

			@Override
			public Factura extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFactura.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public Factura findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Factura>() {

			@Override
			public Factura extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterFactura.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(Factura factura) {
		if (factura.getIdFac() > 0) {
			return update(factura);
		} else {
			ModelFactura mf = converterFactura.convert(factura);
			String sql = "INSERT INTO " + TABLA
					+ " (compra, ivaTotal, descuentoTotal, fechaCompra, fechaEntrega, idEst, direccionEntrega, observaciones, idFor) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			return jdbcTemplate.update(sql, mf.isCompra(), mf.getIvaTotal(), mf.getDescuentoTotal(),
					mf.getFechaCompra(), mf.getFechaEntrega(), mf.getIdEst(), mf.getDireccionEntrega(),
					mf.getObservaciones(), mf.getIdFor());
		}
	}

	@Override
	public int update(Factura factura) {
		ModelFactura mf = converterFactura.convert(factura);
		String sql = "UPDATE " + TABLA
				+ " SET compra=?, ivaTotal=?, descuentoTotal=?, fechaCompra=?, fechaEntrega=?, idEst=?, direccionEntrega=?, observaciones=?, idFor=? "
				+ "WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, mf.isCompra(), mf.getIvaTotal(), mf.getDescuentoTotal(), mf.getFechaCompra(),
				mf.getFechaEntrega(), mf.getIdEst(), mf.getDireccionEntrega(), mf.getObservaciones(), mf.getIdFor(),
				mf.getIdFac());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Factura> findByIdEstModel(int idEst) {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY fechaCompra DESC WHERE idEst = " + idEst;

		return lista(sql);
	}

	@Override
	public List<Factura> findByIdForModel(int idFor) {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY fechaCompra DESC WHERE idFor = " + idFor;

		return lista(sql);
	}

	private List<Factura> lista(String sql) {
		List<ModelFactura> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelFactura.class));
		List<Factura> eList = new ArrayList<>();
		for (ModelFactura mf : mpList) {
			eList.add(converterFactura.convertAll(mf));
		}
		return eList;
	}

	private ModelFactura mapeo(ResultSet rs) throws SQLException {
		ModelFactura mf = new ModelFactura();
		mf.setIdFac(rs.getInt("idFac"));
		mf.setCompra(rs.getBoolean("compra"));
		mf.setIvaTotal(rs.getDouble("ivaTotal"));
		mf.setDescuentoTotal(rs.getDouble("descuentoTotal"));
		mf.setFechaCompra(rs.getDate("fechaCompra"));
		mf.setFechaEntrega(rs.getDate("fechaEntrega"));
		mf.setIdEst(rs.getInt("idEst"));
		mf.setDireccionEntrega(rs.getString("direccionEntrega"));
		mf.setObservaciones(rs.getString("observaciones"));
		mf.setIdFor(rs.getInt("idFor"));
		return mf;
	}
}
