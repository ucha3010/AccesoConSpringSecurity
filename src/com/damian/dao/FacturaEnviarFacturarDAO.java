package com.damian.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.damian.pojo.FacturaEnviarFacturar;

public interface FacturaEnviarFacturarDAO {

	public FacturaEnviarFacturar findById(int id);

	public int save(FacturaEnviarFacturar facturaEnviarFacturar, HttpServletRequest request);

	public int update(FacturaEnviarFacturar facturaEnviarFacturar, HttpServletRequest request);

	public int delete(int id, HttpServletRequest request);

	public List<FacturaEnviarFacturar> findByIdFac(int idFac);

}
