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

import com.damian.converter.ConverterProducto;
import com.damian.dao.ProductoDAO;
import com.damian.dao.UsuarioDAO;
import com.damian.dao.UsuarioOrdenDAO;
import com.damian.dao.model.ModelProducto;
import com.damian.pojo.Producto;
import com.damian.utils.Utils;

@Repository
public class ProductoDAOImpl implements ProductoDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "producto";
	private final String KEY = "idPro";
	private final String KEY_COLUMN = "nombreES";
	private final String KEY_ORDER = "ASC";
	private final String COLUMN_ORDER = "nombreES ASC";

	@Autowired
	private ConverterProducto converterProducto;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioOrdenDAO usuarioOrdenDAO;

	@Override
	public List<Producto> findAll(String column, int paginaInicio, int totalPaginas, HttpServletRequest request) {

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
	public Producto findById(int id) {

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
			String sql = "INSERT INTO " + TABLA
					+ " (nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU, unidades, precioVenta, precioCompra, marca, modelo, "
					+ "serie, ubicacion, estado, partida, fechaCompra, enviar, vendible, mesesGarantia, peso, volumen, idSub, modificadoPor, fechaModificacion)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, mp.getNombreES(), mp.getNombreEN(), mp.getNombrePT(), mp.getNombreFR(),
					mp.getNombreIT(), mp.getNombreGE(), mp.getNombreCA(), mp.getNombreEU(), mp.getUnidades(),
					mp.getPrecioVenta(), mp.getPrecioCompra(), mp.getMarca(), mp.getModelo(), mp.getSerie(),
					mp.getUbicacion(), mp.getEstado(), mp.getPartida(), mp.getFechaCompra(), mp.isEnviar(),
					mp.isVendible(), mp.getMesesGarantia(), mp.getPeso(), mp.getVolumen(), mp.getIdSub(),
					mp.getModificadoPor(), mp.getFechaModificacion());
		}
	}

	@Override
	public void update(Producto producto) {
		ModelProducto mp = converterProducto.convert(producto);
		String sql = "UPDATE " + TABLA
				+ " SET nombreES=?, nombreEN=?, nombrePT=?, nombreFR=?, nombreIT=?, nombreGE=?, nombreCA=?, nombreEU=?, unidades=?, precioVenta=?, precioCompra=?, marca=?, "
				+ "modelo=?, serie=?, ubicacion=?, estado=?, partida=?, fechaCompra=?, enviar=?, vendible=?, "
				+ "mesesGarantia=?, peso=?, volumen=?, idSub=?, modificadoPor=?, fechaModificacion=? WHERE " + KEY
				+ "=?";
		jdbcTemplate.update(sql, mp.getNombreES(), mp.getNombreEN(), mp.getNombrePT(), mp.getNombreFR(),
				mp.getNombreIT(), mp.getNombreGE(), mp.getNombreCA(), mp.getNombreEU(), mp.getUnidades(),
				mp.getPrecioVenta(), mp.getPrecioCompra(), mp.getMarca(), mp.getModelo(), mp.getSerie(),
				mp.getUbicacion(), mp.getEstado(), mp.getPartida(), mp.getFechaCompra(), mp.isEnviar(), mp.isVendible(),
				mp.getMesesGarantia(), mp.getPeso(), mp.getVolumen(), mp.getIdSub(), mp.getModificadoPor(),
				mp.getFechaModificacion(), mp.getIdPro());
	}

	@Override
	public int delete(int id) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Producto> findByIdSubModel(int idSub) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idSub = " + idSub + " ORDER BY nombreES ASC";

		return lista(sql);
	}

	@Override
	public List<Producto> findByIdList(int id) {

		List<Producto> productos = new ArrayList<>();
		productos.add(findById(id));
		return productos;
	}

	@Override
	public int getMaxId() {
		return jdbcTemplate.queryForObject("SELECT MAX(" + KEY + ") FROM " + TABLA, Integer.class);
	}

	@Override
	public List<Producto> findSearchAll() {
		List<ModelProducto> mpList = jdbcTemplate.query(
				"SELECT idPro, marca, modelo, precioVenta, nombreES, nombreEN, nombrePT, nombreFR, nombreIT, nombreGE, nombreCA, nombreEU FROM "
						+ TABLA,
				BeanPropertyRowMapper.newInstance(ModelProducto.class));
		List<Producto> pList = new ArrayList<>();
		for (ModelProducto mp : mpList) {
			pList.add(converterProducto.convert(mp));
		}
		return pList;
	}

	private List<Producto> lista(String sql) {
		List<ModelProducto> mpList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelProducto.class));
		List<Producto> eList = new ArrayList<>();
		for (ModelProducto mp : mpList) {
			eList.add(converterProducto.convert(mp));
		}
		return eList;
	}

	private ModelProducto mapeo(ResultSet rs) throws SQLException {
		ModelProducto mp = new ModelProducto();
		mp.setIdPro(rs.getInt("idPro"));
		mp.setNombreES(rs.getString("nombreES"));
		mp.setNombreEN(rs.getString("nombreEN"));
		mp.setNombrePT(rs.getString("nombrePT"));
		mp.setNombreFR(rs.getString("nombreFR"));
		mp.setNombreIT(rs.getString("nombreIT"));
		mp.setNombreGE(rs.getString("nombreGE"));
		mp.setNombreCA(rs.getString("nombreCA"));
		mp.setNombreEU(rs.getString("nombreEU"));
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
		mp.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
		mp.setModificadoPor(rs.getString("modificadoPor"));
		return mp;
	}
}
