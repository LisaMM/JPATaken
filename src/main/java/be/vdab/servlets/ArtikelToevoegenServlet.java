package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import be.vdab.entities.*;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ArtikelToevoegenServlet
 */
@WebServlet("/artikels/toevoegen.htm")
public class ArtikelToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/toegevoegd.htm?artikelNr=%d";
	private final ArtikelService artikelService = new ArtikelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> fouten = new ArrayList<>();
		String naam = request.getParameter("naam");
		int houdbaarheid = 0;
		int garantie = 0;
		BigDecimal aankoopprijs = null;
		BigDecimal verkoopprijs = null;
		if (naam == null || naam.isEmpty()) {
			fouten.add("Naam verplicht");
		}
		try {
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (aankoopprijs.compareTo(BigDecimal.ZERO) < 0) {
				fouten.add("De aankoopprijs moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("De aankoopprijs moet een getal zijn");
		}
		try {
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (verkoopprijs.compareTo(new BigDecimal(
					request.getParameter("aankoopprijs"))) < 0) {
				fouten.add("De aankoopprijs moet kleiner dan de verkoopprijs zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("De verkoopprijs moet een getal zijn");
		}
		String soort = request.getParameter("soort");
		if ("F".equals(soort)) {
			try {
				houdbaarheid = Integer.parseInt(request.getParameter("houdbaarheid"));
				if (houdbaarheid <= 0) {
					fouten.add("De houdbaarheid moet een positief getal zijn");
				}
			} catch (NumberFormatException ex) {
				fouten.add("De houdbaarheid moet een getal zijn");
			}	
		} else if ("NF".equals(soort)) {
			try {
				garantie = Integer.parseInt(request.getParameter("garantie"));
				if (garantie < 0) {
					fouten.add("De garantie moet een positief getal zijn");
				}
			} catch (NumberFormatException ex) {
				fouten.add("De garantie moet een getal zijn");
			}	
		} else {
			fouten.add("Is dit een Food of Non-Food artikel?");
		}
		if (fouten.isEmpty()) {
			Artikel artikel;
			if ("F".equals(soort)) {
				artikel = new FoodArtikel(naam, aankoopprijs, verkoopprijs, houdbaarheid);
			} else {
				artikel = new NonFoodArtikel(naam, aankoopprijs, verkoopprijs, houdbaarheid);
			}
			artikelService.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), artikel.getArtikelNr())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
