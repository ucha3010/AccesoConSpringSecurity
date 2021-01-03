package com.damian.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.damian.dao.FavoritoDAO;
import com.damian.pojo.Favorito;
import com.damian.utils.LocalLogger;

@Repository
public class FavoritoDAOImpl implements FavoritoDAO {

	private JdbcTemplate jdbcTemplate;

	public FavoritoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "favorito";
	private final String KEY1 = "idPro";
	private final String KEY2 = "idUsr";

	@Override
	public List<Favorito> findAll() {

		String sql = "SELECT * FROM " + TABLA;

		return lista(sql);
	}

	@Override
	public Favorito findById(int idUsr, int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY1 + "=" + idPro + " AND " + KEY2 + "=" + idUsr;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Favorito>() {

			@Override
			public Favorito extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return mapeo(rs);
				}

				return null;
			}

		});
	}

	@Override
	public List<Favorito> findByIdPro(int idPro) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY1 + "=" + idPro;

		return lista(sql);

	}

	@Override
	public List<Favorito> findByIdUsr(int idUsr) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY2 + "=" + idUsr;

		return lista(sql);
	}

	@Override
	public int save(Favorito favorito, HttpServletRequest request) {
		String sql = "INSERT INTO " + TABLA + " (idPro, idUsr, fechaAlta)" + " VALUES (?, ?, ?)";
		int salida = jdbcTemplate.update(sql, favorito.getIdPro(), favorito.getIdUsr(), favorito.getFechaAlta());
		LocalLogger.save(TABLA, favorito, request);
		return salida;
	}

	@Override
	public int delete(int idUsr, int idPro, HttpServletRequest request) {

		Object object = findById(idUsr, idPro);
		String sql = "DELETE FROM " + TABLA + " WHERE " + KEY1 + "=? AND " + KEY2 + "=?";
		int salida = jdbcTemplate.update(sql, idPro, idUsr);
		LocalLogger.delete(TABLA, object, request);
		return salida;
	}

	private List<Favorito> lista(String sql) {
		List<Favorito> fList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Favorito.class));
		return fList;
	}

	private Favorito mapeo(ResultSet rs) throws SQLException {
		Favorito f = new Favorito();
		f.setIdPro(rs.getInt("idPro"));
		f.setIdUsr(rs.getInt("idUsr"));
		f.setFechaAlta(rs.getTimestamp("fechaAlta"));

		return f;
	}

}
