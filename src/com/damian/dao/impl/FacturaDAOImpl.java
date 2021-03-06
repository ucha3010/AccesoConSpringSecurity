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

import com.damian.converter.ConverterFactura;
import com.damian.dao.FacturaDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelFactura;
import com.damian.pojo.Factura;
import com.damian.service.EstadoService;
import com.damian.utils.LocalLogger;
import com.damian.utils.Utils;

@Repository
public class FacturaDAOImpl implements FacturaDAO {

	private JdbcTemplate jdbcTemplate;

	public FacturaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "factura";
	private final String KEY = "idFac";
	private final String KEY_COLUMN = "fechaCompra";
	private final String KEY_ORDER = "DESC";
	private final String COLUMN_ORDER = "fechaCompra DESC";

	@Autowired
	private ConverterFactura converterFactura;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioOrdenDAO usuarioOrdenDAO;

	@Override
	public List<Factura> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request) {

		String columnAndOrder = Utils.validateColumnAndOrder(column, null, TABLA, KEY_COLUMN, KEY_ORDER, COLUMN_ORDER,
				request, usuarioDAO, usuarioOrdenDAO);

		if (columnAndOrder != null) {
			String sql = "SELECT * FROM " + TABLA + " ORDER BY " + columnAndOrder + " LIMIT " + paginaInicio + ","
					+ totalPaginas;

			return lista(sql);
		} else {
			return new ArrayList<>();
		}
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
	public int save(Factura factura, HttpServletRequest request) {
		if (factura.getIdFac() > 0) {
			return update(factura, request);
		} else {
			ModelFactura mf = converterFactura.convert(factura);
			String sql = "INSERT INTO " + TABLA
					+ " (compra, totalSinIvaEnvDescfac, descuentoTotal, descuentoImporteProductos, descuentoImporteFactura, descuentoImporteTotal, importeEnvioSinIva, envioIvaPor, envioIvaImp, productosIvaImp, totalSinIvaConDescfac, ivaTotal, ivaImporteTotal, importeTotal, fechaCompra, "
					+ "fechaEntrega, idEst, observaciones, idFor, creadoPor, idCuo, modificadoPor, fechaModificacion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(sql, mf.isCompra(), mf.getTotalSinIvaEnvDescfac(), 
					mf.getDescuentoTotal(), mf.getDescuentoImporteProductos(), mf.getDescuentoImporteFactura(), mf.getDescuentoImporteTotal(), mf.getImporteEnvioSinIva(), mf.getEnvioIvaPor(), mf.getEnvioIvaImp(), mf.getProductosIvaImp(), mf.getTotalSinIvaConDescfac(), mf.getIvaTotal(), mf.getIvaImporteTotal(),
					mf.getImporteTotal(), mf.getFechaCompra(), mf.getFechaEntrega(), mf.getIdEst(),
					mf.getObservaciones(), mf.getIdFor(), mf.getCreadoPor(), mf.getIdCuo(), mf.getModificadoPor(),
					mf.getFechaModificacion());
			LocalLogger.save(TABLA, mf, request);
			return result;
		}
	}

	@Override
	public int update(Factura factura, HttpServletRequest request) {
		ModelFactura mf = converterFactura.convert(factura);
		String sql = "UPDATE " + TABLA
				+ " SET compra=?, totalSinIvaEnvDescfac=?, descuentoTotal=?, descuentoImporteProductos=?, descuentoImporteFactura=?, descuentoImporteTotal=?, importeEnvioSinIva=?, envioIvaPor=?, envioIvaImp=?, productosIvaImp=?, totalSinIvaConDescfac=?, ivaTotal=?, ivaImporteTotal=?, importeTotal=?, "
				+ "fechaCompra=?, fechaEntrega=?, idEst=?, observaciones=?, idFor=?, creadoPor=?, idCuo=?, modificadoPor=?, "
				+ "fechaModificacion=? WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mf.isCompra(), mf.getTotalSinIvaEnvDescfac(), 
				mf.getDescuentoTotal(), mf.getDescuentoImporteProductos(), mf.getDescuentoImporteFactura(), mf.getDescuentoImporteTotal(), mf.getImporteEnvioSinIva(), mf.getEnvioIvaPor(), mf.getEnvioIvaImp(), mf.getProductosIvaImp(), mf.getTotalSinIvaConDescfac(), mf.getIvaTotal(), mf.getIvaImporteTotal(), mf.getImporteTotal(),
				mf.getFechaCompra(), mf.getFechaEntrega(), mf.getIdEst(), mf.getObservaciones(), mf.getIdFor(),
				mf.getCreadoPor(), mf.getIdCuo(), mf.getModificadoPor(), mf.getFechaModificacion(), mf.getIdFac());
		LocalLogger.update(TABLA, mf, request);
		return result;
	}

	@Override
	public int delete(int id, HttpServletRequest request) {

		Object object = findById(id);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, id);
		LocalLogger.delete(TABLA, object, request);
		return result;
	}

	@Override
	public List<Factura> findByIdEstModel(int idEst, String column, HttpServletRequest request) {

		String columnAndOrder = Utils.validateColumnAndOrder(column, null, TABLA, KEY_COLUMN, KEY_ORDER, COLUMN_ORDER,
				request, usuarioDAO, usuarioOrdenDAO);

		if (columnAndOrder != null) {
			String sql = "SELECT * FROM " + TABLA + " WHERE idEst = " + idEst + " ORDER BY " + columnAndOrder;

			return lista(sql);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Factura> findByIdForModel(int idFor) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idFor = " + idFor + " ORDER BY fechaCompra DESC";

		return lista(sql);
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	@Override
	public List<Factura> findByIdCuo(int idCuo) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idCuo = " + idCuo + " ORDER BY fechaCompra ASC";

		return lista(sql);
	}

	@Override
	public List<Factura> findByIdList(int id) {
		List<Factura> facturas = new ArrayList<>();
		facturas.add(findById(id));
		return facturas;
	}

	@Override
	public List<Factura> findSearchAll() {
		List<ModelFactura> mfList = jdbcTemplate.query("SELECT idFac, fechaCompra, importeTotal, idEst FROM " + TABLA,
				BeanPropertyRowMapper.newInstance(ModelFactura.class));
		List<Factura> fList = new ArrayList<>();
		for (ModelFactura mf : mfList) {
			Factura f = converterFactura.convert(mf);
			f.setEstado(estadoService.findByIdModel(mf.getIdEst()));
			fList.add(f);
		}
		return fList;
	}

	@Override
	public ModelFactura findModelById(int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + idFac;		
		List<ModelFactura> facturas = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelFactura.class));
		if(facturas != null) {
			return facturas.get(0);
		} else {
			return new ModelFactura();
		}
	}

	private List<Factura> lista(String sql) {
		List<ModelFactura> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelFactura.class));
		List<Factura> eList = new ArrayList<>();
		for (ModelFactura mf : mpList) {
			eList.add(converterFactura.convert(mf));
		}
		return eList;
	}

	private ModelFactura mapeo(ResultSet rs) throws SQLException {
		ModelFactura mf = new ModelFactura();
		mf.setIdFac(rs.getInt("idFac"));
		mf.setCompra(rs.getBoolean("compra"));
		mf.setTotalSinIvaEnvDescfac(rs.getDouble("totalSinIvaEnvDescfac"));
		mf.setDescuentoTotal(rs.getDouble("descuentoTotal"));
		mf.setDescuentoImporteProductos(rs.getDouble("descuentoImporteProductos"));
		mf.setDescuentoImporteFactura(rs.getDouble("descuentoImporteFactura"));
		mf.setDescuentoImporteTotal(rs.getDouble("descuentoImporteTotal"));
		mf.setImporteEnvioSinIva(rs.getDouble("importeEnvioSinIva"));
		mf.setEnvioIvaPor(rs.getDouble("envioIvaPor"));
		mf.setEnvioIvaImp(rs.getDouble("envioIvaImp"));
		mf.setProductosIvaImp(rs.getDouble("productosIvaImp"));
		mf.setTotalSinIvaConDescfac(rs.getDouble("totalSinIvaConDescfac"));
		mf.setIvaTotal(rs.getDouble("ivaTotal"));
		mf.setIvaImporteTotal(rs.getDouble("ivaImporteTotal"));
		mf.setImporteTotal(rs.getDouble("importeTotal"));
		mf.setFechaCompra(rs.getDate("fechaCompra"));
		mf.setFechaEntrega(rs.getDate("fechaEntrega"));
		mf.setIdEst(rs.getInt("idEst"));
		mf.setObservaciones(rs.getString("observaciones"));
		mf.setCreadoPor(rs.getString("creadoPor"));
		mf.setIdFor(rs.getInt("idFor"));
		mf.setIdCuo(rs.getInt("idCuo"));
		mf.setModificadoPor(rs.getString("modificadoPor"));
		mf.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		return mf;
	}

	@Override
	public List<Map<String, Object>> facturaMap(int id) {
		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.queryForList(sql);
	}

}
