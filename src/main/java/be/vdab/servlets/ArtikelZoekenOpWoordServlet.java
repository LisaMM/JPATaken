package be.vdab.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ArtikelZoekenOpWoordServlet
 */
@WebServlet("/artikels/zoeken.htm")
public class ArtikelZoekenOpWoordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoeken.jsp";
	private final ArtikelService artikelService = new ArtikelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterMap().isEmpty()) {
			List<String> fouten = new ArrayList<>();
			String woord = request.getParameter("woord");
			if (woord == null) {
				fouten.add("U moet een woord invullen");
			}
			if (fouten.isEmpty()) {
				woord = "%" + woord + "%";
				Iterable<Artikel> artikels = artikelService.findByWoord(woord);
				if (!artikels.iterator().hasNext()) {
					fouten.add("Geen artikels gevonden");
				} else {
					request.setAttribute("artikels", artikels);
				}
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
