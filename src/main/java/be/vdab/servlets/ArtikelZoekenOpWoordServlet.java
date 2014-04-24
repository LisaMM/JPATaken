package be.vdab.servlets;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
		String woord = request.getParameter("woord");
		if (woord != null && !woord.isEmpty()) {
			request.setAttribute("artikels", artikelService.findByWoord(woord));
		} else {
			request.setAttribute("fouten", Arrays.asList("woord is verplicht"));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
