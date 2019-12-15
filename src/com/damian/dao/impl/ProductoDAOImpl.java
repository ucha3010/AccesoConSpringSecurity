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

import com.damian.converter.ConverterProducto;
import com.damian.dao.ProductoDAO;
import com.damian.dao.model.ModelProducto;
import com.damian.pojo.Producto;

@Repository
public class ProductoDAOImpl implements ProductoDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "producto";
	private final String KEY = "idPro";

	@Autowired
	private ConverterProducto converterProducto;

	@Override
	public List<Producto> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY descripcion ASC";

		return lista(sql);
	}

	@Override
	public Producto findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Producto>() {

			@Override
			public Producto extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterProducto.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public Producto findByIdModel(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Producto>() {

			@Override
			public Producto extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterProducto.convert(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public void save(Producto producto) {
		if (producto.getIdPro() > 0) {
			update(producto);
		} else {
			ModelProducto mp = converterProducto.convert(producto);
			String sql = "INSERT INTO " + TABLA + " (descripcion, unidades, precioVenta, precioCompra, marca, modelo, "
					+ "serie, ubicacion, estado, partida, fechaCompra, enviar, vendible, mesesGarantia, peso, volumen, idSub)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mp.getDescripcion(), mp.getUnidades(), mp.getPrecioVenta(), mp.getPrecioCompra(),
					mp.getMarca(), mp.getModelo(), mp.getSerie(), mp.getUbicacion(), mp.getEstado(), mp.getPartida(),
					mp.getFechaCompra(), mp.isEnviar(), mp.isVendible(), mp.getMesesGarantia(), mp.getPeso(),
					mp.getVolumen(), mp.getIdSub());
		}
	}

	@Override
	public void update(Producto producto) {
		ModelProducto mp = converterProducto.convert(producto);
		String sql = "UPDATE " + TABLA + " SET descripcion=?, unidades=?, precioVenta=?, precioCompra=?, marca=?, "
				+ "modelo=?, serie=?, ubicacion=?, estado=?, partida=?, fechaCompra=?, enviar=?, vendible=?, "
				+ "mesesGarantia=?, peso=?, volumen=?, idSub " + "WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, mp.getDescripcion(), mp.getUnidades(), mp.getPrecioVenta(), mp.getPrecioCompra(),
				mp.getMarca(), mp.getModelo(), mp.getSerie(), mp.getUbicacion(), mp.getEstado(), mp.getPartida(),
				mp.getFechaCompra(), mp.isEnviar(), mp.isVendible(), mp.getMesesGarantia(), mp.getPeso(),
				mp.getVolumen(), mp.getIdSub(), mp.getIdPro());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Producto> findByIdSubModel(int idSub) {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY descripcion ASC WHERE idSub = " + idSub;

		return lista(sql);
	}

	private List<Producto> lista(String sql) {
		List<ModelProducto> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelProducto.class));
		List<Producto> eList = new ArrayList<>();
		for (ModelProducto mp : mpList) {
			eList.add(converterProducto.convertAll(mp));
		}
		return eList;
	}

	private ModelProducto mapeo(ResultSet rs) throws SQLException {
		ModelProducto mp = new ModelProducto();
		mp.setIdPro(rs.getInt("idPro"));
		mp.setDescripcion(rs.getString("descripcion"));
		mp.setUnidades(rs.getInt("unidades"));
		mp.setPrecioVenta(rs.getDouble("precioVenta"));
		mp.setPrecioCompra(rs.getDouble("precioCompra"));
		mp.setMarca(rs.getString("marca"));
		mp.setModelo(rs.getString("modelo"));
		mp.setSerie(rs.getString("serie"));
		mp.setUbicacion(rs.getString("ubicacion"));
		mp.setEstado(rs.getString("estado"));
		mp.setPartida(rs.getString("partida"));
		mp.setFechaCompra(rs.getDate("fechaCompra"));
		mp.setEnviar(rs.getBoolean("enviar"));
		mp.setVendible(rs.getBoolean("vendible"));
		mp.setMesesGarantia(rs.getDouble("mesesGarantia"));
		mp.setPeso(rs.getDouble("peso"));
		mp.setVolumen(rs.getDouble("volumen"));
		mp.setIdSub(rs.getInt("idSub"));
		return mp;
	}
}
