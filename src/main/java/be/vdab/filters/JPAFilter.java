package be.vdab.filters;

import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.persistence.*;
import javax.servlet.*;

@WebFilter("*.htm")
public class JPAFilter implements Filter {
	private static EntityManagerFactory entityManagerFactory;
	private static ThreadLocal<EntityManager> entityManagers;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("vdab2");
		entityManagers = new ThreadLocal<EntityManager>();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		entityManagers.set(entityManagerFactory.createEntityManager());
		try {
			chain.doFilter(request, response);
		} finally {
			EntityManager entityManager = entityManagers.get();
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			entityManager.close();
			entityManagers.remove();
		}
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}

	public static EntityManager getEntityManager() {
		return entityManagers.get();
	}
}
