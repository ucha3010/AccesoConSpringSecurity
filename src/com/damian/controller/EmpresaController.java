package com.damian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.Empresa;
import com.damian.pojo.EmpresaPropia;
import com.damian.service.EmpresaPropiaService;
import com.damian.service.EmpresaService;
import com.damian.service.IndexService;
import com.damian.service.PaginacionService;
import com.damian.valid.SpringFormGroup;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EmpresaPropiaService empresaPropiaService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private PaginacionService paginacionService;

	@RequestMapping("/empresa/all/{column}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("column") String column,
			@PathVariable("paginaInicio") int paginaInicio, @PathVariable("totalPaginas") int totalPaginas,
			HttpServletRequest request) {
		indexService.idUserLogged(modelAndView);
		modelAndView.addObject("empresas", empresaService.findAll(column, paginaInicio, totalPaginas, request));
		modelAndView.addObject("paginacion", paginacionService.pagination(paginaInicio, totalPaginas, "empresa"));
		modelAndView.addObject("buscarempresas", empresaService.findSearchAll());
		modelAndView.addObject("hayEmpresaPropia", hayEmpresaPropia());
		modelAndView.setViewName("empresas");
		return modelAndView;
	}

	@RequestMapping("/empresa/filtered/{idEmp}")
	public ModelAndView getFiltered(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp) {
		modelAndView.addObject("empresas", empresaService.findByIdList(idEmp));
		modelAndView.addObject("paginacion", paginacionService.pagination(0, 0, "empresa"));
		modelAndView.addObject("buscarempresas", empresaService.findSearchAll());
		modelAndView.addObject("hayEmpresaPropia", hayEmpresaPropia());
		modelAndView.setViewName("empresas");
		return modelAndView;
	}

	@RequestMapping("/empresa/{idEmp}")
	public ModelAndView getUser(ModelAndView modelAndView, @PathVariable("idEmp") int idEmp) {
		Empresa empresa = new Empresa();
		if (idEmp > 0) {
			empresa = empresaService.findById(idEmp);
		}
		modelAndView.addObject("empresa", empresa);
		modelAndView.setViewName("empresa");
		return modelAndView;
	}

	@RequestMapping(value = { "/empresa/save" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("empresa") @Validated(value = SpringFormGroup.class) Empresa empresa,
			BindingResult result, Model model, RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			// return "empresa";
		}
		boolean nueva = false;
		if (empresa.getIdEmp() == 0) {
			nueva = true;
		}
		empresaService.save(empresa, request);
		if (nueva) {
			ra.addFlashAttribute("empresa_agregada", "empresa_agregada");
		}
		return "redirect:/empresa/all/null/0/100";
	}

	@RequestMapping("/empresa/{idEmp}/delete")
	public String deleteUser(@PathVariable("idEmp") int idEmp, RedirectAttributes ra, HttpServletRequest request) {

		if (empresaService.delete(idEmp, request)) {
			ra.addFlashAttribute("empresa_eliminada", "empresa_eliminada");
		} else {
			ra.addFlashAttribute("resultado", "No se ha podido borrar la empresa");
		}

		return "redirect:/empresa/all/null/0/100";

	}
	
	private boolean hayEmpresaPropia() {
		List<EmpresaPropia> empresaPropiaList = empresaPropiaService.findAll();
		return !empresaPropiaList.isEmpty();
	}

}
