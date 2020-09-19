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

import com.damian.converter.ConverterPreferenciaUsuario;
import com.damian.dao.PreferenciaUsuarioDAO;
import com.damian.dao.model.ModelPreferenciaUsuario;
import com.damian.pojo.PreferenciaUsuario;
import com.damian.utils.LocalLogger;

@Repository
public class PreferenciaUsuarioDAOImpl implements PreferenciaUsuarioDAO {

	private JdbcTemplate jdbcTemplate;

	public PreferenciaUsuarioDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final String TABLA = "preferenciausuario";
	private final String KEY = "idPrefUsr";

	@Autowired
	private ConverterPreferenciaUsuario converterPreferenciaUsuario;

	@Override
	public List<PreferenciaUsuario> findAll() {

		String sql = "SELECT * FROM " + TABLA + " ORDER BY " + KEY + " ASC";

		return lista(sql);
	}

	@Override
	public PreferenciaUsuario findById(int id) {

		String sql = "SELECT * FROM " + TABLA + " WHERE " + KEY + "=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<PreferenciaUsuario>() {

			@Override
			public PreferenciaUsuario extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return converterPreferenciaUsuario.convertAll(mapeo(rs));
				}

				return null;
			}

		});
	}

	@Override
	public int save(PreferenciaUsuario preferenciaUsuario, HttpServletRequest request) {
		ModelPreferenciaUsuario mpu = converterPreferenciaUsuario.convert(preferenciaUsuario);
		String sql = "INSERT INTO " + TABLA
				+ " (idPrefUsr, tema, botonFavorito, recibirPublicidad) VALUES (?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, mpu.getIdPrefUsr(), mpu.getTema(), mpu.getBotonFavorito(),
				mpu.isRecibirPublicidad());
		LocalLogger.save(TABLA, mpu, request);
		return result;
	}

	@Override
	public int update(PreferenciaUsuario preferenciaUsuario, HttpServletRequest request) {
		ModelPreferenciaUsuario mpu = converterPreferenciaUsuario.convert(preferenciaUsuario);
		String sql = "UPDATE " + TABLA + " SET tema=?, botonFavorito=?, recibirPublicidad=? " + "WHERE " + KEY + "=?";
		int result = jdbcTemplate.update(sql, mpu.getTema(), mpu.getBotonFavorito(), mpu.isRecibirPublicidad(),
				mpu.getIdPrefUsr());
		LocalLogger.update(TABLA, mpu, request);
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
	public List<PreferenciaUsuario> findByPublicity(boolean receive) {

		int receivePublicity = (receive ? 1 : 0);
		String sql = "SELECT * FROM " + TABLA + " WHERE recibirPublicidad = " + receivePublicity + " ORDER BY " + KEY + " ASC";

		return lista(sql);
	}

	private List<PreferenciaUsuario> lista(String sql) {
		List<ModelPreferenciaUsuario> mpuList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(ModelPreferenciaUsuario.class));
		List<PreferenciaUsuario> puList = new ArrayList<>();
		for (ModelPreferenciaUsuario mpu : mpuList) {
			puList.add(converterPreferenciaUsuario.convertAll(mpu));
		}
		return puList;
	}

	private ModelPreferenciaUsuario mapeo(ResultSet rs) throws SQLException {
		ModelPreferenciaUsuario mpu = new ModelPreferenciaUsuario();
		mpu.setIdPrefUsr(rs.getInt("idPrefUsr"));
		mpu.setTema(rs.getString("tema"));
		mpu.setBotonFavorito(rs.getString("botonFavorito"));
		mpu.setRecibirPublicidad(rs.getBoolean("recibirPublicidad"));
		return mpu;
	}
}
