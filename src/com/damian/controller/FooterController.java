package com.damian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.damian.service.EmpresaPropiaService;
import com.damian.service.IndexService;
import com.damian.service.MarcaService;

@Controller
// los atributos que pueden mantenerse en sesión y verse en distintas páginas
@SessionAttributes({ "resultado", "estoy", "errorUsuario", "idUsrLogged", "nameUsrLogged", "prinPicUsr", "prefUsr" })
public class FooterController {

	@Autowired
	private IndexService indexService;

	@Autowired
	private EmpresaPropiaService empresaPropiaService;

	@Autowired
	private MarcaService marcaService;

	@RequestMapping("/footer/aboutus")
	public ModelAndView getaboutus(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("aboutus", "footeraboutus", modelAndView);
	}

	@RequestMapping("/private/aboutus")
	public ModelAndView getPrivateAboutus(ModelAndView modelAndView) throws Exception {
		return getaboutus(modelAndView);
	}

	@RequestMapping("/footer/findyourstore")
	public ModelAndView getfindyourstore(ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("empresaPropias", empresaPropiaService.findAll());
		return fillModelAndView("findyourstore", "footerfindyourstore", modelAndView);
	}

	@RequestMapping("/private/findyourstore")
	public ModelAndView getPrivateFindyourstore(ModelAndView modelAndView) throws Exception {
		return getfindyourstore(modelAndView);
	}

	@RequestMapping("/footer/workwithus")
	public ModelAndView getworkwithus(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("workwithus", "footerworkwithus", modelAndView);
	}

	@RequestMapping("/private/workwithus")
	public ModelAndView getPrivateWorkwithus(ModelAndView modelAndView) throws Exception {
		return getworkwithus(modelAndView);
	}

	@RequestMapping("/footer/legal")
	public ModelAndView getlegal(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("legal", "footerlegal", modelAndView);
	}

	@RequestMapping("/private/legal")
	public ModelAndView getPrivateLegal(ModelAndView modelAndView) throws Exception {
		return getlegal(modelAndView);
	}

	@RequestMapping("/footer/customerreviews")
	public ModelAndView getcustomerreviews(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("customerreviews", "footercustomerreviews", modelAndView);
	}

	@RequestMapping("/private/customerreviews")
	public ModelAndView getPrivateCustomerreviews(ModelAndView modelAndView) throws Exception {
		return getcustomerreviews(modelAndView);
	}

	@RequestMapping("/footer/howtobuy")
	public ModelAndView gethowtobuy(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("howtobuy", "footerhowtobuy", modelAndView);
	}

	@RequestMapping("/private/howtobuy")
	public ModelAndView getPrivateHowtobuy(ModelAndView modelAndView) throws Exception {
		return gethowtobuy(modelAndView);
	}

	@RequestMapping("/footer/cookiespolicy")
	public ModelAndView getcookiespolicy(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("cookiespolicy", "footercookiespolicy", modelAndView);
	}

	@RequestMapping("/private/cookiespolicy")
	public ModelAndView getPrivateCookiespolicy(ModelAndView modelAndView) throws Exception {
		return getcookiespolicy(modelAndView);
	}

	@RequestMapping("/footer/shipments")
	public ModelAndView getshipments(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("shipments", "footershipments", modelAndView);
	}

	@RequestMapping("/private/shipments")
	public ModelAndView getPrivateShipments(ModelAndView modelAndView) throws Exception {
		return getshipments(modelAndView);
	}

	@RequestMapping("/footer/giftcard")
	public ModelAndView getgiftcard(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("giftcard", "footergiftcard", modelAndView);
	}

	@RequestMapping("/private/giftcard")
	public ModelAndView getPrivateGiftcard(ModelAndView modelAndView) throws Exception {
		return getgiftcard(modelAndView);
	}

	@RequestMapping("/footer/brands")
	public ModelAndView getbrands(ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("marcas", marcaService.findAll());
		return fillModelAndView("brands", "footerbrands", modelAndView);
	}

	@RequestMapping("/private/brands")
	public ModelAndView getPrivateBrands(ModelAndView modelAndView) throws Exception {
		return getbrands(modelAndView);
	}

	@RequestMapping("/footer/membersclub")
	public ModelAndView getmembersclub(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("membersclub", "footermembersclub", modelAndView);
	}

	@RequestMapping("/private/membersclub")
	public ModelAndView getPrivateMembersclub(ModelAndView modelAndView) throws Exception {
		return getmembersclub(modelAndView);
	}

	@RequestMapping("/footer/contact")
	public ModelAndView getcontact(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("contact", "footercontact", modelAndView);
	}

	@RequestMapping("/private/contact")
	public ModelAndView getPrivateContact(ModelAndView modelAndView) throws Exception {
		return getcontact(modelAndView);
	}

	@RequestMapping("/footer/Frequentquestions")
	public ModelAndView getFrequentquestions(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("Frequentquestions", "footerFrequentquestions", modelAndView);
	}

	@RequestMapping("/private/Frequentquestions")
	public ModelAndView getPrivateFrequentquestions(ModelAndView modelAndView) throws Exception {
		return getFrequentquestions(modelAndView);
	}

	@RequestMapping("/footer/refund")
	public ModelAndView getrefund(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("refund", "footerrefund", modelAndView);
	}

	@RequestMapping("/private/refund")
	public ModelAndView getPrivateRefund(ModelAndView modelAndView) throws Exception {
		return getrefund(modelAndView);
	}

	@RequestMapping("/footer/guarantee")
	public ModelAndView getguarantee(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("guarantee", "footerguarantee", modelAndView);
	}

	@RequestMapping("/private/guarantee")
	public ModelAndView getPrivateGuarantee(ModelAndView modelAndView) throws Exception {
		return getguarantee(modelAndView);
	}

	@RequestMapping("/footer/promotion1")
	public ModelAndView getpromotion1(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("promotion1", "promotion1", modelAndView);
	}

	@RequestMapping("/private/promotion1")
	public ModelAndView getPrivatePromotion1(ModelAndView modelAndView) throws Exception {
		return getpromotion1(modelAndView);
	}

	@RequestMapping("/footer/promotion2")
	public ModelAndView getpromotion2(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("promotion2", "promotion2", modelAndView);
	}

	@RequestMapping("/private/promotion2")
	public ModelAndView getPrivatePromotion2(ModelAndView modelAndView) throws Exception {
		return getpromotion2(modelAndView);
	}

	@RequestMapping("/footer/promotion3")
	public ModelAndView getpromotion3(ModelAndView modelAndView) throws Exception {
		return fillModelAndView("promotion3", "promotion3", modelAndView);
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

	private ModelAndView fillModelAndView(String estoy, String go, ModelAndView modelAndView) {
		indexService.idUserLogged(modelAndView);
		modelAndView.addObject("estoy", estoy);
		modelAndView.setViewName(go);
		return modelAndView;
	}

}
