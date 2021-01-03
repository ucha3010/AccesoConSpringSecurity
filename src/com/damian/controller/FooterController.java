package com.damian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.pojo.AdministracionOfertas;
import com.damian.service.AdministracionOfertasService;
import com.damian.service.EmpresaPropiaService;
import com.damian.service.IndexService;
import com.damian.service.MarcaService;

@Controller
// los atributos que pueden mantenerse en sesión y verse en distintas páginas
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class FooterController {

	@Autowired
	private AdministracionOfertasService administracionOfertasService;

	@Autowired
	private EmpresaPropiaService empresaPropiaService;

	@Autowired
	private IndexService indexService;

	@Autowired
	private MarcaService marcaService;

	@RequestMapping("/footer/aboutus")
	public ModelAndView getaboutus(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("aboutus", "frontaboutus", modelAndView);
	}

	@RequestMapping("/private/aboutus")
	public ModelAndView getPrivateAboutus(ModelAndView modelAndView) throws Exception {
		return getaboutus(modelAndView);
	}

	@RequestMapping("/footer/findyourstore")
	public ModelAndView getfindyourstore(ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("empresaPropias", empresaPropiaService.findAll());
		return fillModelAndView("findyourstore", "frontfindyourstore", modelAndView);
	}

	@RequestMapping("/private/findyourstore")
	public ModelAndView getPrivateFindyourstore(ModelAndView modelAndView) throws Exception {
		return getfindyourstore(modelAndView);
	}

	@RequestMapping("/footer/workwithus")
	public ModelAndView getworkwithus(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("workwithus", "frontworkwithus", modelAndView);
	}

	@RequestMapping("/private/workwithus")
	public ModelAndView getPrivateWorkwithus(ModelAndView modelAndView) throws Exception {
		return getworkwithus(modelAndView);
	}

	@RequestMapping("/footer/legal")
	public ModelAndView getlegal(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("legal", "frontlegal", modelAndView);
	}

	@RequestMapping("/private/legal")
	public ModelAndView getPrivateLegal(ModelAndView modelAndView) throws Exception {
		return getlegal(modelAndView);
	}

	@RequestMapping("/footer/customerreviews")
	public ModelAndView getcustomerreviews(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("customerreviews", "frontcustomerreviews", modelAndView);
	}

	@RequestMapping("/private/customerreviews")
	public ModelAndView getPrivateCustomerreviews(ModelAndView modelAndView) throws Exception {
		return getcustomerreviews(modelAndView);
	}

	@RequestMapping("/footer/howtobuy")
	public ModelAndView gethowtobuy(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("howtobuy", "fronthowtobuy", modelAndView);
	}

	@RequestMapping("/private/howtobuy")
	public ModelAndView getPrivateHowtobuy(ModelAndView modelAndView) throws Exception {
		return gethowtobuy(modelAndView);
	}

	@RequestMapping("/footer/cookiespolicy")
	public ModelAndView getcookiespolicy(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("cookiespolicy", "frontcookiespolicy", modelAndView);
	}

	@RequestMapping("/private/cookiespolicy")
	public ModelAndView getPrivateCookiespolicy(ModelAndView modelAndView) throws Exception {
		return getcookiespolicy(modelAndView);
	}

	@RequestMapping("/footer/shipments")
	public ModelAndView getshipments(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("shipments", "frontshipments", modelAndView);
	}

	@RequestMapping("/private/shipments")
	public ModelAndView getPrivateShipments(ModelAndView modelAndView) throws Exception {
		return getshipments(modelAndView);
	}

	@RequestMapping("/footer/giftcard")
	public ModelAndView getgiftcard(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("giftcard", "frontgiftcard", modelAndView);
	}

	@RequestMapping("/private/giftcard")
	public ModelAndView getPrivateGiftcard(ModelAndView modelAndView) throws Exception {
		return getgiftcard(modelAndView);
	}

	@RequestMapping("/footer/brands")
	public ModelAndView getbrands(ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("marcas", marcaService.findAll());
		return fillModelAndView("brands", "frontbrands", modelAndView);
	}

	@RequestMapping("/private/brands")
	public ModelAndView getPrivateBrands(ModelAndView modelAndView) throws Exception {
		return getbrands(modelAndView);
	}

	@RequestMapping("/footer/membersclub")
	public ModelAndView getmembersclub(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("membersclub", "frontmembersclub", modelAndView);
	}

	@RequestMapping("/private/membersclub")
	public ModelAndView getPrivateMembersclub(ModelAndView modelAndView) throws Exception {
		return getmembersclub(modelAndView);
	}

	@RequestMapping("/footer/contact")
	public ModelAndView getcontact(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("contact", "frontcontact", modelAndView);
	}

	@RequestMapping("/private/contact")
	public ModelAndView getPrivateContact(ModelAndView modelAndView) throws Exception {
		return getcontact(modelAndView);
	}

	@RequestMapping("/footer/Frequentquestions")
	public ModelAndView getFrequentquestions(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("Frequentquestions", "frontfrequentquestions", modelAndView);
	}

	@RequestMapping("/private/Frequentquestions")
	public ModelAndView getPrivateFrequentquestions(ModelAndView modelAndView) throws Exception {
		return getFrequentquestions(modelAndView);
	}

	@RequestMapping("/footer/refund")
	public ModelAndView getrefund(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("refund", "frontrefund", modelAndView);
	}

	@RequestMapping("/private/refund")
	public ModelAndView getPrivateRefund(ModelAndView modelAndView) throws Exception {
		return getrefund(modelAndView);
	}

	@RequestMapping("/footer/guarantee")
	public ModelAndView getguarantee(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("guarantee", "frontguarantee", modelAndView);
	}

	@RequestMapping("/private/guarantee")
	public ModelAndView getPrivateGuarantee(ModelAndView modelAndView) throws Exception {
		return getguarantee(modelAndView);
	}

	@RequestMapping("/footer/promotion1")
	public ModelAndView getpromotion1(ModelAndView modelAndView) throws Exception {

		List<AdministracionOfertas> ofertasList = administracionOfertasService.findByOfertas(0);
		completaDatosProductos(ofertasList, modelAndView);
		modelAndView.addObject("ofertas", ofertasList);

		return fillModelAndView("promotion1", "frontofertas", modelAndView);
	}

	@RequestMapping("/private/promotion1")
	public ModelAndView getPrivatePromotion1(ModelAndView modelAndView) throws Exception {
		return getpromotion1(modelAndView);
	}

	@RequestMapping("/footer/promotion2")
	public ModelAndView getpromotion2(ModelAndView modelAndView) throws Exception {

		List<AdministracionOfertas> popularesList = administracionOfertasService.findByProductosPopulares(0);
		completaDatosProductos(popularesList, modelAndView);
		modelAndView.addObject("populares", popularesList);

		return fillModelAndView("promotion2", "frontpopulares", modelAndView);
	}

	@RequestMapping("/private/promotion2")
	public ModelAndView getPrivatePromotion2(ModelAndView modelAndView) throws Exception {
		return getpromotion2(modelAndView);
	}

	@RequestMapping("/footer/promotion3")
	public ModelAndView getpromotion3(ModelAndView modelAndView) throws Exception {

		List<AdministracionOfertas> novedadesList = administracionOfertasService.findByNovedades(0);
		completaDatosProductos(novedadesList, modelAndView);
		modelAndView.addObject("novedades", novedadesList);

		return fillModelAndView("promotion3", "frontnovedades", modelAndView);
	}

	@RequestMapping("/private/promotion3")
	public ModelAndView getPrivatePromotion3(ModelAndView modelAndView) throws Exception {
		return getpromotion3(modelAndView);
	}

	@RequestMapping("/footer/promotion4")
	public ModelAndView getpromotion4(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("promotion4", "promotion4", modelAndView);
	}

	@RequestMapping("/private/promotion4")
	public ModelAndView getPrivatePromotion4(ModelAndView modelAndView) throws Exception {
		return getpromotion4(modelAndView);
	}

	private void completaDatosProductos(List<AdministracionOfertas> administracionOfertasList, ModelAndView modelAndView) {
		indexService.agregarFotos(administracionOfertasList);
		administracionOfertasService.fillFavoritos(administracionOfertasList, indexService.idUserLogged(modelAndView));
	}

	private ModelAndView fillModelAndView(String estoy, String go, ModelAndView modelAndView) {
		indexService.idUserLogged(modelAndView);
		indexService.chargeDivSearchBar(modelAndView);
		modelAndView.addObject("estoy", estoy);
		modelAndView.setViewName(go);
		return modelAndView;
	}

}
