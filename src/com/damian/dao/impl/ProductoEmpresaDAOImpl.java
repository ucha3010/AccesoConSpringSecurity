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

import com.damian.converter.ConverterProductoEmpresa;
import com.damian.dao.EmpresaDAO;
import com.damian.dao.ProductoDAO;
import com.damian.dao.ProductoEmpresaDAO;
import com.damian.dao.model.ModelProductoEmpresa;
import com.damian.pojo.ProductoEmpresa;
import com.damian.utils.LocalLogger;

@Repository
public class ProductoEmpresaDAOImpl implements ProductoEmpresaDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductoEmpresaDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "producto_empresa";
	private final String KEY1 = "idPro";
	private final String KEY2 = "idEmp";

	@Autowired
	private ConverterProductoEmpresa converterProductoEmpresa;

	@Autowired
	private EmpresaDAO empresaDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Override
	public List<ProductoEmpresa> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public void save(ProductoEmpresa productoEmpresa, HttpServletRequest request) {
		ModelProductoEmpresa mpe = converterProductoEmpresa.convert(productoEmpresa);
		String sql = "INSERT INTO " + TABLA + " (idPro, idEmp, fechaCreacion, creadoPor)" + " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, mpe.getIdPro(), mpe.getIdEmp(), mpe.getFechaCreacion(), mpe.getCreadoPor());
		LocalLogger.save(TABLA, mpe, request);
	}

	@Override
	public void update(ProductoEmpresa productoEmpresa, HttpServletRequest request) {
		ModelProductoEmpresa mpe = converterProductoEmpresa.convert(productoEmpresa);
		String sql = "UPDATE " + TABLA + " SET fechaCreacion=?, creadoPor=? " + "WHERE " + KEY1 + "=? AND " + KEY2
				+ "=?";
		jdbcTemplate.update(sql, mpe.getFechaCreacion(), mpe.getCreadoPor(), mpe.getIdPro(), mpe.getIdEmp());
		LocalLogger.update(TABLA, mpe, request);
	}

	@Override
	public void delete(int idPro, int idEmp, HttpServletRequest request) {

		Object object = findByIdProAndIdEmp(idPro, idEmp);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		jdbcTemplate.update(sql, idPro, idEmp);
		LocalLogger.delete(TABLA, object, request);
	}

	@Override
	public List<ProductoEmpresa> findByIdPro(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro;

		return lista(sql);

	}

	@Override
	public List<ProductoEmpresa> findByIdEmp(int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;
		List<ProductoEmpresa> productoEmpresas = lista(sql);
		ordenarPorNombre(productoEmpresas);
		return productoEmpresas;
	}

	@Override
	public List<ProductoEmpresa> findByIdProModel(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro;

		return listaModel(sql);
	}

	@Override
	public List<ProductoEmpresa> findByIdEmpModel(int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idEmp=" + idEmp;

		return listaModel(sql);
	}

	@Override
	public ProductoEmpresa findByIdProAndIdEmp(int idPro, int idEmp) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idPro=" + idPro + " AND idEmp=" + idEmp;
		return jdbcTemplate.query(sql, new ResultSetExtractor<ProductoEmpresa>() {

			@Override
			public ProductoEmpresa extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterProductoEmpresa.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	private void ordenarPorNombre(List<ProductoEmpresa> productoEmpresas) {
		Collections.sort(productoEmpresas, new Comparator<ProductoEmpresa>() {

			@Override
			public int compare(ProductoEmpresa u1, ProductoEmpresa u2) {
				return new String(u1.getProducto().getNombreES())
						.compareToIgnoreCase(new String(u2.getProducto().getNombreES()));
			}
		});
	}

	private List<ProductoEmpresa> lista(String sql) {
		List<ModelProductoEmpresa> mpeList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelProductoEmpresa.class));
		List<ProductoEmpresa> peList = new ArrayList<>();
		for (ModelProductoEmpresa mpe : mpeList) {
			peList.add(converterProductoEmpresa.convertAll(mpe));
		}
		return peList;
	}

	private List<ProductoEmpresa> listaModel(String sql) {
		List<ModelProductoEmpresa> mpeList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelProductoEmpresa.class));
		List<ProductoEmpresa> upList = new ArrayList<>();
		for (ModelProductoEmpresa mpe : mpeList) {
			ProductoEmpresa pe = converterProductoEmpresa.convert(mpe);
			pe.setProducto(productoDAO.findByIdModel(mpe.getIdPro()));
			pe.setEmpresa(empresaDAO.findByIdModel(mpe.getIdEmp()));
			upList.add(pe);
		}
		return upList;
	}

	private ModelProductoEmpresa mapeo(ResultSet rs) throws SQLException {
		ModelProductoEmpresa mue = new ModelProductoEmpresa();
		mue.setIdPro(rs.getInt("idPro"));
		mue.setIdEmp(rs.getInt("idEmp"));
		mue.setFechaCreacion(rs.getDate("fechaCreacion"));
		mue.setCreadoPor(rs.getString("creadoPor"));
		return mue;
	}

}
