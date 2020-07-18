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

import com.damian.converter.ConverterProductoFactura;
import com.damian.dao.FacturaDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.ProductoFacturaDAO;
import com.damian.dao.model.ModelProductoFactura;
import com.damian.pojo.ProductoFactura;
import com.damian.utils.LocalLogger;

@Repository
public class ProductoFacturaDAOImpl implements ProductoFacturaDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductoFacturaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "producto_factura";
	private final String KEY1 = "idPro";
	private final String KEY2 = "idFac";

	@Autowired
	private ConverterProductoFactura converterProductoFactura;

	@Autowired
	private FacturaDAO facturaDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Override
	public List<ProductoFactura> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(ProductoFactura productoFactura, HttpServletRequest request) {
		ModelProductoFactura mpf = converterProductoFactura.convert(productoFactura);
		String sql = "INSERT INTO " + TABLA
				+ " (idPro, idFac, cantidad, ivaProducto, porcentajeDescuento, precioUnitSinIva, observaciones, modificadoPor, fechaModificacion)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, mpf.getIdPro(), mpf.getIdFac(), mpf.getCantidad(), mpf.getIvaProducto(),
				mpf.getPorcentajeDescuento(), mpf.getPrecioUnitSinIva(), mpf.getObservaciones(), mpf.getModificadoPor(),
				mpf.getFechaModificacion());
		LocalLogger.save(TABLA, productoFactura, request);
	}

	@Override
	public void update(ProductoFactura productoFactura, HttpServletRequest request) {
		ModelProductoFactura mpf = converterProductoFactura.convert(productoFactura);
		String sql = "UPDATE " + TABLA
				+ " SET cantidad=?, ivaProducto=?, porcentajeDescuento=?, precioUnitSinIva=?, observaciones=?, modificadoPor=?, fechaModificacion=? "
				+ "WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, mpf.getCantidad(), mpf.getIvaProducto(), mpf.getPorcentajeDescuento(),
				mpf.getPrecioUnitSinIva(), mpf.getObservaciones(), mpf.getModificadoPor(), mpf.getFechaModificacion(),
				mpf.getIdPro(), mpf.getIdFac());
		LocalLogger.update(TABLA, productoFactura, request);
	}

	@Override
	public void delete(int idPro, int idFac, HttpServletRequest request) {

		Object object = findByIdProAndIdFac(idPro, idFac);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, idPro, idFac);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public List<ProductoFactura> findByIdPro(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro + " ORDER BY idFac DESC";

		return lista(sql);

	}

	@Override
	public List<ProductoFactura> findByIdFac(int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idFac=" + idFac + " ORDER BY precioUnitSinIva ASC";

		return lista(sql);
	}

	@Override
	public List<ProductoFactura> findByIdProModel(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro;

		return listaModel(sql);
	}

	@Override
	public List<ProductoFactura> findByIdFacModel(int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idFac=" + idFac;

		return listaModel(sql);
	}

	@Override
	public ProductoFactura findByIdProAndIdFac(int idPro, int idFac) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro + " AND idFac=" + idFac;
		return jdbcTemplate.query(sql, new ResultSetExtractor<ProductoFactura>() {

			@Override
			public ProductoFactura extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterProductoFactura.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	private List<ProductoFactura> lista(String sql) {
		List<ModelProductoFactura> mpfList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelProductoFactura.class));
		List<ProductoFactura> pfList = new ArrayList<>();
		for (ModelProductoFactura mpf : mpfList) {
			pfList.add(converterProductoFactura.convertAll(mpf));
		}
		return pfList;
	}

	private List<ProductoFactura> listaModel(String sql) {
		List<ModelProductoFactura> mpfList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelProductoFactura.class));
		List<ProductoFactura> pfList = new ArrayList<>();
		for (ModelProductoFactura mpf : mpfList) {
			ProductoFactura pf = converterProductoFactura.convert(mpf);
			pf.setProducto(productoDAO.findByIdModel(mpf.getIdPro()));
			pf.setFactura(facturaDAO.findByIdModel(mpf.getIdFac()));
			pfList.add(pf);
		}
		return pfList;
	}

	private ModelProductoFactura mapeo(ResultSet rs) throws SQLException {
		ModelProductoFactura mpf = new ModelProductoFactura();
		mpf.setIdPro(rs.getInt("idPro"));
		mpf.setIdFac(rs.getInt("idFac"));
		mpf.setCantidad(rs.getInt("cantidad"));
		mpf.setIvaProducto(rs.getDouble("ivaProducto"));
		mpf.setPorcentajeDescuento(rs.getDouble("porcentajeDescuento"));
		mpf.setPrecioUnitSinIva(rs.getDouble("precioUnitSinIva"));
		mpf.setObservaciones(rs.getString("observaciones"));
		mpf.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		mpf.setModificadoPor(rs.getString("modificadoPor"));

		return mpf;
	}

}
