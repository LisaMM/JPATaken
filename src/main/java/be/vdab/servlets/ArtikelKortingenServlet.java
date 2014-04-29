package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ArtikelKortingenServlet
 */
@WebServlet("/artikels/kortingen.htm")
public class ArtikelKortingenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/kortingen.jsp";
	private final ArtikelService artikelService = new ArtikelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikels", artikelService.findAll());
		String artikelNrAlsString = request.getParameter("artikelNr");
		if (artikelNrAlsString != null) {
			request.setAttribute("artikel",
					artikelService.read(Long.parseLong(artikelNrAlsString)));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
