package com.damian.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.damian.pojo.FiltroNombre;
import com.damian.pojo.FiltroTitulo;
import com.damian.pojo.Producto;
import com.damian.pojo.Subcategoria;
import com.damian.pojo.front.FrontProductoFiltro;
import com.damian.service.FiltroNombreService;
import com.damian.service.FiltroTituloService;
import com.damian.service.IndexService;
import com.damian.service.ProductoFiltroService;
import com.damian.service.ProductoService;
import com.damian.service.SubcategoriaService;

@Controller
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class FiltroController {
	
	@Autowired
	private FiltroNombreService filtroNombreService;

	@Autowired
	private FiltroTituloService filtroTituloService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProductoFiltroService productoFiltroService;

	@Autowired
	private SubcategoriaService subcategoriaService;

	@Autowired
	private IndexService indexService;

	@RequestMapping("/filtro/nuevo/{idPro}/{paginaInicio}/{totalPaginas}")
	public ModelAndView getAll(ModelAndView modelAndView, @PathVariable("idPro") int idPro,
			@PathVariable("paginaInicio") int productoPaginaInicio, @PathVariable("totalPaginas") int productoTotalPaginas,
			HttpServletRequest request) {
		
		indexService.idUserLogged(modelAndView);
		
		Producto producto = productoService.findByIdModel(idPro);
		modelAndView.addObject("producto", producto);
		
		Subcategoria subcategoria = subcategoriaService.findByIdModel(producto.getSubcategoria().getIdSub());
		modelAndView.addObject("subcategoria", subcategoria);
		
		List<FiltroTitulo> filtroTitulos = filtroTituloService.findByIdSub(subcategoria.getIdSub());
		modelAndView.addObject("filtroTitulos", filtroTitulos);
		
		FrontProductoFiltro frontProductoFiltro = new FrontProductoFiltro();
		frontProductoFiltro.setIdPro(idPro);
		frontProductoFiltro.setProductoPaginaInicio(productoPaginaInicio);
		frontProductoFiltro.setProductoTotalPaginas(productoTotalPaginas);
		frontProductoFiltro.setIdSub(producto.getSubcategoria().getIdSub());
		modelAndView.addObject("frontProductoFiltro", frontProductoFiltro);
		
		modelAndView.setViewName("filtros");
		return modelAndView;
	}

	@RequestMapping(value = { "/filtro/save" }, method = RequestMethod.POST)
	public String saveProducto(@ModelAttribute("frontProductoFiltro") FrontProductoFiltro frontProductoFiltro, BindingResult result, Model model,
			RedirectAttributes ra, HttpServletRequest request) {
		if (result.hasErrors()) {
			// System.out.println(result.getAllErrors());
			// return "producto";
		}
		int cambioHecho = 0;
		
		if(StringUtils.isNotBlank(frontProductoFiltro.getTituloNuevo())) {
			FiltroTitulo filtroTitulo = new FiltroTitulo();
			filtroTitulo.setNombreES(frontProductoFiltro.getTituloNuevo()); //sólo estoy agregando filtros en Español
			Subcategoria subcategoria = new Subcategoria();
			subcategoria.setIdSub(frontProductoFiltro.getIdSub());
			filtroTitulo.setSubcategoria(subcategoria);
			cambioHecho += filtroTituloService.save(filtroTitulo, request);
		}
		
		if(StringUtils.isNotBlank(frontProductoFiltro.getNombreNuevo())) {
			FiltroNombre filtroNombre = new FiltroNombre();
			filtroNombre.setNombreES(frontProductoFiltro.getNombreNuevo()); //sólo estoy agregando filtros en Español
			FiltroTitulo filtroTitulo = new FiltroTitulo();
			filtroTitulo.setIdTitulo(frontProductoFiltro.getIdTituloNuevo());
			filtroNombre.setFiltroTitulo(filtroTitulo);
			cambioHecho += filtroNombreService.save(filtroNombre, request);
		}
		
		if(frontProductoFiltro.getIdNombres() != null) {
			for(Integer idNombre: frontProductoFiltro.getIdNombres()) {
				productoFiltroService.save(frontProductoFiltro.getIdPro(), idNombre, request); //TODO DAMIAN acá seguramente vuelva una excepción cuando exista la entrada. Cappturarla
			}
		}
		
		
		if (cambioHecho > 0) {
			ra.addFlashAttribute("filtro_guardado", "filtro_guardado");
		}
		return "redirect:/filtro/nuevo/"+frontProductoFiltro.getIdPro()+"/"+frontProductoFiltro.getProductoPaginaInicio()+"/"+frontProductoFiltro.getProductoTotalPaginas();
	}

}
