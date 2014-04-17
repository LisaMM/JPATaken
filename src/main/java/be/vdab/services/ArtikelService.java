package be.vdab.services;

import javax.persistence.EntityManager;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entitites.Artikel;
import be.vdab.filters.JPAFilter;

public class ArtikelService {
	private final ArtikelDAO artikelDAO = new ArtikelDAO();

	public void create(Artikel artikel) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			artikelDAO.create(artikel, entityManager);
			entityManager.getTransaction().commit();
		} catch (RuntimeException ex) {
			entityManager.getTransaction().rollback();
			throw ex;
		} finally {
			entityManager.close();
		}
	}
}
