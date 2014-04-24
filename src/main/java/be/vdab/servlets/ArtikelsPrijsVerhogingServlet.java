package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ArtikelsPrijsVerhoging
 */
@WebServlet("/artikels/prijsverhoging.htm")
public class ArtikelsPrijsVerhogingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/prijsverhoging.jsp";
	private static final String REDIRECT_URL = "%s/artikels/prijsverhoogd.htm";
	private final ArtikelService artikelService = new ArtikelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<String> fouten = new ArrayList<>();
		BigDecimal percentage = null;
		try {
			percentage = new BigDecimal(request.getParameter("percentage"));
			if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
				fouten.add("Percentage moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("Percentage moet een getal zijn");
		}
		if (fouten.isEmpty()) {
			artikelService.prijsVerhoging(percentage);
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL , request.getContextPath())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
