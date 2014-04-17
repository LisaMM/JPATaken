package be.vdab.filters;

import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.persistence.*;
import javax.servlet.*;

@WebFilter("*.htm")
public class JPAFilter implements Filter {
	private static EntityManagerFactory entityManagerFactory;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("vdab2");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
