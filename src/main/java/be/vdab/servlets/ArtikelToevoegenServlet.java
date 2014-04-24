package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import be.vdab.entities.Artikel;
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
		if (naam == null || naam.isEmpty()) {
			fouten.add("Naam verplicht");
		}
		BigDecimal aankoopprijs = null;
		try {
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (aankoopprijs.compareTo(BigDecimal.ZERO) < 0) {
				fouten.add("De aankoopprijs moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("De aankoopprijs moet een getal zijn");
		}
		BigDecimal verkoopprijs = null;
		try {
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (verkoopprijs.compareTo(new BigDecimal(
					request.getParameter("aankoopprijs"))) < 0) {
				fouten.add("De verkoopprijs moet kleiner dan de aankoopprijs zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("De verkoopprijs moet een getal zijn");
		}

		if (fouten.isEmpty()) {
			Artikel artikel = new Artikel(naam, aankoopprijs, verkoopprijs);
			artikelService.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), artikel.getArtikelNr())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
