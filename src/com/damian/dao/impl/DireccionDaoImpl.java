package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.converter.ConverterDireccion;
import com.damian.dao.DireccionDao;
import com.damian.dao.model.ModelDireccion;
import com.damian.pojo.Direccion;

@Repository
public class DireccionDaoImpl implements DireccionDao {

	private JdbcTemplate jdbcTemplate;

	public DireccionDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "direccion";
	private final String KEY = "idDir";

	@Override
	public Direccion findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Direccion>() {

			@Override
			public Direccion extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return ConverterDireccion.convert(mapeo(rs));
				}

				return null;
			}

		});

	}

	@Override
	public void save(Direccion direccion) {

		ModelDireccion md = ConverterDireccion.convert(direccion);
		if (direccion.getIdDir() > 0) {
			String sql = "INSERT INTO " + TABLA + " (tipoVia, nombreVia, numero, resto, "
					+ "cp, provincia, ciudad, pais, idDatosPers)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, md.getTipoVia(), md.getNombreVia(), md.getNumero(), md.getResto(), md.getCp(),
					md.getProvincia(), md.getCiudad(), md.getPais(), md.getIdDatosPers());
		} else {
			String sql = "UPDATE " + TABLA + "SET tipoVia=?, nombreVia=?, numero=?, resto=?, cp=?, provincia=?, "
					+ "ciudad=?, pais=?, idDatosPers=? WHERE " + KEY + "=?";
			jdbcTemplate.update(sql, md.getTipoVia(), md.getNombreVia(), md.getNumero(), md.getResto(), md.getCp(),
					md.getProvincia(), md.getCiudad(), md.getPais(), md.getIdDatosPers(), md.getIdDir());

		}
	}

	@Override
	public List<Direccion> findListFromUsuario(int idDatosPers) {

		String sql = "SELECT * FROM " + TABLA + " WHERE idDatosPers=" + idDatosPers;

		List<ModelDireccion> mdList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ModelDireccion.class));
		List<Direccion> dList = new ArrayList<>();
		for (ModelDireccion md : mdList) {
			dList.add(ConverterDireccion.convert(md));
		}

		return dList;
	}

	@Override
	public void delete(Direccion direccion) {

		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY + "=?";
		jdbcTemplate.update(sql, direccion.getIdDir());
	}

	private ModelDireccion mapeo(ResultSet rs) throws SQLException {
		ModelDireccion md = new ModelDireccion();
		md.setIdDir(rs.getInt("idDir"));
		md.setTipoVia(rs.getString("tipoVia"));
		md.setNombreVia(rs.getString("nombreVia"));
		md.setNumero(rs.getString("numero"));
		md.setResto(rs.getString("resto"));
		md.setCp(rs.getString("cp"));
		md.setProvincia(rs.getString("provincia"));
		md.setCiudad(rs.getString("ciudad"));
		md.setPais(rs.getString("pais"));
		md.setIdDatosPers(rs.getInt("idDatosPers"));
		return md;
	}

}
