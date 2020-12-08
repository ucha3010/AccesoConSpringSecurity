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

import com.damian.converter.ConverterProductoFiltro;
import com.damian.dao.FiltroNombreDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.ProductoFiltroDAO;
import com.damian.dao.model.ModelProductoFiltro;
import com.damian.pojo.ProductoFiltro;
import com.damian.utils.LocalLogger;

@Repository
public class ProductoFiltroDAOImpl implements ProductoFiltroDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductoFiltroDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "producto_filtro";
	private final String KEY1 = "idPro";
	private final String KEY2 = "idNombre";

	@Autowired
	private ConverterProductoFiltro converterProductoFiltro;

	@Autowired
	private FiltroNombreDAO filtroNombreDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Override
	public List<ProductoFiltro> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(ProductoFiltro productoFiltro, HttpServletRequest request) {
		ModelProductoFiltro mpe = converterProductoFiltro.convert(productoFiltro);
		String sql = "INSERT INTO " + TABLA + " (idPro, idNombre, fechaCreacion, creadoPor)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, mpe.getIdPro(), mpe.getIdNombre(), mpe.getFechaCreacion(), mpe.getCreadoPor());
		LocalLogger.save(TABLA, mpe, request);
	}

	@Override
	public void update(ProductoFiltro productoFiltro, HttpServletRequest request) {
		ModelProductoFiltro mpe = converterProductoFiltro.convert(productoFiltro);
		String sql = "UPDATE " + TABLA + " SET fechaCreacion=?, creadoPor=? " + "WHERE " + KEY1 + "=? AND " + KEY2
				+ "=?";
		jdbcTemplate.update(sql, mpe.getFechaCreacion(), mpe.getCreadoPor(), mpe.getIdPro(), mpe.getIdNombre());
		LocalLogger.update(TABLA, mpe, request);
	}

	@Override
	public void delete(int idPro, int idNombre, HttpServletRequest request) {

		Object object = findByIdProAndIdNombre(idPro, idNombre);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, idPro, idNombre);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public List<ProductoFiltro> findByIdPro(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro;

		return lista(sql);

	}

	@Override
	public List<ProductoFiltro> findByIdNombre(int idNombre) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idNombre=" + idNombre;
		List<ProductoFiltro> productoFiltros = lista(sql);
		ordenarPorNombre(productoFiltros);
		return productoFiltros;
	}

	@Override
	public List<ProductoFiltro> findByIdProModel(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro;

		return listaModel(sql);
	}

	@Override
	public List<ProductoFiltro> findByIdNombreModel(int idNombre) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idNombre=" + idNombre;

		return listaModel(sql);
	}

	@Override
	public ProductoFiltro findByIdProAndIdNombre(int idPro, int idNombre) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro + " AND idNombre=" + idNombre;
		return jdbcTemplate.query(sql, new ResultSetExtractor<ProductoFiltro>() {

			@Override
			public ProductoFiltro extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterProductoFiltro.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	private void ordenarPorNombre(List<ProductoFiltro> productoFiltros) {
		Collections.sort(productoFiltros, new Comparator<ProductoFiltro>() {

			@Override
			public int compare(ProductoFiltro u1, ProductoFiltro u2) {
				return new String(u1.getProducto().getNombreES())
						.compareToIgnoreCase(new String(u2.getProducto().getNombreES()));
			}
		});
	}

	private List<ProductoFiltro> lista(String sql) {
		List<ModelProductoFiltro> mpeList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelProductoFiltro.class));
		List<ProductoFiltro> pfList = new ArrayList<>();
		for (ModelProductoFiltro mpf : mpeList) {
			pfList.add(converterProductoFiltro.convertAll(mpf));
		}
		return pfList;
	}

	private List<ProductoFiltro> listaModel(String sql) {
		List<ModelProductoFiltro> mpfList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelProductoFiltro.class));
		List<ProductoFiltro> upList = new ArrayList<>();
		for (ModelProductoFiltro mpf : mpfList) {
			ProductoFiltro pf = converterProductoFiltro.convert(mpf);
			pf.setProducto(productoDAO.findByIdModel(mpf.getIdPro()));
			pf.setFiltroNombre(filtroNombreDAO.findByIdModel(mpf.getIdNombre()));
			upList.add(pf);
		}
		return upList;
	}

	private ModelProductoFiltro mapeo(ResultSet rs) throws SQLException {
		ModelProductoFiltro mpf = new ModelProductoFiltro();
		mpf.setIdPro(rs.getInt("idPro"));
		mpf.setIdNombre(rs.getInt("idNombre"));
		mpf.setFechaCreacion(rs.getDate("fechaCreacion"));
		mpf.setCreadoPor(rs.getString("creadoPor"));
		return mpf;
	}

}
